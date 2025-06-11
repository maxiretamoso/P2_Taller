package com.rutassa.tipoVehiculo;
import com.rutassa.Vehiculo;

/**
 * Clase Colectivo con el atributo pisoDoble.
 * Esta clase hereda de {@link Vehiculo}.
 * @author MaximoRetamoso
 * @version 1.0
 */
public class Colectivo extends Vehiculo {
    private boolean pisoDoble;

    /**
     * Constructor por defecto de Colectivo.
     * @param patente La patente por defecto del colectivo
     * @param capacidad La capacidad por defecto del colectivo
     * @param pisoDoble El piso doble por defecto del colectivo
     */
    public Colectivo() {
        super("", 0);
        this.pisoDoble = false;
    }

    /**
     * Constructor parametrizado de Colectivo.
     * @param patente La patente del colectivo
     * @param capacidad La capacidad del colectivo
     * @param pisoDoble El piso doble del colectivo
     */
    public Colectivo(String patente, int capacidad, boolean pisoDoble) {
        super(patente, capacidad);
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
}