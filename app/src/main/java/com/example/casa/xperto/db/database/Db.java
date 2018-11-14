package com.example.casa.xperto.db.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.casa.xperto.constantes.Constantes;
import com.example.casa.xperto.db.dao.EquipoDAO;
import com.example.casa.xperto.db.dao.JugadorDAO;
import com.example.casa.xperto.db.dao.PartidoDAO;
import com.example.casa.xperto.db.entity.Equipo;
import com.example.casa.xperto.db.entity.Jugador;
import com.example.casa.xperto.db.entity.Partido;

// anotación para convertir fecha
@TypeConverters(Converter.class)
@Database(entities = {Equipo.class, Jugador.class, Partido.class}, version = 3)
public abstract class Db extends RoomDatabase {

    private static Db INSTANCIA;
    // instancias de los DAO
    public abstract EquipoDAO equipoDao();
    public abstract JugadorDAO jugadorDAO();
    public abstract PartidoDAO partidoDAO();

    public static Db obtenerDb(Context context){
        if(INSTANCIA == null){
            INSTANCIA = Room.databaseBuilder(context.getApplicationContext(), Db.class, Constantes.NOMBRE_DATABASE)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRACION_I_2)
                    .build();
        }
        return INSTANCIA;
    }

    // metodo para destruir instancia
    public static void destruirInstancia(){
        INSTANCIA = null;
    }

    // migración de 2 a 3
    static final Migration MIGRACION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE partido (id INTEGER PRIMARY KEY NOT NULL, nombre TEXT, " +
                    "goles TEXT, ganador TEXT, fecha TEXT, ganador DATE, clasificacion TEXT, rival_1_fk INTEGER NOT NULL, foreign key (rival_1_fk)" +
                    " references equipo(id) ON DELETE CASCADE, foreign key (rival_2_fk) references (equipo)id ON DELETE CASCADE");
        }
    };

    // migración de 1 a 2
    static final Migration MIGRACION_I_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE jugador (id INTEGER PRIMARY KEY NOT NULL, rut TEXT, " +
                    "nombre TEXT, posicion TEXT, equipoId INTEGER NOT NULL, foreign key (equipoId)" +
                    " references equipo(id) ON DELETE CASCADE)");
        }
    };
}
