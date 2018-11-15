package com.example.casa.xperto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.casa.xperto.db.database.Db;
import com.example.casa.xperto.db.entity.Equipo;

import java.util.ArrayList;
import java.util.List;

public class AgregarPartido extends AppCompatActivity {

    private Button volverMenu;
    private Spinner comboRival_1;
    private Spinner comboRival_2;
    private Spinner comboRivales;
    private List<Equipo> listaEquipos;
    private List<Equipo> listaRivales;
    private int idRival_1;
    private Integer rival_1;
    private int idRival_2;
    private Integer rival_2;
    private int idGanador;
    private EditText goles;
    // Objeto que se utiliza dentro de los adapters
    private Equipo equipoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_partido);
        configurarVista();
    }

    private void configurarVista(){

        // volver al menu
        volverMenu = findViewById(R.id.btnVolverAgregarPar);
        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.casa.xperto.MainActivity.class));
            }
        });

        // obtener las listas para trabajar con los spinners
        listaEquipos = new ArrayList<>();
        listaRivales = new ArrayList<>();
        listaEquipos = Db.obtenerDb(getApplicationContext()).equipoDao().listarEquipos();

        // instancias del spinners
        comboRival_1 = (Spinner) findViewById(R.id.spinnerR1AgregarPar);
        comboRival_2 = (Spinner) findViewById(R.id.spinnerR2AgregarPar);

        // lógica del spinner + adapter
        ArrayAdapter<Equipo> adaptador = new ArrayAdapter<Equipo>(this,android.R.layout.simple_spinner_item, listaEquipos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboRival_1.setAdapter(adaptador);
        comboRival_2.setAdapter(adaptador);

        //listeners para instanciar los idRivales
        comboRival_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equipoAdapter = (Equipo) parent.getSelectedItem();
                idRival_1 = equipoAdapter.getId();
                // metodo para agregar la selección al spinner de los rivales
                equipoAdapter = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        comboRival_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equipoAdapter = (Equipo) parent.getSelectedItem();
                idRival_2 = equipoAdapter.getId();
                equipoAdapter = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // marcador
        goles = findViewById(R.id.etGolesAgregarPar);

        verificarRivales(idRival_1, idRival_2);

        // combo para rivales
        comboRivales = (Spinner) findViewById(R.id.spinnerRivalesAgregarPar);
        ArrayAdapter<Equipo> adaptadorRivales = new ArrayAdapter<Equipo>(this, android.R.layout.simple_spinner_item, listaRivales);
        comboRivales.setAdapter(adaptadorRivales);

        comboRivales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Equipo equipoGanador = (Equipo) parent.getSelectedItem();
                idGanador = equipoGanador.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // metodo para obtener y actualizar los rivales
    private void verificarRivales(int id1, int id2){

        ArrayList<Equipo> lista = new ArrayList<>();
        rival_1 = (Integer) id1;
        if(rival_1 == 0){
            Equipo objRival_1 = new Equipo();
            objRival_1 = Db.obtenerDb(getApplicationContext()).equipoDao().encontrarEquipoPorId(1);
            lista.add(objRival_1);
        }
        else
        {
            Equipo objRival_1 = new Equipo();
            objRival_1 = Db.obtenerDb(getApplicationContext()).equipoDao().encontrarEquipoPorId(rival_1);
            lista.add(objRival_1);
        }

        rival_2 = (Integer) id2;
        if(rival_2 == 0){
            Equipo objRival_2 = new Equipo();
            objRival_2 = Db.obtenerDb(getApplicationContext()).equipoDao().encontrarEquipoPorId(2);
            lista.add(objRival_2);
        }
        else
        {
            Equipo objRival_2 = new Equipo();
            objRival_2 = Db.obtenerDb(getApplicationContext()).equipoDao().encontrarEquipoPorId(rival_2);
            lista.add(objRival_2);
        }

        listaRivales = lista;
    }

}