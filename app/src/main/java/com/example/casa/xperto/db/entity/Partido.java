package com.example.casa.xperto.db.entity;
// importar con static la opcion CASCADE
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import com.example.casa.xperto.constantes.Constantes;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = Constantes.NOMBRE_TABLA_PARTIDO,
        foreignKeys = {
        @ForeignKey(
                entity = Equipo.class,
                parentColumns = "id",
                childColumns = "rival_1_fk",
                onDelete = CASCADE
        ),
        @ForeignKey(
                entity = Equipo.class,
                parentColumns = "id",
                childColumns = "rival_2_fk",
                onDelete = CASCADE
        )
        })
public class Partido {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "rival_1_fk")
    private int rival_1_fk;

    @ColumnInfo(name = "rival_2_fk")
    private int rival_2_fk;

    @ColumnInfo(name = "goles")
    private String goles;

    @ColumnInfo(name = "ganador")
    private String ganador;

    @ColumnInfo(name = "fecha")
    private Date fecha;

    @ColumnInfo(name = "clasificacion")
    private String clasificacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRival_1_fk() {
        return rival_1_fk;
    }

    public void setRival_1_fk(int rival_1_fk) {
        this.rival_1_fk = rival_1_fk;
    }

    public int getRival_2_fk() {
        return rival_2_fk;
    }

    public void setRival_2_fk(int rival_2_fk) {
        this.rival_2_fk = rival_2_fk;
    }

    public String getGoles() {
        return goles;
    }

    public void setGoles(String goles) {
        this.goles = goles;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
