package com.rutassa.tipoVehiculo;
import com.rutassa.Vehiculo;

/**
 * Clase Minibus con el atributo tieneBodega y aireAcondicionado.
 * Esta clase hereda de {@link Vehiculo}.
 * @author MaximoRetamoso
 * @version 1.0
 */
public class Minibus extends Vehiculo{
    private boolean tieneBodega;
    private boolean aireAcondicionado;

    /**
     * Constructor por defecto de Minibus
     * @param patente La patente por defecto del minibus
     * @param capacidad La capacidad por defecto del minibus
     * @param tieneBodega La bodega por defecto del minibus
     * @param aireAcondicionado El aire acondicionado por defecto del minibus
     */
    public Minibus() {
        super("", 0);
        this.tieneBodega = false;
        this.aireAcondicionado = false;
    }

    /**
     * constructor parametrizado de Minibus.
     * @param patente La patente del minibus
     * @param capacidad La capacidad del minibus
     * @param tieneBodega La bodega del minibus
     * @param aireAcondicionado El aire acondicionado del minibus
     */
    public Minibus(String patente, int capacidad, boolean tieneBodega, boolean aireAcondicionado) {
        super(patente, capacidad);
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
}