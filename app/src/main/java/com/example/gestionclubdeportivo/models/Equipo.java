package com.example.gestionclubdeportivo.models;

public class Equipo {

    private String nombre;
    private String patrocinador;
    private String categoria;
    private String modalidad;
    private boolean federado;
    private int imagen; // Referencia a drawable
    private String diaPartido;
    private String horaPartido;
    private String entrenamientos;
    private String contacto;
    private String telefono;

    public Equipo(String nombre, String patrocinador, String categoria, String modalidad, boolean federado,
                  int imagen, String diaPartido, String horaPartido, String entrenamientos, String contacto, String telefono) {
        this.nombre = nombre;
        this.patrocinador = patrocinador;
        this.categoria = categoria;
        this.modalidad = modalidad;
        this.federado = federado;
        this.imagen = imagen;
        this.diaPartido = diaPartido;
        this.horaPartido = horaPartido;
        this.entrenamientos = entrenamientos;
        this.contacto = contacto;
        this.telefono = telefono;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getModalidad() {
        return modalidad;
    }

    public boolean isFederado() {
        return federado;
    }

    public int getImagen() {
        return imagen;
    }

    public String getDiaPartido() {
        return diaPartido;
    }

    public String getHoraPartido() {
        return horaPartido;
    }

    public String getEntrenamientos() {
        return entrenamientos;
    }

    public String getContacto() {
        return contacto;
    }

    public String getTelefono() {
        return telefono;
    }

    // Setters (si es necesario para modificaciones)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public void setFederado(boolean federado) {
        this.federado = federado;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setDiaPartido(String diaPartido) {
        this.diaPartido = diaPartido;
    }

    public void setHoraPartido(String horaPartido) {
        this.horaPartido = horaPartido;
    }

    public void setEntrenamientos(String entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
