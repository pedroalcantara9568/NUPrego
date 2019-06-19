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

    public long inserir(Nota nota) {
        ContentValues values = new ContentValues();
        values.put("nome", nota.getNome());
        values.put("valor",String.valueOf(nota.getValor()));
        values.put("descricao", nota.getDescricao());
        return banco.insert("nota" ,null,values);
    }

    public List<Nota> findAll(){
        List<Nota> notas = new ArrayList<>();
        Cursor cursor = banco.query("nota", new String[ ] {"id","nome","valor","descricao"}, null,null,null,null,null,null);
        while (cursor.moveToNext()){
            Nota a = new Nota();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setValor(cursor.getDouble(2));
            a.setDescricao(cursor.getString(3));
            notas.add(a);
        }
        return notas;
    }

    public void excluir(Nota a){
        banco.delete("nota","id = ?", new String[] {a.getId().toString()});
    }
    public void atualizar( Nota nota){
        ContentValues values = new ContentValues();
        values.put("nome", nota.getNome());
        values.put("valor",String.valueOf(nota.getValor()));
        values.put("descricao", nota.getDescricao());
        banco.update("nota", values,"id = ?", new String[]{nota.getId().toString()});
    }

}
