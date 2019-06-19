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

    private Nota nota = null;

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
            nota =(Nota) it.getSerializableExtra("nota");
            nome.setText(nota.getNome());
            valor.setText(String.valueOf(nota.getValor()));
            descricao.setText(nota.getDescricao());
        }
    }

    public void salvar (View view) {
        if(nota == null ){
        nota = new Nota();
        nota.setNome(nome.getText().toString());
        nota.setValor(Double.parseDouble(valor.getText().toString()));
        nota.setDescricao(descricao.getText().toString());
        long id = dao.inserir(nota);
        Toast.makeText(this, "Nota inserida com sucesso", Toast.LENGTH_LONG).show();
        }else{
            nota.setNome(nome.getText().toString());
            nota.setValor(Double.parseDouble(valor.getText().toString()));
            nota.setDescricao(descricao.getText().toString());
            dao.atualizar(nota);
            Toast.makeText(this, "Nota alterada com sucesso", Toast.LENGTH_LONG).show();
        }
    }




}





