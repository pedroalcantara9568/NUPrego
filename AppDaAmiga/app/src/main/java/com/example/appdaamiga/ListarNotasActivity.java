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
    private TextView valor;
    private List<Nota> notas;
    private List<Nota> alunosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);
        listView = findViewById(R.id.listaAlunos);
        dao = new NotaDAO(this);
        notas = dao.findAll();
        valor = findViewById(R.id.txtValor);
        alunosFiltrados.addAll(notas);

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
        double aux = 0;
        for(Nota a : notas) {
            if(a.getNome().toLowerCase().contains(nome.toLowerCase())){
                alunosFiltrados.add(a);
                aux += a.getValor();
                valor.setText(String.valueOf(aux));
            }
        }
        valor.setText(String.valueOf(aux));
        listView.invalidateViews();
    }

    public void cadastrarAluno (MenuItem item){
        Intent i = new Intent(this, CadastroNotaActivity.class);
        startActivity(i);
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
      final Nota notaExcluir = alunosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atençao")
                .setMessage("Realmente deseja excluir pessoa ?")
                .setNegativeButton("NÃO",null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alunosFiltrados.remove(notaExcluir);
                        notas.remove(notaExcluir);
                        dao.excluir(notaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Nota notaAtualizar = alunosFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroNotaActivity.class);
        it.putExtra("nota", notaAtualizar);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        notas = dao.findAll();
        alunosFiltrados.clear();
        alunosFiltrados.addAll(notas);
        double aux = 0;
        for(Nota a : notas) {
                aux += a.getValor();
        }
        valor.setText(String.valueOf(aux));
        listView.invalidateViews();
    }


}
