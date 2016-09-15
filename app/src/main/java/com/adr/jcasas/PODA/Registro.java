package com.adr.jcasas.PODA;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;


public class Registro extends AppCompatActivity {
private EditText txtDia,txtMes,txtAño;
    private int mMes,mAño,mDia,sDia,sMes,sAño;
    private Button btnFecha,btnInicio;

    //ArrayAdapter para conectar el Spinner a nuestros recursos strings.xml
    protected ArrayAdapter<CharSequence> adapter;
    SimpleCursorAdapter SucursalAdapter,adspnLote;

    static final int DATE_ID=0;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtDia = (EditText) findViewById(R.id.txtDia);
        txtMes = (EditText) findViewById(R.id.txtMes);
        txtAño = (EditText) findViewById(R.id.txtAño);

        txtDia.setEnabled(false);
        txtMes.setEnabled(false);
        txtAño.setEnabled(false);
        txtDia.setVisibility(View.INVISIBLE);
        txtMes.setVisibility(View.INVISIBLE);
        txtAño.setVisibility(View.INVISIBLE);

        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnInicio=(Button)findViewById(R.id.btn_Iniciar) ;
        DCampo Campo = new DCampo(this);
        db = Campo.getWritableDatabase();
        final Spinner cboFundo = (Spinner)findViewById(R.id.cboFundo);

//Obtener instancia del GameSpinner
        final Spinner cboZona = (Spinner) findViewById(R.id.cboZona);

//Asignas el origen de datos desde los recursos
        adapter = ArrayAdapter.createFromResource(this, R.array.Zonas,
                android.R.layout.simple_spinner_item);

//Asignas el layout a inflar para cada elemento
//al momento de desplegar la lista
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//Seteas el adaptador
        assert cboZona != null;
        cboZona.setAdapter(adapter);
        cboZona.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        FichaRegistro Re=new FichaRegistro();
                        String ZonaId =  parent.getItemAtPosition(position).toString();
                        //Integer Suc_Id = ZonaId.getInt(ZonaId.getColumnIndex(BaseColumns._ID));
                        switch ( ZonaId ) {
                            case "ZONA ALTA":
                                Re.Zona="ZA";
                                break;
                            case "ZONA BAJA":
                                Re.Zona="ZB";
                            default:
                                System.out.println("error" );
                                break;
                        }

                        //Toast.makeText(Registro.this, ZonaId.toString(),Toast.LENGTH_LONG).show();
                        Cursor Sucursal=db.rawQuery("SELECT  Suc_Id  AS _id,Suc_Descripcion,suc_Zona FROM SUCURSAL WHERE Suc_Zona='"+Re.Zona+"'",null);
                        SucursalAdapter = new SimpleCursorAdapter(Registro.this,
                                android.R.layout.simple_spinner_item,
                                Sucursal,
                                new String[]{"Suc_Descripcion"},
                                new int[]{android.R.id.text1},
                                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                        assert cboFundo != null;
                        cboFundo.setAdapter(SucursalAdapter);

                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        final Spinner CboLote= (Spinner) findViewById(R.id.cboLote);

        assert cboFundo != null;
        cboFundo.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {

                        Cursor CurId = (Cursor) parent.getItemAtPosition(position);
                        Integer Suc_Id = CurId.getInt(CurId.getColumnIndex(BaseColumns._ID));

                        //Toast.makeText(Registro.this, Suc_Id.toString(),Toast.LENGTH_LONG).show();
                        Cursor Sucursal = db.rawQuery("SELECT Con_Id AS _id,Suc_Id,Con_Descripcion,Con_DescripcionCor,Est_Id FROM LOTE WHERE Suc_Id="+Suc_Id,null);
                        adspnLote = new SimpleCursorAdapter(Registro.this ,
                                android.R.layout.simple_spinner_item,Sucursal,//Layout simple
                                //Todos los registros
                                new String[]{T_Lote.FIELD_DESCRIPCIONCOR},//Mostrar solo el nombre
                                new int[]{android.R.id.text1}//View para el nombre
                                ,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                        assert CboLote != null;
                        CboLote.setAdapter(adspnLote);

                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });




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


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FichaRegistro Fr=new FichaRegistro();
                assert CboLote != null;
                Cursor Lote_Id = (Cursor) CboLote.getSelectedItem();
                Fr.Lote = Lote_Id.getString(Lote_Id.getColumnIndex("Con_DescripcionCor"));
                //Fr.Lote =(String)CboLote.getSelectedItem();
                        Fr.Fecha=txtDia.getText().toString()+"/"+txtMes.getText().toString()+"/"+txtAño.getText().toString();

                Intent FichaRegistro =
                        new Intent(Registro.this,FichaRegistro.class);
                startActivity(FichaRegistro);
            }
        });
 btnFecha.setEnabled(false);
      btnFecha.setVisibility(View.INVISIBLE);

    }
    private void ColocarFecha()
    {
        txtDia.setText(new StringBuilder().append(mDia));
        txtMes.setText(new StringBuilder().append(mMes+1));
        txtAño.setText(new StringBuilder().append(mAño));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()

    {
      public void onDateSet(DatePicker view,int year,int monthOfYear,int dayOfMonth)
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
