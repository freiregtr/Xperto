package com.example.casa.xperto.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.casa.xperto.db.entity.Jugador;

import java.util.List;

@Dao
public interface JugadorDAO {
    @Insert
    void insert(Jugador jugador);

    @Query("SELECT * FROM jugador WHERE equipoId = :equipoId")
    List<Jugador> buscarJugadorPorEquipo(int equipoId);

    @Update
    void actualizarJugadorPorId(Jugador jugador);

    @Delete
    void borrarJugadorPorId(Jugador jugador);
}
