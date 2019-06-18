package com.example.appdaamiga;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotaAdapter extends BaseAdapter {

    private List<Nota> notas;

    private Activity activity;

    public NotaAdapter(Activity activity, List<Nota> notas) {
        this.activity = activity;
        this.notas = notas;
    }
    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return notas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        TextView nome = v.findViewById(R.id.txtNome);
        TextView valor = v.findViewById(R.id.txtCpf);
        TextView descricao = v.findViewById(R.id.txtTelefone);

        Nota a = notas.get(position);
        nome.setText(a.getNome());
        descricao.setText(a.getDescricao());
        valor.setText(a.getValor().toString());
        return v;
    }

}
