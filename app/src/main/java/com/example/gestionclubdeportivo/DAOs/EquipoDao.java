// app/src/main/java/com/example/gestionclubdeportivo/DAOs/EquipoDao.java
package com.example.gestionclubdeportivo.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gestionclubdeportivo.models.Equipo;

import java.util.List;

@Dao
public interface EquipoDao {
    @Query("SELECT * FROM equipos")
    List<Equipo> getAllEquipos();

    @Insert
    void insertAll(Equipo... equipos);

    @Query("SELECT * FROM equipos WHERE id = :equipo")
    Equipo getEquipoById(int equipo);

    @Query("SELECT * FROM equipos WHERE nombre = :nombre")
    Equipo getEquipoByNombre(String nombre);
}