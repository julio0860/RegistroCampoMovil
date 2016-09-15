package com.adr.jcasas.PODA;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jcasas on 21/04/2016.
 */
public class DCampo extends SQLiteOpenHelper {
    private static final String DB_NAME="ADRCAMPO";
    private static final int SCHEME_VERSION=1;
    private SQLiteDatabase db;

    public DCampo(Context context) {
        super(context, DB_NAME, null, SCHEME_VERSION);
        db=this.getWritableDatabase();
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(T_Registro.CREATE_DB_TABLE);
        db.execSQL(T_Usuario.CREATE_DB_TABLE);
        db.execSQL(T_Menu.CREATE_DB_TABLE);
        db.execSQL(T_MenuUsuario.CREATE_DB_TABLE);
        db.execSQL(T_Sucural.CREATE_DB_TABLE);
        db.execSQL(T_Responsable.CREATE_DB_TABLE);
        db.execSQL(T_Lote.CREATE_DB_TABLE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS Registro");
     db.execSQL(T_Registro.CREATE_DB_TABLE);


    }
}
