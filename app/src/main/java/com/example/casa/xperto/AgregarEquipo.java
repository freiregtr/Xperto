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

public class AgregarEquipo extends AppCompatActivity {

    private Button volverMenu, guardarEquipo;
    private EditText etNombre, etPais;

    private Equipo objEquipo;
    private List<Equipo> listaEquipos;
    // instancia de BBDD AsyncTask
    private EscribirBaseDatosTask baseDatosTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_equipo);
        configurarVista();
    }

    private void configurarVista()
    {
        // botones y editext
        volverMenu = findViewById(R.id.btnVolverAgregarEq);
        guardarEquipo = findViewById(R.id.btnGuardarAgregarEq);
        etNombre = findViewById(R.id.etNombreAgregarEq);
        etPais = findViewById(R.id.etPaisAgregarEq);

        // clase para ser guardada en AsynkTask
        objEquipo = new Equipo();

        // lista para invocar una consulta
        listaEquipos = new ArrayList<>();

        // Guardar equipo
        guardarEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objEquipo.setNombreEquipo(etNombre.getText().toString());
                objEquipo.setPais(etPais.getText().toString());
                EscribirBaseDatosTask baseDatosTask = new EscribirBaseDatosTask();
                baseDatosTask.execute(objEquipo);
            }
        });

        // volver al menu
        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.MainActivity.class));
            }
        });
    }

    // clase asyntask para guardar
    private class EscribirBaseDatosTask extends AsyncTask<Equipo, Void, Void>{
        // metodo que se ejecuta en segundo plano
        @Override
        protected Void doInBackground(Equipo... equipos) {
            // se llama al metodo estático y al metodo de la implementación del DAO
            Db.obtenerDb(getApplicationContext()).equipoDao().insertarEquipo(equipos[0]);
            return null;
        }
    }

    // clase asyntask para leer todo
    private class LeerBaseDatosTask extends AsyncTask<Void, Void, List<Equipo>>{

        // metodo para obtener todos los equipos
        @Override
        protected List<Equipo> doInBackground(Void... voids) {
            listaEquipos = Db.obtenerDb(getApplicationContext()).equipoDao().listarEquipos();
            return listaEquipos;
        }

        @Override
        protected void onPostExecute(List<Equipo> equipos){
            mostrarEquipo(equipos);
        }
    }

    private void mostrarEquipo(List<Equipo> equipos){
        for(Equipo equipo : equipos){
            // mostrar en consola
            Log.d("TAG", "nombre: " + equipo.getNombreEquipo() + " Pais: " + equipo.getPais() + " id: " + equipo.getId() + "\n");
        }
    }
}
