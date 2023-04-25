package com.example.elbadulake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elbadulake.db.MiDB;

import java.util.Locale;

public class Registro extends AppCompatActivity {

    Button btnRegistrar;
    private EditText textoNombreUsuario;
    private EditText textoPWD;
    private static String nombreUsu="";
    private static String pwdUsu="";

    private static String idioma="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setTitle("");

        super.onCreate(savedInstanceState);
        idioma= getIntent().getStringExtra("Idioma");
        if(savedInstanceState!=null){

            String nombre = savedInstanceState.getString("nombre");
            String pwd = savedInstanceState.getString("pwd");
            nombreUsu=nombre;
            pwdUsu=pwd;
            idioma=savedInstanceState.getString("Idioma");
        }
        setContentView(R.layout.activity_registro);

        btnRegistrar = findViewById(R.id.btnEnviarRegistro);
        textoNombreUsuario = findViewById(R.id.editTextTextPersonName2);
        textoPWD = findViewById(R.id.editTextTextPassword);
        textoNombreUsuario.setText(nombreUsu);
        textoPWD.setText(pwdUsu);


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiDB miDB = new MiDB(Registro.this);
                SQLiteDatabase bd = miDB.getWritableDatabase();

                //Comprobamos si se han añadido los datos
                if(!textoNombreUsuario.getText().toString().equals("") && !textoPWD.getText().toString().equals("")){

                    ContentValues nuevo = new ContentValues();
                    nuevo.put("Nombre", textoNombreUsuario.getText().toString());
                    nuevo.put("Contraseña",textoPWD.getText().toString());
                    bd.insert("Usuarios", null, nuevo);


                    if(idioma.equals("es")){
                        Toast.makeText(Registro.this, "Registrado correctamente",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Registro.this, "Successfully registered",Toast.LENGTH_SHORT).show();
                    }



                    Intent intent = new Intent(Registro.this, MenuLogeado.class);
                    intent.putExtra("Idioma",idioma);
                    startActivity(intent);
                }
                else{
                    Log.i("INFO",btnRegistrar.getText().toString());
                    if(idioma.equals("es")){
                        Toast.makeText(Registro.this, "RELLENA TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Registro.this, "FILL ALL THE FIELDS",Toast.LENGTH_SHORT).show();
                    }                }
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("nombre",textoNombreUsuario.getText().toString());
        savedInstanceState.putString("pwd",textoPWD.getText().toString());
        savedInstanceState.putString("Idioma",idioma);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        String parametro= savedInstanceState.getString("Idioma");

        idioma=parametro;
        Locale nuevaLoc = new Locale(idioma);
        Locale.setDefault(nuevaLoc);
        Configuration configuration=getBaseContext().getResources().getConfiguration();
        configuration.setLocale(nuevaLoc);
        configuration.setLayoutDirection(nuevaLoc);

        Context context=getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        finish();
        startActivity(getIntent());
    }
}