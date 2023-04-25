package com.example.elbadulake.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MiDB extends SQLiteOpenHelper{

    //Definimos variables de la DB
    private static int versionDB = 1;
    private static String nombreDB   = "DasBD";

    public MiDB(@Nullable Context context){
        super(context, nombreDB, null, versionDB);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" CREATE TABLE Usuarios (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "'Nombre' VARCHAR(255) NOT NULL," +
                "'Contraseña' VARCHAR(255) NOT NULL) ");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Al añadir un nuevo campo tenemos que eliminar la tabla anterior y volver a crear una nueva con el campo nuevo
        sqLiteDatabase.execSQL("DROP TABLE Usuarios");
        onCreate(sqLiteDatabase);

    }
}





