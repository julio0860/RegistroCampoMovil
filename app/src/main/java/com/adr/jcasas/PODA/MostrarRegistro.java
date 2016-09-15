package com.adr.jcasas.PODA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostrarRegistro extends AppCompatActivity {

    private ListView lstv_Registro;
    private GridView dgv_Registro;
  SimpleAdapter AdaptadorLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_registro);
        dgv_Registro=(GridView)findViewById(R.id.dgv_Registro);
        List<Map<String, Object>> data = null;
        data = new ArrayList<Map<String, Object>>();

        DCampo Campo = new DCampo(this);
        SQLiteDatabase db = Campo.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT Id_Registo,Fecha,Lote,Cargadores,Frc,Yemas,Promy,Fry,Pitones,Frp,Supervisor,Ubicacion,Falta,Limpieza,Registrador FROM Registro", null);
        if (c.moveToFirst()) {
            do {
                Map<String, Object> valores = new HashMap<String, Object>();
                valores.put("A", c.getString(1));//FECHA
                valores.put("B", c.getString(2));//LOTE
                valores.put("C", c.getString(3));//CARGADORES
                valores.put("D", c.getString(4));//FRC
                valores.put("E", c.getString(5));//YEMAS
                valores.put("F", c.getString(6));//PROMY
                valores.put("G", c.getString(7));//FRY
                valores.put("H", c.getString(8));//PITONES
                valores.put("I", c.getString(9));//FRP
                valores.put("J", c.getString(10));//SUPERVISOR
                valores.put("K", c.getString(11));//UBICACION
                valores.put("L", c.getString(12));//FALTA
                valores.put("M", c.getString(13));//LIMPIEZA
                valores.put("N", c.getString(14));//REGISTRADOR
                data.add(valores);
            } while (c.moveToNext());
        }
        String[] from = {"A", "B","C","D","E","F","G","H","I","J","K","L","M","N"};
       int[] views = {R.id.lblFecha, R.id.lblLote,R.id.lblCargadores,R.id.lblFrc,R.id.lblYemas,R.id.lblPromy,
               R.id.lblFry,R.id.lblPitones,R.id.lblFrp,R.id.lblSupervisor,R.id.lblUbicacion,R.id.lblFalta,R.id.lblLimpieza,R.id.lblRegistrador};
       // int[] views = {R.id.lblFecha, R.id.lblLote};
        AdaptadorLista = new SimpleAdapter(MostrarRegistro.this, data, R.layout.lstvpersonalizado, from, views);
        dgv_Registro.setAdapter(AdaptadorLista);
        ;
    }

}