package com.example.casa.xperto.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.casa.xperto.constantes.Constantes;

@Entity(tableName = Constantes.NOMBRE_TABLA_EQUIPO)
public class Equipo {

    // autoincremental
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombreEquipo;

    @ColumnInfo(name = "pais")
    private String Pais;

    // getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    @Override
    public String toString() {
        return id + " - " + nombreEquipo;
    }
}
