package com.example.elbadulake;



import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.elbadulake.db.MiDB;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Inicializamos las variables

    Button btnIniciarSesion;
    Button btnRegistrar;
    private static String idioma="es";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Borramos la action bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setTitle("");

        //RE¡cuperamos los datos guardados
        if (savedInstanceState!= null)
        {
            idioma = savedInstanceState.getString("Idioma");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistro);
        ImageButton btnEsp =findViewById(R.id.icoEsp);
        ImageButton btnEnglish =findViewById(R.id.icoEnglish);


        //Definimos la accion al clickar el boton de españa
        btnEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Idioma cambiado a castellano",Toast.LENGTH_SHORT).show();

                idioma ="es";
                Locale nuevaLoc = new Locale("es");
                Locale.setDefault(nuevaLoc);
                Configuration configuration=getBaseContext().getResources().getConfiguration();
                configuration.setLocale(nuevaLoc);
                configuration.setLayoutDirection(nuevaLoc);

                Context context=getBaseContext().createConfigurationContext(configuration);
                getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idioma ="en";
                Toast.makeText(MainActivity.this, "Language set to English",Toast.LENGTH_SHORT).show();


                Locale nuevaLoc = new Locale("en");
                Locale.setDefault(nuevaLoc);
                Configuration configuration=getBaseContext().getResources().getConfiguration();
                configuration.setLocale(nuevaLoc);
                configuration.setLayoutDirection(nuevaLoc);

                Context context=getBaseContext().createConfigurationContext(configuration);
                getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
                finish();
                startActivity(getIntent());
            }
        });


        //Definimos las acciones de los botones
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiDB miDB = new MiDB(MainActivity.this);
                SQLiteDatabase bd = miDB.getWritableDatabase();
                // CAMBIAR EL CATALOGO A INICIAR SESION
                Intent intent = new Intent(MainActivity.this, IniciarSesion.class);
                intent.putExtra("Idioma",getIdioma());
                startActivityIntent.launch(intent);
            }

        });



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MiDB miDB = new MiDB(MainActivity.this);
                SQLiteDatabase bd = miDB.getWritableDatabase();
                Intent intent = new Intent(MainActivity.this, Registro.class);
                intent.putExtra("Idioma",getIdioma());
                startActivityIntent.launch(intent);
            }

        });


    }

    ActivityResultLauncher<Intent> startActivityIntent =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {

                            if (result.getResultCode() == RESULT_OK){

                            }
                        }
                    });


    public String getIdioma(){
        return idioma;
    }

    //Guardar los valores de la instancia(para prevenir los giros y demas)
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
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