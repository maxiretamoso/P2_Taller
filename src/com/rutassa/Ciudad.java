package com.rutassa;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Ciudad con atributos nombre, provincia, origenViajes, y destinoViajes.
 * Cada ciudad puede tener de origen o destino de varios {@link Viaje}.
 * @author Martin, Maximo, Paz
 * @version 1.0
 */
public class Ciudad {
    private String nombre;
    private Provincia provincia;
    private List<Viaje> origenesViajes;
    private List<Viaje> destinosViajes;

    /**
     * Constructor por defecto de Ciudad.
     * Inicializa nombre como una cadena vacia, provincia como null, e instancia origenesViajes y destinosViajes.
     */
    public Ciudad(){
        this.nombre="";
        this.provincia = null;
        origenesViajes = new ArrayList<>();
        destinosViajes = new ArrayList<>();
    }

    /**
     * Constructor parametrizado de Viaje.
     * @param nombre El nombre de la ciudad
     * @param provincia La provincia
     * @param origenesViajes Los origenes de los viajes
     * @param destinosViajes Los destinos de los viajes
     */
    public Ciudad(String nombre, Provincia provincia, List<Viaje> origenesViajes, List<Viaje> destinosViajes) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.origenesViajes = origenesViajes;
        this.destinosViajes = destinosViajes;
    }

    /**
     * Getter que devuelve el nombre de la ciudad.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter que establece el nombre.
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter que devuelve la provincia.
     * @return provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Setter que establece la provincia.
     * @param provincia La nueva provincia
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    /**
     * Getter que devuelve el origen de los viajes.
     * @return origenesViajes
     */
    public List<Viaje> getOrigenesViajes() {
        return origenesViajes;
    }

    /**
     * Setter que establece los origenes de los viajes.
     * @param origenesViajes Los nuevos origenes de viajes
     */
    public void setOrigenesViajes(Viaje origenesViajes) {
        this.origenesViajes.add(origenesViajes);
    }

    /**
     * Getter que devuelve los destinos de los viajes.
     * @return destinosViajes
     */
    public List<Viaje> getDestinosViajes() {
        return destinosViajes;
    }

    /**
     * Setter que establece el destino de los viajes.
     * @param destinosViajes Los nuevos destinos de viajes
     */
    public void setDestinosViajes(Viaje destinosViajes) {
        this.destinosViajes.add(destinosViajes);
    }

    /**
     * Metodo toString() que retorna un string de la clase Ciudad.
     * @return String
     */
    @Override
    public String toString() {
        return "[Nombre: " + nombre + ", Provincia: " + provincia + ", Origenes viajes: " + origenesViajes + "Destinos viajes: " + destinosViajes + "]";
    }
}