package com.example.casa.xperto.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.example.casa.xperto.db.entity.Partido;

@Dao
public interface PartidoDAO {
    // query para insertar registros
    @Insert
    void insert(Partido partido);
}
