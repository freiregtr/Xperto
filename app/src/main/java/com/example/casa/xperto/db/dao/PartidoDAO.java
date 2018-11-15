package com.example.casa.xperto.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.casa.xperto.db.entity.Partido;

import java.util.List;

@Dao
public interface PartidoDAO {
    // query para insertar registros
    @Insert
    void insert(Partido partido);

    // listar jugadores
    @Query("SELECT * FROM partido")
    List<Partido> listarPartidos();

}
