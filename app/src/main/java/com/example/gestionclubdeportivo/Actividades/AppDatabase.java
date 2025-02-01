package com.example.gestionclubdeportivo.Actividades;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.gestionclubdeportivo.DAOs.DaoJugador;
import com.example.gestionclubdeportivo.models.Jugador;

@Database(entities = {Jugador.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoJugador jugadorDao();
}
