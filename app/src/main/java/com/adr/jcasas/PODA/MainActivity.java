package com.adr.jcasas.PODA;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button Ingresar;
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnSalir;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario=(EditText) findViewById(R.id.txtUsuario);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        Ingresar=(Button)findViewById(R.id.btn_Ingresar);
        DCampo Campo = new DCampo(this);
        final SQLiteDatabase db = Campo.getWritableDatabase();
        Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password;
                String Usu_Nombre;
                String Usu_Password=null;
                FichaRegistro Re=new FichaRegistro();

               Re.usuario=txtUsuario.getText().toString();
                password=txtPassword.getText().toString();
                Re.usuario=Re.usuario.toUpperCase();

                boolean TodoOk=true;
                String Clave=null;
                Cursor c = db.rawQuery("SELECT * FROM Usuario WHERE Usu_Nombre='"+ Re.usuario+"'", null);
                if (c.moveToFirst()) {

                    do {
                        Usu_Nombre=c.getString(1);
                        Usu_Password=c.getString(2).trim();
                        TodoOk=true;

                        Clave=Funciones.Encriptar(password);
                    } while (c.moveToNext());
                }
                    else
                    {
                        TodoOk=false;
                        Usu_Password="";
                      /*  DialogFragment dialog = new MyDialogFragment();
                        dialog.show(getSupportFragmentManager(), "MyDialogFragmentTag");
                        txtUsuario.setText("");
                        txtPassword.setText("");*/
                    }

                if (Usu_Password.equals(Clave))
                {
                    TodoOk=true;
                }
                else
                {
                    TodoOk=false;
                    DialogFragment dialog = new MyDialogFragment();
                    dialog.show(getSupportFragmentManager(), "MyDialogFragmentTag");
                    txtUsuario.setText("");
                    txtPassword.setText("");
                }
                if (TodoOk==true)
                {
                    Intent Menu =
                            new Intent(MainActivity.this,Menu.class);
                    startActivity(Menu);
                }
            }
        });

    }
    public static class MyDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("REGISTRO PODA");
            builder.setMessage("USUARIO O CONTRASEÑA INCORRECTA");
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
