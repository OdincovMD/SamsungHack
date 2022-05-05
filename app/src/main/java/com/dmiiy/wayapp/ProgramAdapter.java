package com.dmiiy.wayapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder>  {
    private List<ItemObject> listaObjeto;
    public ProgramAdapter(List<ItemObject> listaObjeto){
        this.listaObjeto=listaObjeto;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View listaItem= layoutInflater.inflate(R.layout.layout_item,null,false);
        ViewHolder view= new ViewHolder(listaItem);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
final ItemObject itemObject=listaObjeto.get(position);
holder.tv_nombre.setText(itemObject.getNombre());
holder.tv_grupo.setText(itemObject.getGrupo());
holder.tv_foto.setImageResource(itemObject.getFoto());
    }

    @Override
    public int getItemCount() {

        return listaObjeto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       public TextView tv_nombre;
       public TextView tv_grupo;
       public ImageView tv_foto;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_nombre=(TextView) itemView.findViewById(R.id.tv_nombre);
           tv_grupo= (TextView) itemView.findViewById(R.id.tv_grupo);
           tv_foto=(ImageView) itemView.findViewById(R.id.iv_foto);
       }
   }
}
