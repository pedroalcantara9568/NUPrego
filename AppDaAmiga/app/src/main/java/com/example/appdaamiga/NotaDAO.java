package com.example.appdaamiga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public NotaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Aluno aluno ) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("valor",aluno.getValor());
        values.put("descricao", aluno.getDescricao());
        return banco.insert("nota" ,null,values);
    }

    public List<Aluno> findAll(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("nota", new String[ ] {"id","nome","valor","descricao"}, null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setValor(cursor.getString(2));
            a.setDescricao(cursor.getString(3));
            alunos.add(a);
        }
        return alunos;
    }

    public void excluir(Aluno a){
        banco.delete("nota","id = ?", new String[] {a.getId().toString()});
    }
    public void atualizar( Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("valor",aluno.getValor());
        values.put("descricao", aluno.getDescricao());
        banco.update("nota", values,"id = ?", new String[]{aluno.getId().toString()});
    }

}
