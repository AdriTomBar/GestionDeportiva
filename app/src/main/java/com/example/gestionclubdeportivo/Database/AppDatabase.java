// app/src/main/java/com/example/gestionclubdeportivo/Database/AppDatabase.java
package com.example.gestionclubdeportivo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gestionclubdeportivo.models.Equipo;
import com.example.gestionclubdeportivo.models.Jugador;
import com.example.gestionclubdeportivo.DAOs.EquipoDao;
import com.example.gestionclubdeportivo.DAOs.DaoJugador;

@Database(entities = {Equipo.class, Jugador.class}, version = 9)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract EquipoDao equipoDao();
    public abstract DaoJugador jugadorDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "gestionclubdeportivo")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}