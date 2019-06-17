package com.example.appdaamiga;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;



public class CadastroNotaActivity extends  AppCompatActivity {

    private EditText nome;
    private EditText valor;
    private EditText descricao;
    private NotaDAO dao;

    private Aluno aluno = null;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        valor = findViewById(R.id.editCPF);
        descricao = findViewById(R.id.editTelefone);
        dao = new NotaDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("nota" )){
            aluno =(Aluno) it.getSerializableExtra("nota");
            nome.setText(aluno.getNome().toString());
            valor.setText(aluno.getValor().toString());
            descricao.setText(aluno.getDescricao().toString());
        }
    }

    public void salvar (View view) {
        if(aluno == null ){
        aluno = new Aluno();
        aluno.setNome(nome.getText().toString());
        aluno.setValor(valor.getText().toString());
        aluno.setDescricao(descricao.getText().toString());
        long id = dao.inserir(aluno);
        Toast.makeText(this, "Nota inserida com sucesso"  + id, Toast.LENGTH_LONG).show();
        }else{
            aluno.setNome(nome.getText().toString());
            aluno.setValor(valor.getText().toString());
            aluno.setDescricao(descricao.getText().toString());
            dao.atualizar(aluno);
            Toast.makeText(this, "Nota alterada com sucesso", Toast.LENGTH_LONG).show();
        }
    }




}





