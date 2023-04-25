package com.example.elbadulake;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class UsuarioIncorrecto extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error al Iniciar Sesión");
        builder.setMessage("No se ha podido Iniciar Sesión ");
        builder.setPositiveButton("cerrar",null);
        return builder.create();
    }
}


