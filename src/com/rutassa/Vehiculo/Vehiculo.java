package com.rutassa.Vehiculo;
import java.util.ArrayList;
import java.util.List;

import com.rutassa.Vehiculo.tipoVehiculo.Colectivo;
import com.rutassa.Vehiculo.tipoVehiculo.Minibus;
import com.rutassa.Viaje.Viaje;

/**
 * Clase Vehiculo con atributos patente, capacidad y vehiculoViajes.
 * Es la clase padre de {@link Colectivo} y {@link Minibus}.
 * 1 vehiculo puede tener varios {@link Viaje}.
 * @author Gonzalez Paz, Retamoso Maximo, Zanandrea Martin
 * @version 1.0
 */
public abstract class Vehiculo {
    private String patente;
    private int capacidad;
    private List<Viaje> vehiculoViajes;

    /**
     * Constructor por defecto de Vehiculo.
     * Inicializa patente como una cadena vacia, y capacidad en 0, e instancia vehiculoViajes.
     */
    public Vehiculo() {
        this.patente="";
        this.capacidad=0;
        vehiculoViajes = new ArrayList<>();
    }

    /**
     * Constructor parametrizado de Vehiculo.
     * @param patente La patente del vehiculo
     * @param capacidad La capacidad del vehiculo 
     * @param vehiculoViajes El Vehiculo del viaje/s
     */
    public Vehiculo(String patente, int capacidad, List<Viaje> vehiculoViajes) {
        this.patente = patente;
        this.capacidad = capacidad;
        this.vehiculoViajes = (vehiculoViajes != null) ? vehiculoViajes : new ArrayList<>();
    }

    /**
     * Getter que devuelve la patente del vehiculo.
     * @return patente
     */
    public String getPatente() {
        return patente;
    }

    /**
     * Setter que establece la patente del vehiculo.
     * @param patente La nueva patente
     */
    public void setPatente(String patente) {
        this.patente = patente;
    }
    
    /**
     * Getter que devuelve la capacidad del vehiculo.
     * @return capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Setter que establece la capacidad del vehiculo.
     * @param capacidad La nueva capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Getter que devuelve el vehiculo del viaje/s.
     * @return vehiculoViajes
     */
    public List<Viaje> getVehiculoViajes() {
        return vehiculoViajes;
    }

    /**
     * Setter que establece el vehiculo del viaje/s.
     * @param viaje El nuevo vehiculoViajes
     */
    public void setVehiculoViajes(Viaje viaje) {
        if (vehiculoViajes == null) {
            vehiculoViajes = new ArrayList<>();
        }
        this.vehiculoViajes.add(viaje);
    }

    /**
     * Metodo toString() que retorna un string de la clase Vehiculo.
     * @return String
     */
    @Override
    public String toString() {
        return "[Patente: " + patente + ", Capacidad: " + capacidad + "Vehiculo viajes: " + vehiculoViajes + "]";
    }
}