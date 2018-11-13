package com.example.casa.xperto.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.casa.xperto.db.entity.Equipo;

import java.util.List;

@Dao
public interface EquipoDAO {
    @Insert
    void insertarEquipo(Equipo equipo);

    // Listar equipos
    @Query("SELECT * FROM equipo")
    List<Equipo> listarEquipos();

    // Buscar equipos por id
    @Query("SELECT * FROM equipo WHERE nombre LIKE :id")
    Equipo encontrarEquipoPorId(int id);

    // Buscar equipos por nombre
    @Query("SELECT * FROM equipo WHERE nombre LIKE :nombre")
    Equipo encontrarEquipoPorNombre(String nombre);

    // Actualizar Equipo
    @Update
    void actualizarEquipoPorId(Equipo equipo);

    // Eliminar todos los equipos
    @Query("DELETE FROM equipo")
    void eliminarTodosLosEquipos();

    @Delete
    void eliminarEquipoPorId(Equipo equipo);
}
