package com.example.gestionclubdeportivo.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.gestionclubdeportivo.models.Jugador;

import java.util.List;

@Dao
public interface DaoJugador {
    @Insert
    void insert(Jugador jugador);

    @Query("SELECT * FROM jugadores")
    List<Jugador> getAllJugadores();

    @Query("SELECT * FROM jugadores WHERE equipo = :equipoId")
    List<Jugador> getJugadoresByEquipoId(String equipoId);

    @Query("SELECT * FROM jugadores WHERE equipo IS NULL")
    List<Jugador> getJugadoresSinEquipo();

    @Query("SELECT * FROM jugadores WHERE equipo = :equipo AND posicion = :posicion")
    List<Jugador> getJugadoresByEquipoIdAndPosicion(String equipo, String posicion);

    @Update
    void update(Jugador jugador);

    @Delete
    void delete(Jugador jugador);

    @Query("SELECT * FROM jugadores WHERE posicion = :posicion")
    List<Jugador> getJugadoresByPosicion(String posicion);

    @Query("SELECT * FROM jugadores WHERE equipo = :equipoId")
    List<Jugador> getJugadoresByEquipo(int equipoId);

    @Query("SELECT * FROM jugadores WHERE posicion = :posicion AND equipo = :equipoId")
    List<Jugador> getJugadoresByPosicionAndEquipo(String posicion, int equipoId);

    @Query("SELECT * FROM jugadores WHERE id = :jugadorId LIMIT 1")
    Jugador getJugadorById(int jugadorId);
}