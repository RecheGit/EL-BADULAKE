package com.example.elbadulake;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


public class MenuLogeado extends AppCompatActivity {

    private Button btnComprar;
    private Button btnAjustes;
    private static String code=null;

    private boolean clicked = false;
    private static String colorBtn="default" ;

    private static String idioma="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setTitle("");
        idioma= getIntent().getStringExtra("Idioma");//idioma de la app


        if (savedInstanceState!= null)
        {
            String  valor= savedInstanceState.getString("colorBtn");
            idioma= getIntent().getStringExtra("Idioma");
            colorBtn=valor;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_logeado);

        btnComprar = findViewById(R.id.btnRealizarCompra);
        btnAjustes = findViewById(R.id.btnAjustesUsuario);

        //Establecemos el color de botones y fondo en funcion del resultado de lo elegido en settings(Si no se elige nada se usa el default)
        if(colorBtn.equals("default")){
            btnAjustes.setBackgroundColor(getColor(R.color.amarilloPalido));
            btnAjustes.setTextColor(getColor(R.color.black));
            btnComprar.setBackgroundColor(getColor(R.color.amarilloPalido));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.verde_apu);
        }
        if(colorBtn.equals("amarillo")){
            btnAjustes.setBackgroundColor(getColor(R.color.amarilloPalido));
            btnAjustes.setTextColor(getColor(R.color.black));
            btnComprar.setBackgroundColor(getColor(R.color.amarilloPalido));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.naranja);

        } else if (colorBtn.equals("azulCielo")) {

            btnAjustes.setBackgroundColor(getColor(R.color.marron));
            btnAjustes.setTextColor(getColor(R.color.black));
            btnComprar.setBackgroundColor(getColor(R.color.marron));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.verde_apu);

        }
        else if (colorBtn.equals("oscuro")) {

            btnAjustes.setBackgroundColor(getColor(R.color.black));
            btnAjustes.setTextColor(getColor(R.color.white));
            btnComprar.setBackgroundColor(getColor(R.color.black));
            btnComprar.setTextColor(getColor(R.color.white));
            getWindow().setBackgroundDrawableResource(androidx.cardview.R.color.cardview_dark_background);
        }
        else if (colorBtn.equals("apu")) {

            btnAjustes.setBackgroundColor(getColor(R.color.verde_apu));
            btnAjustes.setTextColor(getColor(R.color.black));
            btnComprar.setBackgroundColor(getColor(R.color.verde_apu));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.drawable.apu);
        }
        else if (colorBtn.equals("españa")) {
            btnAjustes.setBackgroundColor(getColor(R.color.amarilloEspaña));
            btnAjustes.setTextColor(getColor(R.color.white));
            btnComprar.setBackgroundColor(getColor(R.color.amarilloEspaña));
            btnComprar.setTextColor(getColor(R.color.white));
            getWindow().setBackgroundDrawableResource(R.color.rojoEspaña);
        }
        else if (colorBtn.equals("uk")) {
            btnAjustes.setBackgroundColor(getColor(androidx.cardview.R.color.cardview_dark_background));
            btnAjustes.setTextColor(getColor(R.color.white));
            btnComprar.setBackgroundColor(getColor(androidx.cardview.R.color.cardview_dark_background));
            btnComprar.setTextColor(getColor(R.color.white));
            getWindow().setBackgroundDrawableResource(R.drawable.uk);
        }


        //Volvemos despues de estar en settings
        ActivityResultLauncher<Intent> startActivityIntent =
                registerForActivityResult(new
                                ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if(result.getResultCode()==RESULT_OK){

                                    //Guardamos el color recibido
                                    String valor = result.getData().getStringExtra("color");
                                    code = result.getData().getStringExtra("code");


                                    if(valor.equals("amarillo")){
                                        colorBtn = valor;
                                        btnAjustes.setBackgroundColor(getColor(R.color.amarilloPalido));
                                        btnAjustes.setTextColor(getColor(R.color.black));
                                        btnComprar.setBackgroundColor(getColor(R.color.amarilloPalido));
                                        btnComprar.setTextColor(getColor(R.color.black));
                                        getWindow().setBackgroundDrawableResource(R.color.naranja);

                                    } else if (valor.equals("azulCielo")) {
                                        colorBtn = valor;
                                        btnAjustes.setBackgroundColor(getColor(R.color.marron));
                                        btnAjustes.setTextColor(getColor(R.color.black));
                                        btnComprar.setBackgroundColor(getColor(R.color.marron));
                                        btnComprar.setTextColor(getColor(R.color.black));
                                        getWindow().setBackgroundDrawableResource(R.color.verde_apu);

                                    }
                                    else if (valor.equals("oscuro")) {
                                        colorBtn = valor;
                                        btnAjustes.setBackgroundColor(getColor(R.color.black));
                                        btnAjustes.setTextColor(getColor(R.color.white));
                                        btnComprar.setBackgroundColor(getColor(R.color.black));
                                        btnComprar.setTextColor(getColor(R.color.white));
                                        getWindow().setBackgroundDrawableResource(androidx.cardview.R.color.cardview_dark_background);
                                    }
                                    else if (valor.equals("apu")) {
                                        colorBtn = valor;
                                        btnAjustes.setBackgroundColor(getColor(R.color.verde_apu));
                                        btnAjustes.setTextColor(getColor(R.color.black));
                                        btnComprar.setBackgroundColor(getColor(R.color.verde_apu));
                                        btnComprar.setTextColor(getColor(R.color.black));
                                        getWindow().setBackgroundDrawableResource(R.drawable.apu);
                                    }
                                    else if (valor.equals("españa")) {
                                        colorBtn = valor;
                                        btnAjustes.setBackgroundColor(getColor(R.color.amarilloEspaña));
                                        btnAjustes.setTextColor(getColor(R.color.white));
                                        btnComprar.setBackgroundColor(getColor(R.color.amarilloEspaña));
                                        btnComprar.setTextColor(getColor(R.color.white));
                                        getWindow().setBackgroundDrawableResource(R.color.rojoEspaña);
                                    }
                                    else if (valor.equals("uk")) {
                                        colorBtn = valor;
                                        btnAjustes.setBackgroundColor(getColor(androidx.cardview.R.color.cardview_dark_background));
                                        btnAjustes.setTextColor(getColor(R.color.white));
                                        btnComprar.setBackgroundColor(getColor(androidx.cardview.R.color.cardview_dark_background));
                                        btnComprar.setTextColor(getColor(R.color.white));
                                        getWindow().setBackgroundDrawableResource(R.drawable.uk);
                                    }



                                }
                            }
                        });



        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuLogeado.this, MenuColores.class);
                intent.putExtra("idioma",btnAjustes.getText().toString());
                startActivityIntent.launch(intent);

            }
        });


        //Enviamos el idioma y mantenemos los colores a¡en el catalogo

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuLogeado.this, Catalogo.class);
                if (code!=null){

                    intent.putExtra("code", code);
                }
                else{
                    intent.putExtra("code", "-1");
                }
                intent.putExtra("Idioma",idioma);

                startActivity(intent);


            }
        });


    }


    //Función para que el usuario no salga del menu principal
    public boolean onKeyUp(int KeyCode, KeyEvent event) {

        String idioma = btnAjustes.getText().toString();
        if(idioma.equals("Settings")){
            idioma="en";
         }
        else{
            idioma="es";
        }
        if (idioma.equals("en")){
            if (KeyCode == event.KEYCODE_BACK) {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("¿Do you want to exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.show();
            }

        }
        else{
            if(idioma.equals("es")){

                if (KeyCode == event.KEYCODE_BACK) {

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setMessage("¿Desea salir de la aplicación?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_HOME);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.show();
                }
            }


        }

        return super.onKeyDown(KeyCode,event);

    }


    //GUARDAR LOS COLORES AL GIRAR PANTALLA

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("colorBtn",colorBtn);
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