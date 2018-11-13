package com.example.casa.xperto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.casa.xperto.db.database.Db;
import com.example.casa.xperto.db.entity.Equipo;

import java.util.ArrayList;
import java.util.List;

public class AgregarFutbolista extends AppCompatActivity {

    private Button volverMenu;
    private Spinner comboEquipos;
    private List<Equipo> listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_futbolista);
        configurarVista();
    }

    private void configurarVista(){


        listaEquipos = new ArrayList<>();
        listaEquipos = Db.obtenerDb(getApplicationContext()).equipoDao().listarEquipos();

        volverMenu = findViewById(R.id.btnVolverAgregarFut);
        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.MainActivity.class));
            }
        });

        // instancia spinner
        comboEquipos = (Spinner) findViewById(R.id.spinnerAgregarFut);

        // Lógica spinner
        ArrayAdapter<Equipo> adaptador = new ArrayAdapter<Equipo>(this,android.R.layout.simple_spinner_item, listaEquipos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboEquipos.setAdapter(adaptador);
    }


}
