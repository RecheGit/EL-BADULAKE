package com.example.elbadulake;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class ListadoCompra extends DialogFragment {



    ListenerdelDialogo miListener;
    public interface ListenerdelDialogo {
        void alpulsarComprar();

    }

    Catalogo cat = new Catalogo();
    private static String[] nombreComida = {"Aguacate", "Carrito", "Alitas de pollo", "Pizza","Flan de chocolate", "Palomitas", "Rosquillas","Donut", "Sardina"};
    private static String[] precioComida = {"Precio: 2€ ","Precio: 20€ ","Precio: 6€ ","Precio: 1€ ","Precio: 2€ ","Precio: 1€ ","Precio: 2€ ","Precio: 1€ ","Precio: 7€ "};
    private static int[] imagenesComida = {R.drawable.aguacate,R.drawable.cestacompra,R.drawable.alitas,R.drawable.pizza,R.drawable.flan,R.drawable.palomitas,R.drawable.rosquilla,R.drawable.donut,R.drawable.sardina};
    @NonNull
    @Override




    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        super.onCreateDialog(savedInstanceState);

        miListener =(ListenerdelDialogo) getActivity();

        //Creamos el Dialogo con el builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        //llamada al metodo de imprimir con los datos actuales
        AdaptadorListViewCatalogo listaImprimir = new AdaptadorListViewCatalogo(this.getContext(),cat.getNombreComida(), cat.getPrecioComida(), imagenesComida,null,null,cat.getCantidad());
        //Mostrar elementos del ArrayList
        builder.setItems(listaImprimir.prepararDialogo().toArray(new String[0]),null);


        //Definicion de los botones disponibles en el dialogo

        //DIFERENCIAMOS SEGUN EL IDIOMA
        String idioma= cat.getIdioma();
        if (idioma.equals("es")){

            builder.setTitle("Detalles de su compra: ");

            builder.setPositiveButton("Comprar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    //Creamos la notificacion local
                    NotificationManager elManager = (NotificationManager)  getContext().getSystemService(NOTIFICATION_SERVICE);
                    NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(getContext(), "IdCanal");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        elManager.createNotificationChannel(elCanal);

                        //Personalizamos el mensaje de lanotificacion
                        elBuilder.setSmallIcon(R.drawable.icobadulake)
                                .setContentTitle("Compra realizada correctamente")
                                .setContentText("Su pedido está en camino.")
                                .setSubText("")
                                .setVibrate(new long[]{0, 1000, 500, 1000})
                                .setAutoCancel(true);
                        elCanal.setDescription("Descripción del canal");
                        elCanal.enableLights(true);
                        elCanal.setLightColor(Color.RED);
                        elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                        elCanal.enableVibration(true);
                        elManager.notify(1, elBuilder.build());
                    }
                    miListener.alpulsarComprar();
                }
            });
            builder.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }
        else {

            builder.setTitle("Purchase details: ");
            builder.setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    //Creamos la notificacion local
                    NotificationManager elManager = (NotificationManager)  getContext().getSystemService(NOTIFICATION_SERVICE);
                    NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(getContext(), "IdCanal");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal",
                                NotificationManager.IMPORTANCE_DEFAULT);
                        elManager.createNotificationChannel(elCanal);

                        //Personalizamos el mensaje de lanotificacion
                        elBuilder.setSmallIcon(R.drawable.icobadulake)
                                .setContentTitle("Purchase correctly done")
                                .setContentText("Your order is on the way.")
                                .setSubText("")
                                .setVibrate(new long[]{0, 1000, 500, 1000})
                                .setAutoCancel(true);
                        elCanal.setDescription("Descripción del canal");
                        elCanal.enableLights(true);
                        elCanal.setLightColor(Color.RED);
                        elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                        elCanal.enableVibration(true);
                        elManager.notify(1, elBuilder.build());
                    }
                    miListener.alpulsarComprar();
                }
            });
            builder.setNegativeButton("Return", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
        }



        return builder.create();
    }
}


