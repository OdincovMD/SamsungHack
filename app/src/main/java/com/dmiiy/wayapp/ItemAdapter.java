package com.dmiiy.wayapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemObject> mListItems;
    private IClickListner iClickListner;

    public ItemAdapter(List<ItemObject> mListItems,IClickListner iClickListner) {
        this.mListItems = mListItems;
        this.iClickListner= iClickListner;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
final ItemObject itemObject=mListItems.get(position);
if (itemObject==null){
    return;
}
holder.tvItem.setText(itemObject.getName());
holder.tvItem.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        iClickListner.clickItem(itemObject);
    }
});
    }

    @Override
    public int getItemCount() {
        if (mListItems!=null){
            return mListItems.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
private TextView tvItem;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem=itemView.findViewById(R.id.tv_item);
        }
    }
}
