package com.adr.jcasas.PODA;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;


//HOLA MUNDO
public class Administrativo extends AppCompatActivity {
    private static final String TAG ="Administrativo" ;
    private EditText txtDia,txtMes,txtAño;
    private int mMes,mAño,mDia,sDia,sMes,sAño;
    private Button btnFecha,btnGenerar,btnSincronizar;
    static final int DATE_ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrativo);

        txtDia = (EditText) findViewById(R.id.txtDia);
        txtMes = (EditText) findViewById(R.id.txtMes);
        txtAño = (EditText) findViewById(R.id.txtAño);

        txtDia.setEnabled(false);
        txtMes.setEnabled(false);
        txtAño.setEnabled(false);
        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnGenerar=(Button) findViewById(R.id.btnGenerar);
        btnSincronizar=(Button)findViewById(R.id.btn_Sincronizar);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });
        Calendar C = Calendar.getInstance();
        sAño = C.get(Calendar.YEAR);
        sMes = C.get(Calendar.MONTH);
        sDia = C.get(Calendar.DAY_OF_MONTH);

        txtDia.setText(new StringBuilder().append(sDia));
        txtMes.setText(new StringBuilder().append(sMes + 1));
        txtAño.setText(new StringBuilder().append(sAño));
        DCampo Campo = new DCampo(this);
        final SQLiteDatabase db = Campo.getWritableDatabase();

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Lote,Id,Fecha,Registrador,Valor_C,Frc,Fry,PromY,Frp,Valor_Y,Valor_P,Supervisor,Ubicacion,Falta,Limpiza,Zona;
                String Acumulador="";
                String Fechap,Dia,Mes,Año;
                Dia=txtDia.getText().toString();
                Mes=txtMes.getText().toString();
                Año=txtAño.getText().toString();
                Fechap=Dia+"/"+Mes+"/"+Año;

                WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = manager.getConnectionInfo();
                String address = info.getMacAddress();

                Cursor c = db.rawQuery("SELECT Id_Registo,Fecha,Id,Lote,Cargadores,Frc,Yemas,Promy,Fry,Pitones,Frp,Supervisor,Ubicacion,Falta,Limpieza,Registrador,Zona FROM Registro WHERE Fecha='"+Fechap+"'", null);
                if (c.moveToFirst()) {

                    do {
                        Fecha= c.getString(1);//LOTE
                        Id=c.getString(2);
                        Lote=c.getString(3);//FECHA
                        Valor_C=c.getString(4);//VALOR_C
                        Frc=c.getString(5);//FRC
                        Valor_Y= c.getString(6);//VALOR_Y
                        PromY= c.getString(7);//PROMY
                        Fry= c.getString(8);//FRY
                        Valor_P= c.getString(9);//VALOR_P
                        Frp= c.getString(10);//FRP
                        Supervisor= c.getString(11);//SUPERVISOR
                        Ubicacion= c.getString(12);//UBICACION
                        Falta= c.getString(13);//FALTA
                        Limpiza= c.getString(14);//LIMPIEZA
                        Registrador= c.getString(15);//REGISTRADOR
                        Zona=c.getString(16);//ZONA

                        Acumulador=Acumulador+Fecha+"|"+Id+"|"+Lote+"|"+Valor_C+"|"+Frc+"|"+Valor_Y+"|"+PromY+"|"+Fry+
                                "|"+Valor_P+"|"+Frp+"|"+Supervisor+"|"+Ubicacion+"|"+Falta+"|"+Limpiza+"|"+Registrador+"|"+Zona+"\n";

                    } while (c.moveToNext());
                }
             if (Acumulador.equals(""))
             {
                 Toast.makeText(getBaseContext(), "No Hay Registros", Toast.LENGTH_SHORT).show();
             }
                else
             {
                 File sdCard;
                 String Fecha1;
                 Fecha1=Dia+"-"+Mes+"-"+Año;
                 String DireccionMac;
                 DireccionMac=address.replace(":","-");
                 String INTERNAL_FILENAME =Fecha1+"--"+DireccionMac+".txt";
                 sdCard = Environment.getExternalStorageDirectory();
                 File wFile = new File(sdCard.getAbsolutePath(), INTERNAL_FILENAME);
                 try{
                     FileWriter out = new FileWriter(wFile);
                     out.write(Acumulador);
                     out.close();
                     Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();
                 }catch (IOException e){
                      Toast.makeText(getBaseContext(), "Error escribiendo fichero en memoria interna", Toast.LENGTH_SHORT).show();
                 }
             }
            }
        });
        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DELETE FROM MenuUsuario");
                db.execSQL("DELETE FROM Usuario");
                db.execSQL("DELETE FROM Menu");
                db.execSQL("DELETE FROM Sucursal");
                db.execSQL("DELETE FROM Responsable");
                db.execSQL("DELETE FROM Lote");

                Connection Cnn = ConexionBD.getInstance().getConnection();

                try {
                    Statement Stmt = Cnn.createStatement();
                    ResultSet Rse = Stmt.executeQuery("SELECT Usu_Id,Usu_Nombre,ISNULL(Usu_Password,'') AS Usu_Password ,1 AS Usu_Sincronizado,Est_Id FROM Usuario where Usu_EsMovil=1");
                    while (Rse.next()) {
                        try {
                            db.execSQL(T_Usuario._INSERT(Rse.getInt(1), Rse.getString(2), Rse.getString(3), Rse.getInt(4), Rse.getInt(5)));
                            Toast.makeText(getBaseContext(), "Usuario Sincronizado", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error Exception: " + e);
                        }
                    }
                    Rse.close();
                    //noinspection UnusedAssignment,UnusedAssignment
                    Rse=null;

                } catch (Exception e) {
                    Log.e(TAG, "Error Exception: " + e);
                }

                try {
                    Statement Stmt = Cnn.createStatement();
                    ResultSet Rse = Stmt.executeQuery("select Men_Codigo,Men_Descripcion,Men_Form,Men_Anterior,Men_Modulo,Est_Id from Menu WHERE Men_Modulo='AMC'");
                    while (Rse.next()) {
                        try {
                            db.execSQL(T_Menu._INSERT(Rse.getString(1), Rse.getString(2), Rse.getString(3), Rse.getString(4), Rse.getString(5),Rse.getInt(6)));
                            Toast.makeText(getBaseContext(), "Menu Sincronizado", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error Exception: " + e);
                        }
                    }
                    Rse.close();
                    //noinspection UnusedAssignment,UnusedAssignment
                    Rse=null;

                } catch (Exception e) {
                    Log.e(TAG, "Error Exception: " + e);
                }
                try {
                    Statement Stmt = Cnn.createStatement();
                    ResultSet Rse = Stmt.executeQuery("SELECT Suc_Id,Suc_Descripcion,Suc_Zona FROM Sucursal WHERE Suc_EsPlanta=2");
                    while (Rse.next()) {
                        try {
                            db.execSQL(T_Sucural._INSERT(Rse.getInt(1), Rse.getString(2), Rse.getString(3)));
                            Toast.makeText(getBaseContext(), "Sucursales Sincronizadas", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error Exception: " + e);
                        }
                    }
                    Rse.close();
                    //noinspection UnusedAssignment,UnusedAssignment
                    Rse=null;

                } catch (Exception e) {
                    Log.e(TAG, "Error Exception: " + e);
                }

                try {
                    Statement Stmt = Cnn.createStatement();
                    ResultSet Rse = Stmt.executeQuery("SELECT Lot_Id,Suc_Id,Lot_Descripcion,Lot_DescripcionCor,Est_Id FROM LOTECALIDAD");
                    while (Rse.next()) {
                        try {
                            db.execSQL(T_Lote._INSERT(Rse.getInt(1), Rse.getInt(2), Rse.getString(3), Rse.getString(4),Rse.getInt(5)));
                            Toast.makeText(getBaseContext(), "Lotes Sincronizados", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error Exception: " + e);
                        }
                    }
                    Rse.close();
                    //noinspection UnusedAssignment,UnusedAssignment
                    Rse=null;

                } catch (Exception e) {
                    Log.e(TAG, "Error Exception: " + e);
                }
                try {
                    Statement Stmt = Cnn.createStatement();
                    ResultSet Rse = Stmt.executeQuery("SELECT Res_Id,Res_Codigo,Res_Descripcion,Est_Id FROM Responsable WHERE Res_Supervisor=1");
                    while (Rse.next()) {
                        try {
                            db.execSQL(T_Responsable._INSERT(Rse.getInt(1), Rse.getString(2), Rse.getString(3), Rse.getInt(4)));
                            Toast.makeText(getBaseContext(), "Supervisores Sincronizados", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error Exception: " + e);
                        }
                    }
                    Rse.close();
                    //noinspection UnusedAssignment,UnusedAssignment
                    Rse=null;

                } catch (Exception e) {
                    Log.e(TAG, "Error Exception: " + e);
                }
                try {
                    Statement Stmt = Cnn.createStatement();
                    ResultSet Rse = Stmt.executeQuery("SELECT MU.* FROM MenuUsuario MU INNER JOIN Menu M ON M.Men_Codigo=MU.Men_Codigo WHERE M.Men_Modulo='AMC'");
                    while (Rse.next()) {
                        try {
                            db.execSQL(T_MenuUsuario._INSERT(Rse.getInt(1), Rse.getInt(2), Rse.getString(3), Rse.getString(4), Rse.getString(5),Rse.getString(6),
                                                             Rse.getString(7),Rse.getString(8),Rse.getString(9),Rse.getString(10),Rse.getString(11),Rse.getString(12),Rse.getString(13)));
                            Toast.makeText(getBaseContext(), "Menu Usuario Sincronizado", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e(TAG, "Error Exception: " + e);
                        }
                    }
                    Rse.close();
                    Cnn.close();
                } catch (Exception e) {
                    Log.e(TAG, "Error Exception: " + e);
                }
            }
        });
    }
    private void ColocarFecha()
    {
        txtDia.setText(new StringBuilder().append(mDia));
        txtMes.setText(new StringBuilder().append(mMes+1));
        txtAño.setText(new StringBuilder().append(mAño));
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            mAño=year;
            mMes=monthOfYear;
            mDia=dayOfMonth;
            ColocarFecha();
        }
    };

    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_ID:
                return new DatePickerDialog(this,mDateSetListener,sAño,sMes,sDia);
        }
        return null;
    }
}
