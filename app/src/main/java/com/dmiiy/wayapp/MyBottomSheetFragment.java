package com.dmiiy.wayapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MyBottomSheetFragment extends BottomSheetDialogFragment implements OnRecyclerViewItemClickListener{

private RecyclerView recyclerView;
private List<ItemObject> itemObjects;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NORMAL,R.style.AppBottomSheetDialogTheme);
        BottomSheetDialog bottomSheetDialog =(BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet,null);
        bottomSheetDialog.setContentView(view);

        recyclerView=view.findViewById(R.id.rcv_data);
        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        cargarLista();
        //ProgramAdapter programAdapter = new ProgramAdapter(programNameList, programDescriptionList, programImages);
        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
    }

    private void cargarLista() {
        itemObjects=new ArrayList<ItemObject>();
        itemObjects.add(new ItemObject("Наследие Сталина","На весь день",R.drawable.build7,"22"));
        itemObjects.add(new ItemObject("Погружение в Азию","Фуд-трип",R.drawable.asian,"23"));
        itemObjects.add(new ItemObject("Топ мест Москвы","Туристичкий тур",R.drawable.moscow,"25"));
        itemObjects.add(new ItemObject("Старые времена","Полное погружение",R.drawable.kolom,"31"));
        ProgramAdapter programAdapter = new ProgramAdapter(itemObjects,this);
        recyclerView.setAdapter(programAdapter);
    }

    @Override
    public void onnItemClick(int position) {
        if (position==0){
        Intent intent= new Intent(getContext(),Trip1.class);
        startActivity(intent);}
        if (position==1){
            Intent intent= new Intent(getContext(),Trip2.class);
            startActivity(intent);
        }
        if (position==2){
            Intent intent= new Intent(getContext(),Trip3.class);
            startActivity(intent);
        }
        if (position==3){
            Intent intent= new Intent(getContext(),Trip4.class);
            startActivity(intent);
        }
    }

    @Override
    public void onLongItemClick(int position) {

    }
}
