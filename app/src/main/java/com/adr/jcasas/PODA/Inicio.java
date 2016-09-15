package com.adr.jcasas.PODA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Inicio extends AppCompatActivity {

    private Button btnInicio,btnAdministrativo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        DCampo helper =new DCampo(this);
        btnInicio=(Button)findViewById(R.id.btnOperario);
        btnAdministrativo=(Button)findViewById(R.id.btnAdministrativo) ;

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Login =
                        new Intent(Inicio.this,MainActivity.class);
                startActivity(Login);
            }
        });
        btnAdministrativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Administrativo =
                        new Intent(Inicio.this,Administrativo.class);
                startActivity(Administrativo);
            }
        });

    }
}
