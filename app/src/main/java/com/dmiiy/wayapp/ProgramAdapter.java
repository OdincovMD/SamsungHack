package com.dmiiy.wayapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder>  {
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private List<ItemObject> listatrips;
    public ProgramAdapter(List<ItemObject> listatrips, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.listatrips = listatrips;
        this.onRecyclerViewItemClickListener=onRecyclerViewItemClickListener;
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
final ItemObject itemObject= listatrips.get(position);
holder.tv_tripname.setText(itemObject.getTripname());
holder.tv_abouttrip.setText(itemObject.getNote());
holder.tv_photo.setImageResource(itemObject.getPhoto());
    }

    @Override
    public int getItemCount() {

        return listatrips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView tv_tripname;
       public TextView tv_abouttrip;
       public ImageView tv_photo;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tv_tripname =(TextView) itemView.findViewById(R.id.tv_nombre);
           tv_abouttrip = (TextView) itemView.findViewById(R.id.tv_grupo);
           tv_photo =(ImageView) itemView.findViewById(R.id.iv_foto);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onRecyclerViewItemClickListener.onnItemClick(getAdapterPosition());
               }
           });
           itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View view) {
                   onRecyclerViewItemClickListener.onLongItemClick(getAdapterPosition());
                   return true;
               }
           });
       }
   }
}
