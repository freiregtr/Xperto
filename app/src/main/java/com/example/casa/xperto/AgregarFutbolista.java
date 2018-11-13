package com.example.casa.xperto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.casa.xperto.db.database.Db;
import com.example.casa.xperto.db.entity.Equipo;
import com.example.casa.xperto.db.entity.Jugador;

import java.util.ArrayList;
import java.util.List;

public class AgregarFutbolista extends AppCompatActivity {

    private Button volverMenu;
    private Button guardarFutbolista;
    private Spinner comboEquipos;
    private List<Equipo> listaEquipos;
    private int idEquipo;
    private EditText rutFutbolista;
    private EditText nombreFutbolista;
    private EditText posicionFutbolista;
    private int idFKFutbolista;
    private Jugador objJugador;

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

        comboEquipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Equipo equipo = (Equipo) parent.getSelectedItem();
                idEquipo = equipo.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // agregar Usuario
        objJugador = new Jugador();
        nombreFutbolista = findViewById(R.id.etNombreAgregarFut);
        rutFutbolista = findViewById(R.id.etRutAgregarFut);
        posicionFutbolista = findViewById(R.id.etPosicionAgregarFut);
        guardarFutbolista = findViewById(R.id.btnGuardarAgregarFut);

        // onclick listener
        guardarFutbolista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // guardando los datos para crear el registro en la BBDD
                objJugador.setNombre(nombreFutbolista.getText().toString());
                objJugador.setRut(rutFutbolista.getText().toString());
                objJugador.setPosicion(posicionFutbolista.getText().toString());
                objJugador.setEquipoId(idEquipo);
                // pasar los parametros a la clase Db
                Db.obtenerDb(getApplicationContext()).jugadorDAO().insert(objJugador);
            }
        });
    }
}
