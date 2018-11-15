package com.example.casa.xperto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.casa.xperto.db.database.Db;
import com.example.casa.xperto.db.entity.Jugador;

import java.util.ArrayList;
import java.util.List;

public class ListarJugador extends AppCompatActivity {

    private Button volverMenu;
    private Button buscarJugador;
    private Button buscarJugadores;
    private EditText etBuscarNombreJugador;

    private Jugador objJugador;
    private List<Jugador> listaJugadores;
    // instancia de la BBDD AsyncTask
    private LeerBaseDatosTask leerBaseDatosTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_jugador);
        configurarVista();
    }

    private void configurarVista()
    {
        volverMenu = findViewById(R.id.btnVolverListarJug);
        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.MainActivity.class));
            }
        });

        // ejecutar boton para buscar un solo jugador
        etBuscarNombreJugador = findViewById(R.id.etNombreListarJug);
        buscarJugador = findViewById(R.id.btnBuscarSListarJug);
        buscarJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objJugador = Db.obtenerDb(getApplicationContext()).jugadorDAO().encontrarJugadorPorNombre(etBuscarNombreJugador.getText().toString());
                mostrarUnJugador(objJugador);
            }
        });

        // ejecutar boton para buscar varios jugadores
        buscarJugadores = findViewById(R.id.btnBuscarPListarJug);
        buscarJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerBaseDatosTask = new LeerBaseDatosTask();
                // no lleva ningun argumento el execute al no ser in insert
                leerBaseDatosTask.execute();
            }
        });

        objJugador = new Jugador();
        listaJugadores = new ArrayList<>();
    }

    // class asyntask para leer todo
    public class LeerBaseDatosTask extends AsyncTask<Void, Void, List<Jugador>>{

        @Override
        protected List<Jugador> doInBackground(Void... voids) {
            listaJugadores = Db.obtenerDb(getApplicationContext()).jugadorDAO().listaJugadores();
            return listaJugadores;
        }

        @Override
        protected void onPostExecute(List<Jugador> jugadores){
            mostrarJugadores(jugadores);
        }
    }

    // buscar varios jugadores
    private void mostrarJugadores(List<Jugador> jugadores){
        for(Jugador jugador : jugadores){
            // mostrar en consola
            Log.d("TAG", "id: " + jugador.getId() + " Nombre: " + jugador.getNombre() + " rut: " +
            jugador.getRut() + " posicion: " + jugador.getPosicion() + " id equipo: " + jugador.getEquipoId());
        }
    }

    // buscar un solo jugador
    private void mostrarUnJugador(Jugador jugador){
        Log.d("TAG", "id: " + jugador.getNombre() + " Nombre: " + jugador.getNombre() + " rut: " +
                jugador.getRut() + " posicion: " + jugador.getPosicion() + " id equipo: " + jugador.getEquipoId());
    }
}
