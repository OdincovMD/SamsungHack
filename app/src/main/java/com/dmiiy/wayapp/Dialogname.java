package com.dmiiy.wayapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Dialogname extends AppCompatDialogFragment {

    private MaterialEditText editTextUsername;
    private ExampleDialogListener listener;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String UID;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialogname, null);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance("https://wayapp-a6d5d-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users");
        UID=user.getUid();
        builder.setView(view)
                .setTitle("Поменять имя")
                .setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Сменить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = editTextUsername.getText().toString();
                        if(username.isEmpty()){
                            editTextUsername.setError("Заполните строку с именем!");
                            editTextUsername.requestFocus();
                            return;
                        }
                        listener.applyTexts(username);
                        reference.child(UID).child("name").setValue(username);
                    }
                });

        editTextUsername = view.findViewById(R.id.edit_username);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(String username);
    }
}