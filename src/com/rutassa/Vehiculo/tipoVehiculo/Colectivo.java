package com.rutassa.Vehiculo.tipoVehiculo;
import java.util.List;

import com.rutassa.Vehiculo.Vehiculo;
import com.rutassa.Viaje.Viaje;

/**
 * Clase Colectivo con el atributo pisoDoble.
 * Esta clase hereda de {@link Vehiculo}.
 * @author Gonzalez Paz, Retamoso Maximo, Zanandrea Martin
 * @version 1.0
 */
public class Colectivo extends Vehiculo {
    private boolean pisoDoble;

    /**
     * Constructor por defecto de Colectivo.
     * Inicializa la patente como una cadena vacia, la capacidad en 0, vehiculoViajes como null, y pisoDoble como false.
     */
    public Colectivo() {
        super("", 0, null);
        this.pisoDoble = false;
    }

    /**
     * Constructor parametrizado de Colectivo.
     * @param patente La patente del colectivo
     * @param capacidad La capacidad del colectivo
     * @param pisoDoble El piso doble del colectivo
     */
    public Colectivo(String patente, int capacidad, List<Viaje> vehiculoViajes, boolean pisoDoble) {
        super(patente, capacidad, vehiculoViajes);
        this.pisoDoble = pisoDoble;
    }

    /**
     * Getter que devuelve si el colectivo posee piso doble.
     * @return pisoDoble
     */
    public boolean getPisoDoble() {
        return pisoDoble;
    }

    /**
     * Setter que establece el piso doble del colectivo.
     * @param pisoDoble El nuevo pisoDoble
     */
    public void setPisoDoble(boolean pisoDoble) {
        this.pisoDoble = pisoDoble;
    }

    @Override
    public String toString() {
        return "Colectivo [Patente: " + getPatente() + ", Capacidad: " + getCapacidad() + ", Piso doble: " + pisoDoble + "]";
    }
}