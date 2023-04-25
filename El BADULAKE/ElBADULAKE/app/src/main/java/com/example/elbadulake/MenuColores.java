package com.example.elbadulake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuColores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_colores);

        String[] arraydedatos={"Tema Calido","Tema Oscuro","Tema Frio","Tema APU","Tema España","Tema UK"};
        String[] arraydedatosEN={"Warm Theme","Dark Theme","Cold Theme","APU Theme","Spain Theme","UK Theme"};


        if(getIntent().getStringExtra("idioma").equals("Settings")){
            arraydedatos=arraydedatosEN;
        }
        ArrayAdapter eladaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arraydedatos);
        ListView lalista = (ListView) findViewById(R.id.lvOpcionesPers);
        lalista.setAdapter(eladaptador);

        lalista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent();
                Log.i("id","numero:"+i);
                if (i==0){
                    intent.putExtra("color", "amarillo");
                    intent.putExtra("code", "amarillo");
                }
                else if (i==1) {
                    intent.putExtra("color", "oscuro");
                    intent.putExtra("code", "oscuro");

                }
                else if (i==2) {
                    intent.putExtra("color", "azulCielo");
                    intent.putExtra("code", "azulCielo");
                }
                else if (i==3) {
                    intent.putExtra("color", "apu");
                    intent.putExtra("code", "apu");

                }
                else if (i==4) {
                    intent.putExtra("color", "españa");
                    intent.putExtra("code", "españa");

                }
                else if (i==5) {
                    intent.putExtra("color", "uk");
                    intent.putExtra("code", "uk");

                }
                setResult(RESULT_OK, intent);


                finish();
            }
        });
    }

}