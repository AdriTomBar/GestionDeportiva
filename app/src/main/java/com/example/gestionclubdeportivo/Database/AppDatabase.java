// app/src/main/java/com/example/gestionclubdeportivo/Database/AppDatabase.java
package com.example.gestionclubdeportivo.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gestionclubdeportivo.models.Equipo;
import com.example.gestionclubdeportivo.models.Jugador;
import com.example.gestionclubdeportivo.DAOs.EquipoDao;
import com.example.gestionclubdeportivo.DAOs.DaoJugador;

@Database(entities = {Equipo.class, Jugador.class}, version = 8) // Increase the version number
public abstract class AppDatabase extends RoomDatabase {
    public abstract EquipoDao equipoDao();
    public abstract DaoJugador jugadorDao();

}