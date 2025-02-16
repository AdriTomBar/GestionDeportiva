package com.example.gestionclubdeportivo.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.annotation.NonNull;

@Entity(tableName = "jugadores")
public class Jugador {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @ColumnInfo(name = "sexo")
    private String sexo;

    @ColumnInfo(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @ColumnInfo(name = "altura")
    private float altura;

    @ColumnInfo(name = "posicion")
    private String posicion;

    @ColumnInfo(name = "equipo")
    private int equipo;

    @ColumnInfo(name = "imagen")
    private byte[] imagen;

    private String equipoNombre;

    public String getEquipoNombre() {
        return equipoNombre;
    }

    public void setEquipoNombre(String equipoNombre) {
        this.equipoNombre = equipoNombre;
    }

    // Constructor, getters, and setters
        public Jugador(String nombre, String apellidos, String sexo, String fechaNacimiento, float altura, String posicion, int equipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.altura = altura;
        this.posicion = posicion;
        this.equipo = equipo;
        this.imagen = null;
        }



    // Getters and setters...


    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipoId) {
        this.equipo = equipoId;
    }



}