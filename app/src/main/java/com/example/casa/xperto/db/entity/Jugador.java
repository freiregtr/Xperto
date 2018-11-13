package com.example.casa.xperto.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
// importar con static la opcion CASCADE
import static android.arch.persistence.room.ForeignKey.CASCADE;

import com.example.casa.xperto.constantes.Constantes;

@Entity(tableName = Constantes.NOMBRE_TABLA_JUGADOR,
        foreignKeys = @ForeignKey(entity = Equipo.class,
        parentColumns = "id",
        childColumns = "equipoId",
        onDelete = CASCADE))
public class Jugador {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "rut")
    private String rut;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "posicion")
    private int Posicion;

    @ColumnInfo(name = "equipoId")
    private int profesorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return Posicion;
    }

    public void setPosicion(int posicion) {
        Posicion = posicion;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }
}
