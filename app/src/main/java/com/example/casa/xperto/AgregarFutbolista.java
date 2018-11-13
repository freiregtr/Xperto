package com.example.casa.xperto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgregarFutbolista extends AppCompatActivity {

    private Button volverMenu;

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
    }
}
