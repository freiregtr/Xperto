package com.example.casa.xperto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.casa.xperto.db.entity.Equipo;

import java.util.ArrayList;

public class AgregarFutbolista extends AppCompatActivity {

    private Button volverMenu;
    private Spinner comboEquipos;
    ArrayList<String> listaEquipos;
    ArrayList<Equipo> listaObjEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_futbolista);
        configurarVista();
    }

    private void configurarVista(){
        volverMenu = findViewById(R.id.btnVolverAgregarFut);
        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.MainActivity.class));
            }
        });

        // Lógica spinner


    }
}
