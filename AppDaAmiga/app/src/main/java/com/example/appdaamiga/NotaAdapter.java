package com.example.appdaamiga;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotaAdapter extends BaseAdapter {

    private List<Aluno> alunos;

    private Activity activity;

    public NotaAdapter(Activity activity, List<Aluno> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }
    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        TextView nome = v.findViewById(R.id.txtNome);
        TextView valor = v.findViewById(R.id.txtCpf);
        TextView descricao = v.findViewById(R.id.txtTelefone);

        Aluno a = alunos.get(position);

        nome.setText(a.getNome());
        descricao.setText(a.getDescricao());
        valor.setText(a.getValor());


        return v;
    }

}
