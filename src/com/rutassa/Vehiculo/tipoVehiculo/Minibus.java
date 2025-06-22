package com.rutassa.Vehiculo.tipoVehiculo;
import java.util.List;

import com.rutassa.Vehiculo.Vehiculo;
import com.rutassa.Viaje.Viaje;

/**
 * Clase Minibus con el atributo tieneBodega y aireAcondicionado.
 * Esta clase hereda de {@link Vehiculo}.
 * @author Gonzalez Paz, Retamoso Maximo, Zanandrea Martin
 * @version 1.0
 */
public class Minibus extends Vehiculo{
    private boolean tieneBodega;
    private boolean aireAcondicionado;

    /**
     * Constructor por defecto de Minibus.
     * Inicializa la patente como cadena vacia, la capacidad en 0, vehiculoViajes como null, y tieneBodega y aireAcondicionado como false.
     */
    public Minibus() {
        super("", 0, null);
        this.tieneBodega = false;
        this.aireAcondicionado = false;
    }

    /**
     * constructor parametrizado de Minibus.
     * @param patente La patente del minibus
     * @param capacidad La capacidad del minibus
     * @param vehiculoViajes Los viajes del minibus
     * @param tieneBodega La bodega del minibus
     * @param aireAcondicionado El aire acondicionado del minibus
     */
    public Minibus(String patente, int capacidad, List<Viaje> vehiculoViajes, boolean tieneBodega, boolean aireAcondicionado) {
        super(patente, capacidad, vehiculoViajes);
        this.tieneBodega = tieneBodega;
        this.aireAcondicionado = aireAcondicionado;
    }

    /**
     * Getter que devuelve si el colectivo posee bodega.
     * @return tieneBodega
     */
    public boolean getTieneBodega() {
        return tieneBodega;
    }

    /**
     * Setter que establece la bodega del minibus.
     * @param tieneBodega El nuevo tieneBodega
     */
    public void setTieneBodega(boolean tieneBodega) {
        this.tieneBodega = tieneBodega;
    }

    /**
     * Getter que devuelve si el colectivo posee aire acondicionado.
     * @return aireAcondicionado
     */
    public boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    /**
     * Setter que establece el aire acondicionado del colectivo.
     * @param aireAcondicionado El nuevo aireAcondicionado
     */
    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    @Override
    public String toString() {
        return "[Tiene bodega: " + tieneBodega + ", Aire condicionado: " + aireAcondicionado + "]";
    }   
}