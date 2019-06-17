package com.example.appdaamiga;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ListarNotasActivity extends AppCompatActivity {




    private ListView listView;
    private NotaDAO dao;
    private List<Aluno> alunos;
    private List<Aluno> alunosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alunos);

        listView = findViewById(R.id.listaAlunos);
        dao = new NotaDAO(this);
        alunos = dao.findAll();
        alunosFiltrados.addAll(alunos);
        NotaAdapter alunoAdapter = new NotaAdapter(this,alunosFiltrados);
        listView.setAdapter(alunoAdapter);
        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menucontexto,menu);

    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraAluno(newText);
                return false;
            }
        });
        return true;
    }
    public void procuraAluno(String nome){
        alunosFiltrados.clear();
        for(Aluno a : alunos) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())){
                alunosFiltrados.add(a);
            }
        }
        listView.invalidateViews();
    }

    public void cadastrarAluno (MenuItem item){
        Intent i = new Intent(this, CadastroNotaActivity.class);
        startActivity(i);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
      final Aluno alunoExcluir = alunosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atençao")
                .setMessage("Realmente deseja excluir pessoa ?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alunosFiltrados.remove(alunoExcluir);
                        alunos.remove(alunoExcluir);
                        dao.excluir(alunoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Aluno alunoAtualizar = alunosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, CadastroNotaActivity.class);
        it.putExtra("nota", alunoAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        alunos = dao.findAll();
        alunosFiltrados.clear();
        alunosFiltrados.addAll(alunos);
        listView.invalidateViews();
    }


}
