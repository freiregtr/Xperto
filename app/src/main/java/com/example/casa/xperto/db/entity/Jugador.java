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
    private String posicion;

    @ColumnInfo(name = "equipoId")
    private int equipoId;

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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }
}
