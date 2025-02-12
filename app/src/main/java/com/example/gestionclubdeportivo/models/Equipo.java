package com.example.gestionclubdeportivo.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "equipos")
public class Equipo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "patrocinador")
    private String patrocinador;

    @ColumnInfo(name = "categoria")
    private String categoria;

    @ColumnInfo(name = "modalidad")
    private String modalidad;

    @ColumnInfo(name = "federado")
    private boolean federado;

    @ColumnInfo(name = "imagen")
    private byte[] imagen;

    @ColumnInfo(name = "persona_contacto")
    private String personaContacto;

    @ColumnInfo(name = "numero_contacto")
    private String numeroContacto;

    @ColumnInfo(name = "dia_partido")
    private String diaPartido;  // Nuevo campo

    @ColumnInfo(name = "hora_partido")
    private String horaPartido;  // Nuevo campo

    @ColumnInfo(name = "entrenamientos")
    private String entrenamientos;  // Nuevo campo

    public Equipo(String nombre, String patrocinador, String categoria, String modalidad, boolean federado, byte[] imagen,
                  String personaContacto, String numeroContacto, String diaPartido, String horaPartido, String entrenamientos) {
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.federado = federado;
        this.imagen = imagen;
        this.personaContacto = personaContacto;
        this.numeroContacto = numeroContacto;
        this.diaPartido = diaPartido;
        this.horaPartido = horaPartido;
        this.entrenamientos = entrenamientos;
    }

    // Getters y setters para todos los atributos


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public boolean isFederado() {
        return federado;
    }

    public void setFederado(boolean federado) {
        this.federado = federado;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getDiaPartido() {
        return diaPartido;
    }

    public void setDiaPartido(String diaPartido) {
        this.diaPartido = diaPartido;
    }

    public String getHoraPartido() {
        return horaPartido;
    }

    public void setHoraPartido(String horaPartido) {
        this.horaPartido = horaPartido;
    }

    public String getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(String entrenamientos) {
        this.entrenamientos = entrenamientos;
    }
}
