package com.example.elbadulake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.elbadulake.db.MiDB;

import java.util.Locale;

public class IniciarSesion extends AppCompatActivity {

    Button btnAceptar;
    EditText textoPwd;
    EditText textoUser;

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

        setContentView(R.layout.activity_iniciar_sesion);

        btnAceptar = findViewById(R.id.btnAceptar);
        textoPwd = findViewById(R.id.textoPWD);
        textoUser = findViewById(R.id.textoUsuario);
        textoUser.setText(nombreUsu);
        textoPwd.setText(pwdUsu);


        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Conectamos con la BD
                MiDB miDB = new MiDB(IniciarSesion.this);
                SQLiteDatabase bd = miDB.getWritableDatabase();
                nombreUsu = textoUser.getText().toString();
                pwdUsu = textoPwd.getText().toString();


                //Comprobamos que usuario y contraseña son correctos

                String[] campos = new String[] {"Nombre","Contraseña"};
                String[] argumentos = new String[] {nombreUsu,pwdUsu};
                Cursor cu = bd.query("Usuarios",campos,"Nombre=? and Contraseña=?",argumentos,null,null,null);


                //Si no se ha encontrado usuario que coincida con los datos introducidos:

                if (cu.getCount()==0){

                    if(idioma.equals("es")){
                        UsuarioIncorrecto incorrecto = new UsuarioIncorrecto();
                        incorrecto.show(getSupportFragmentManager(),"error");
                    }
                    else{
                        UsuarioIncorrectoEnglish incorrect = new UsuarioIncorrectoEnglish();
                        incorrect.show(getSupportFragmentManager(),"error");
                    }



                }
                else {
                    Intent intent = new Intent(IniciarSesion.this, MenuLogeado.class);
                    intent.putExtra("Idioma",idioma);
                    startActivity(intent);

                }
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("nombre",textoUser.getText().toString());
        savedInstanceState.putString("pwd",textoPwd.getText().toString());
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