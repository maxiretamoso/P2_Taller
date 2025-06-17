package com.rutassa;
import java.util.ArrayList;
import java.util.List;

import com.rutassa.tipoVehiculo.Colectivo;
import com.rutassa.tipoVehiculo.Minibus;

/**
 * Clase Vehiculo con atributos patente, capacidad y vehiculoViajes.
 * Es la clase padre de {@link Colectivo} y {@link Minibus}.
 * 1 vehiculo puede tener varios {@link Viaje}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class Vehiculo {
    private String patente;
    private int capacidad;
    private List<Viaje> vehiculoViajes;

    /**
     * Constructor por defecto de Vehiculo.
     * Inicializa patente como una cadena vacia, y capacidad en 0, e instancia VehiculoViajes.
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
     * @param vehiculoViajes El Vehiculo de viajes
     */
    public Vehiculo(String patente, int capacidad, List<Viaje> vehiculoViajes) {
        this.patente = patente;
        this.capacidad = capacidad;
        this.vehiculoViajes = vehiculoViajes;
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
     * Getter que devuelve el vehiculo de viajes.
     * @return vehiculoViajes
     */
    public List<Viaje> getVehiculoViajes() {
        return vehiculoViajes;
    }

    /**
     * Setter que establece el vehiculo de viajes.
     * @param vehiculoViajes El nuevo vehiculoViajes
     */
    public void setVehiculoViajes(Viaje vehiculo) {
        this.vehiculoViajes.add(vehiculo);
    }

    /**
     * Metodo toString() que retorna un string de la clase Vehiculo.
     * @return String
     */
    @Override
    public String toString() {
        return "[Patente: " + patente + ", Capacidad: " + capacidad + "vehiculo viajes: " + vehiculoViajes + "]";
    }


}