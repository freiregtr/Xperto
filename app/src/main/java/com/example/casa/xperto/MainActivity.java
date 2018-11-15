package com.example.casa.xperto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button AgregarEquipo;
    private Button AgregarFutbolista;
    private Button ListarEquipo;
    private Button listarJugadores;
    private Button agregarPartido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarVista();
    }

    private void configurarVista(){
        AgregarEquipo = findViewById(R.id.btnAgregarEquipo);
        AgregarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.AgregarEquipo.class));
            }
        });

        AgregarFutbolista = findViewById(R.id.btnAgregarFutbolista);
        AgregarFutbolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.AgregarFutbolista.class));
            }
        });

        ListarEquipo = findViewById(R.id.btnListarEquipo);
        ListarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.ListarEquipos.class));
            }
        });

        listarJugadores = findViewById(R.id.btnListarJugadores);
        listarJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListarJugador.class));
            }
        });

        agregarPartido = findViewById(R.id.btnAgregarPartido);
        agregarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AgregarPartido.class));
            }
        });
    }
}
