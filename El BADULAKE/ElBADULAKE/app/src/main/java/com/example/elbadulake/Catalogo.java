package com.example.elbadulake;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;

public class Catalogo extends AppCompatActivity implements ListadoCompra.ListenerdelDialogo {

    //Declaramos las variables de forma static para que no se cambien
    private static String[] cantidad = {"0","0","0","0","0","0","0","0","0"};
    private static String[] nombreComida = {"Aguacate", "Carrito", "Alitas de pollo", "Pizza","Flan de chocolate", "Palomitas", "Rosquillas","Donut", "Sardina"};

    private static String[] nombreComidaEnglish = {"Avocado", "Shopping cart", "Chicken wings", "Pizza","Chocolate custard", "Popcorns", "Pretzel","Donut", "Sardine"};

    private static String[] precioComidaEnglish = {"Price: 2€ ","Price: 20€ ","Price: 6€ ","Price: 1€ ","Price: 2€ ","Price: 1€ ","Price: 2€ ","Price: 1€ ","Price: 7€ "};
    private static String[] precioComida = {"Precio: 2€ ","Precio: 20€ ","Precio: 6€ ","Precio: 1€ ","Precio: 2€ ","Precio: 1€ ","Precio: 2€ ","Precio: 1€ ","Precio: 7€ "};
    private static int[] imagenesComida = {R.drawable.aguacate,R.drawable.cestacompra,R.drawable.alitas,R.drawable.pizza,R.drawable.flan,R.drawable.palomitas,R.drawable.rosquilla,R.drawable.donut,R.drawable.sardina};
    //elementos del list view
    private Button btnmas ;
    private Button btnmenos ;

    private static String idioma="";

    private static String color="purple_200";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        idioma= getIntent().getStringExtra("Idioma");
        if (savedInstanceState!= null)
        {
            String[] valor= savedInstanceState.getStringArray("cantidad");
            cantidad = valor;
            idioma = savedInstanceState.getString("Idioma");
        }


         btnmas = new Button(this);
         btnmenos = new Button(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        // Recibimos el color de la edicion
        color = getIntent().getStringExtra("code");
        Log.i("COLOR CATALOGO", color);




        Button btnComprar = findViewById(R.id.button4);

        if(color.equals("amarillo")){
            btnComprar.setBackgroundColor(getColor(R.color.amarilloPalido));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.naranja);
        }
        else if (color.equals("azulCielo")) {
            btnComprar.setBackgroundColor(getColor(R.color.marron));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.verde_apu);

        }
        else if (color.equals("oscuro")) {
            btnComprar.setBackgroundColor(getColor(R.color.black));
            btnComprar.setTextColor(getColor(R.color.white));
            getWindow().setBackgroundDrawableResource(androidx.cardview.R.color.cardview_dark_background);

        }
        else if (color.equals("apu")) {
            btnComprar.setBackgroundColor(getColor(R.color.verde_apu));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.verde_pistacho);
        }
        else if (color.equals("españa")) {
            btnComprar.setBackgroundColor(getColor(R.color.rojoEspaña));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.amarilloEspaña);
        }
        else if (color.equals("uk")) {
            btnComprar.setBackgroundColor(getColor(R.color.rojo));
            btnComprar.setTextColor(getColor(R.color.black));
            getWindow().setBackgroundDrawableResource(R.color.azul);
        }



        if(idioma.equals("es")){
            btnComprar.setText("Comprar");
        }
        ListView catalogo = (ListView) findViewById(R.id.listView);

        if(btnComprar.getText().toString().equals("Comprar")){
            idioma = "es";
            AdaptadorListViewCatalogo eladap = new AdaptadorListViewCatalogo(getApplicationContext(),nombreComida,precioComida,imagenesComida,btnmas,btnmenos,cantidad);
            catalogo.setAdapter(eladap);
        }
        else{
            AdaptadorListViewCatalogo eladap = new AdaptadorListViewCatalogo(getApplicationContext(),nombreComidaEnglish,precioComidaEnglish,imagenesComida,btnmas,btnmenos,cantidad);
            catalogo.setAdapter(eladap);
        }




        //Llamada al dialogo
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean allEqual = Arrays.stream(cantidad).distinct().count() == 1;
                if(allEqual==false){
                    ListadoCompra listadoCompra = new ListadoCompra();
                    listadoCompra.show(getSupportFragmentManager(),"COMPRA");
                }
                else {
                    Toast.makeText(Catalogo.this, "ERROR",Toast.LENGTH_LONG).show();
                }


            }
        });

    }





    //Metodo para recuperar la cantidad de cada producto seleccionado
    public String[] getCantidad(){
        return cantidad;
    }
    public String[] getNombreComida() {

       // Button btn = findViewById(R.id.button4);

        if (idioma.equals("es")){
            return nombreComida;
        }
        else {
            return nombreComidaEnglish;
        }
    }
    public String[] getPrecioComida(){

        if (idioma.equals("es")){
            return precioComida;
        }
        else {
            return precioComidaEnglish;
        }
    }

    public String getIdioma(){
        return idioma;
    }


    //Metodo del listener que cierra el catalogo tras realizar la compra
    @Override
    public void alpulsarComprar() {
        finish();
        if(idioma.equals("es")){
            Toast.makeText(Catalogo.this, "GRACIAS POR SU COMPRA",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Catalogo.this, "THANKS FOR YOUR PURCHASE",Toast.LENGTH_LONG).show();
        }
    }

    //Mantenemos el idioma al rotar
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Button btnComprar2 =findViewById(R.id.button4);
        if(btnComprar2.getText().toString().equals("Comprar")) {
            AdaptadorListViewCatalogo adap = new AdaptadorListViewCatalogo(getApplicationContext(),nombreComida,precioComida,imagenesComida,btnmas,btnmenos,cantidad);
            savedInstanceState.putStringArray("cantidad",adap.getCantidad());
            savedInstanceState.putString("Idioma","es");
        }
        else{
            AdaptadorListViewCatalogo adap = new AdaptadorListViewCatalogo(getApplicationContext(),nombreComidaEnglish,precioComidaEnglish,imagenesComida,btnmas,btnmenos,cantidad);
            savedInstanceState.putStringArray("cantidad",adap.getCantidad());
            savedInstanceState.putString("Idioma","en");
        }

        savedInstanceState.putString("Idioma",idioma);
    }

    //Mantenemos el idioma al rotar
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