package com.rutassa;

/**
 * Clase Vehiculo con atributos patente y capacidad.
 * Es la clase padre de {@link Colectivo} y {@link Minibus}.
 * 1 vehiculo puede tener varios {@link Viaje}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class Vehiculo {
    private String patente;
    private int capacidad;

    /**
     * Constructor por defecto de Vehiculo.
     * Inicializa patente como una cadena vacia, y capacidad en 0.
     */
    public Vehiculo() {
        this.patente="";
        this.capacidad=0;
    }

    /**
     * Constructor parametrizado de Vehiculo.
     * @param patente La patente del vehiculo
     * @param capacidad La capacidad del vehiculo 
     */
    public Vehiculo(String patente, int capacidad) {
        this.patente = patente;
        this.capacidad = capacidad;
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
     * Metodo toString() que retorna un string de la clase Vehiculo.
     * @return String
     */
    @Override
    public String toString() {
        return "[Patente: " + patente + ", Capacidad: " + capacidad + "]";
    }
}