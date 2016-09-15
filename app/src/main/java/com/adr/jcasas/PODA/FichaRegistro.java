package com.adr.jcasas.PODA;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressWarnings("ALL")
public class FichaRegistro extends AppCompatActivity {
    public static  String Lote=null;
    public static  String Fecha=null;
   // public static  String Registrador=null;
    public static String usuario=null;
    public static String Zona=null;
    private TextView lblLote,lblFecha,lblRegistrador,lblResultado;
    private EditText txtCargadores,txtYema,txtPitones;
    private CheckBox chck_Ubicacion;
    private CheckBox chck_Falta;
    private CheckBox chck_Limpieza,chck_Frc,chck_Fry,chck_Frp;
    private Button btnGrabar,btnMostrar;
    int Idregistro=0;
    SimpleCursorAdapter adspnSupervisor;
    SQLiteDatabase db;
    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_registro);

        lblLote =(TextView)findViewById(R.id.lblLote);
        lblFecha =(TextView)findViewById(R.id.lblFecha);
        lblRegistrador =(TextView)findViewById(R.id.lblRegistrador);
        txtCargadores=(EditText) findViewById(R.id.txtCargadores);
        txtYema=(EditText)findViewById(R.id.txtYema);
        txtPitones=(EditText)findViewById(R.id.txtPitones);
        lblResultado=(TextView) findViewById(R.id.lblResultado);
        chck_Ubicacion=(CheckBox) findViewById(R.id.chck_Ubicacion);
        chck_Falta=(CheckBox) findViewById(R.id.chck_Falta);
        chck_Limpieza = (CheckBox) findViewById(R.id.chck_Limpieza);
        chck_Frc=(CheckBox)findViewById(R.id.chck_fCargadores);
        chck_Fry=(CheckBox)findViewById(R.id.chck_fYemas);
        chck_Frp=(CheckBox)findViewById(R.id.chck_fPitones);

        DCampo Campo=new DCampo(this);
        db=Campo.getWritableDatabase();

        lblLote.setText(Lote);
        lblFecha.setText(Fecha);
        lblRegistrador.setText(usuario);
        btnGrabar=(Button)findViewById(R.id.btnGrabar);
        final Spinner CboSupervisor= (Spinner) findViewById(R.id.cboSupervisor);
        Cursor Supervisor = db.rawQuery("Select Res_Id AS _id,Res_Codigo,Res_Descripcion,Est_Id from Responsable" ,null);
        adspnSupervisor = new SimpleCursorAdapter(FichaRegistro.this ,
                android.R.layout.simple_spinner_item,Supervisor,//Layout simple
                //Todos los registros
                new String[]{T_Responsable.FIELD_DESCRIPCION},//Mostrar solo el nombre
                new int[]{android.R.id.text1}//View para el nombre
                ,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //noinspection ConstantConditions,ConstantConditions
        CboSupervisor.setAdapter(adspnSupervisor);

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Cargadores;
                int yemas;
                int pitones;
                int ubicacion=0;
                int falta=0;
                int limpieza=0;
                int frc=0;
                double promY=0;
                int fry=0;
                int frp=0;
                String Id="1";
                String supervisor;

                Cursor Supervisor_Id = (Cursor) CboSupervisor.getSelectedItem();
                supervisor = Supervisor_Id.getString(Supervisor_Id.getColumnIndex("Res_Descripcion"));

                if (chck_Ubicacion.isChecked()) {ubicacion=1; }
                if (chck_Falta.isChecked()) {falta=1; }
                if (chck_Limpieza.isChecked()) {limpieza=1; }
                if (chck_Frc.isChecked()) {frc=1; }
                if (chck_Fry.isChecked()) {fry=1; }
                if (chck_Frp.isChecked()) {frp=1; }

                if (txtCargadores.getText().toString().equals("")|| txtYema.getText().toString().equals("") || txtPitones.getText().toString().equals("") )
                {
                    DialogFragment dialog = new MyDialogFragment1();
                    dialog.show(getSupportFragmentManager(), "MyDialogFragmentTag");
                }
                else {
                    Cargadores=Integer.parseInt(txtCargadores.getText().toString());
                    yemas=Integer.parseInt(txtYema.getText().toString());
                    pitones=Integer.parseInt(txtPitones.getText().toString());
                    promY=(double)yemas/Cargadores;
                    db.execSQL("INSERT INTO Registro(Lote,Id,Fecha,Registrador,Cargadores,Frc,Yemas,Promy,Fry,Pitones,Frp,Supervisor,Ubicacion,Falta,Limpieza,Zona) " +
                            "VAlUES ('" + Lote + "','"+ Id + "','"  + Fecha + "','" + usuario + "'," + Cargadores + ","+ frc + ","  + yemas + ","+ promY + ","
                            + fry + "," + pitones + ","+ frp + ",'"
                            + supervisor + "'," + ubicacion + "," + falta + "," + limpieza +",'"+Zona+"')");
                    Cursor c = db.rawQuery("SELECT MAX(Id) from Registro", null);
                    if (c.moveToFirst()) {
                        //lblResultado.setText("");
                        do {
                            //Idregistro = c.getInt(0);
                            //lblResultado.append("Contador:" + Idregistro);
                        } while (c.moveToNext());
                    }
                    Idregistro=Idregistro+1;
                    lblResultado.setText("Contador:" + Idregistro);
                    txtCargadores.setText("");
                    txtYema.setText("");
                    txtPitones.setText("");
                    chck_Ubicacion.setChecked(false);
                    chck_Falta.setChecked(false);
                    chck_Limpieza.setChecked(false);
                    chck_Frc.setChecked(false);
                    chck_Fry.setChecked(false);
                    chck_Frp.setChecked(false);
                }
            }

        });
        btnMostrar=(Button)findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MostrarRegistro =
                        new Intent(FichaRegistro.this,MostrarRegistro.class);
                startActivity(MostrarRegistro);
            }
        });
    }

    public static class MyDialogFragment1 extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("REGISTRO PODA");
            builder.setMessage("INGRESE VALOR !!! NO DEJE ESPACIOS EN BLANCO");
            final AlertDialog.Builder ok = builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // You don't have to do anything here if you just want it dismissed when clicked
                }
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}
