package com.adr.jcasas.PODA;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button btnPoda;
    private Button btnRaleo;
    private Button btnConteo;
    private Button btnCosecha;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnPoda = (Button) findViewById(R.id.btnPoda);
        btnRaleo = (Button) findViewById(R.id.btnRaleo);
        btnConteo = (Button) findViewById(R.id.btnConteo);
        btnCosecha = (Button) findViewById(R.id.btnCosecha);
        FichaRegistro Re=new FichaRegistro();
        DCampo Campo = new DCampo(this);
        db = Campo.getWritableDatabase();
        String btnActividad=null;
        Button Menu = null;
for (int i=1;i<=4;i++)
        {
            switch ( i ) {
                case 1:
                      btnActividad="btnPoda";
                      Menu=btnPoda;
                    break;
                case 2:
                    btnActividad="btnRaleo";
                    Menu=btnRaleo;
                    break;
                case 3:
                    btnActividad="btnConteo";
                    Menu=btnConteo;
                    break;
                case 4:
                    btnActividad="btnCosecha";
                    Menu=btnCosecha;
                    break;
                default:
                    System.out.println("error" );
                    break;
            }
            Cursor c = db.rawQuery("select  MenUsu_Permitido from MenuUsuario MU \n" +
                    "inner join Menu M on MU.Men_Codigo=M.Men_Codigo\n" +
                    "inner join Usuario U on MU.Usu_Id=U.Id\n" +
                    "where U.Usu_Nombre='"+ Re.usuario+"' AND M.Men_Form='"+btnActividad+"'",null);
            if (c.moveToFirst()) {

                do {
                   Menu.setEnabled(Funciones.Habilitar(c.getString(0)));
                } while (c.moveToNext());
            }
            else
            {
                Menu.setEnabled(false);
            }
            btnPoda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Registro =
                            new Intent(Menu.this,Registro.class);
                    startActivity(Registro);
                }
            });
        }

    }

}
