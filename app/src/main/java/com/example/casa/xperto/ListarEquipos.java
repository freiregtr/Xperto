package com.example.casa.xperto;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.casa.xperto.db.database.Db;
import com.example.casa.xperto.db.entity.Equipo;

import java.util.ArrayList;
import java.util.List;

public class ListarEquipos extends AppCompatActivity {

    private Button volverMenu;
    private Button buscarEquipo;
    private Button buscarEquipos;
    private EditText etBuscarNombre;

    private Equipo objEquipo;
    private List<Equipo> listaEquipos;
    // instancia de la BBDD AsyncTask
    private LeerBaseDatosTask leerBaseDatosTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_equipos);
        configurarVista();
    }

    private void configurarVista(){
        // volver al menu
        volverMenu = findViewById(R.id.btnVolverListarEq);
        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.MainActivity.class));
            }
        });

        // ejecutar boton para buscar un solo equipo
        etBuscarNombre = findViewById(R.id.etNombreBuscarEq);
        buscarEquipo = findViewById(R.id.btnBuscarSListarEq);
        buscarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objEquipo = Db.obtenerDb(getApplicationContext()).equipoDao().encontrarEquipoPorNombre(etBuscarNombre.getText().toString());
                mostrarUnEquipo(objEquipo);
            }
        });

        // ejecutar boton para buscar varios equipos
        buscarEquipos = findViewById(R.id.btnBuscarPListarEq);
        buscarEquipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerBaseDatosTask = new LeerBaseDatosTask();
                // no lleva ningun argumento el execute al no ser in insert
                leerBaseDatosTask.execute();
            }
        });

        objEquipo = new Equipo();
        listaEquipos = new ArrayList<>();
    }

    // class asyntask para leer todo
    public class LeerBaseDatosTask extends AsyncTask<Void, Void, List<Equipo>>{

        @Override
        protected List<Equipo> doInBackground(Void... voids) {
            listaEquipos = Db.obtenerDb(getApplicationContext()).equipoDao().listarEquipos();
            return listaEquipos;
        }

        @Override
        protected void onPostExecute(List<Equipo> equipos){
            mostrarEquipos(equipos);
        }
    }
    // buscar varios equipos
    private void mostrarEquipos(List<Equipo> equipos){
        for(Equipo equipo : equipos){
            // mostrar en consola
            Log.d("TAG", "nombre: " + equipo.getNombreEquipo() + " Pais: " + equipo.getPais() + "\n");
        }
    }

    // buscar un solo equipo
    private void mostrarUnEquipo(Equipo equipo){
        Log.d("TAG", "nombre: " + equipo.getNombreEquipo() + " Pais: " + equipo.getPais() + "\n");
    }
}
