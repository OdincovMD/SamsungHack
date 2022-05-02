package com.dmiiy.wayapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class MyBottomSheetFragment extends BottomSheetDialogFragment {




    // Next, prepare your data set. Create two string arrays for program name and program description respectively.
    String[] programNameList = {"C", "C++", "Java", "Android"};
    String[] programDescriptionList = {"C Description", "C++ Description", "Java Description",
            "Android Description"};
    // Define an integer array to hold the image recourse ids
    int[] programImages = {R.drawable.c, R.drawable.css3,
            R.drawable.html5, R.drawable.github};

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog =(BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_sheet,null);
        bottomSheetDialog.setContentView(view);
        RecyclerView rcvData=view.findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(linearLayoutManager);
        ProgramAdapter programAdapter = new ProgramAdapter(programNameList, programDescriptionList, programImages);
        rcvData.setAdapter(programAdapter);
        RecyclerView.ItemDecoration itemDecoration= new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
    }
}
