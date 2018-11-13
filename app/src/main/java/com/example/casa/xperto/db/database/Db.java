package com.example.casa.xperto.db.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.casa.xperto.constantes.Constantes;
import com.example.casa.xperto.db.dao.EquipoDAO;
import com.example.casa.xperto.db.dao.JugadorDAO;
import com.example.casa.xperto.db.entity.Equipo;
import com.example.casa.xperto.db.entity.Jugador;

@Database(entities = {Equipo.class, Jugador.class}, version = 2)
public abstract class Db extends RoomDatabase {

    private static Db INSTANCIA;
    public abstract EquipoDAO equipoDao();
    public abstract JugadorDAO equipoDAO();

    public static Db obtenerDb(Context context){
        if(INSTANCIA == null){
            INSTANCIA = Room.databaseBuilder(context.getApplicationContext(), Db.class, Constantes.NOMBRE_DATABASE)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCIA;
    }

    public static void destruirInstancia(){
        INSTANCIA = null;
    }

    // migración de la BBDD cuando sube de versión
    static final Migration MIGRACION_I_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ");
        }
    };
}
