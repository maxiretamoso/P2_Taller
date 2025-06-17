package com.rutassa;

/**
 * Clase Viaje con atributos fecha, horarioSalida, horarioLlegada, origen, destino, vehiculo, y chofer.
 * Cada viaje esta a cargo de solo 1 {@link Chofer}.
 * Un viaje tiene 1 {@link Ciudad} de origen y 1 de destino.
 * Cada viaje tiene solo 1 {@link Vehiculo}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class Viaje {
    private String fecha;
    private String horarioSalida;
    private String horarioLlegada;
    private Ciudad origen;
    private Ciudad destino;   
    private Vehiculo vehiculo;  
    private Chofer chofer;    

    /**
     * Constructor por defecto de viaje.
     * Inicializa fecha, horarioSalida, y horarioLlegada como una cadena vacia, y origen y destino como null.
     */
    public Viaje() {
        this.fecha = "";
        this.horarioSalida = "";
        this.horarioLlegada = "";
        this.origen = null;
        this.destino = null;
        this.vehiculo = null;
        this.chofer = null;
    }

    /**
     * Constructor parametrizado de Viaje.
     * @param fecha La fecha del viaje
     * @param horarioSalida El horario de salida del viaje
     * @param horarioLlegada El horario de llegada del viaje 
     * @param origen El origen del viaje
     * @param destino El destino del viaje 
     * @param vehiculo El vehiculo del viaje
     * @param chofer El chofer del viaje
     */
    public Viaje(String fecha, String horarioSalida, String horarioLlegada, Ciudad origen, Ciudad destino, Vehiculo vehiculo, Chofer chofer){
        this.fecha = fecha;
        this.horarioSalida = horarioSalida;
        this.horarioLlegada = horarioLlegada;
        this.origen = origen;
        this.destino = destino;
        this.vehiculo = vehiculo;
        this.chofer = chofer;

    }

    /**
     * Getter que devuelve la fecha del viaje.
     * @return fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Setter que establece la fecha.
     * @param fecha La nueva fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Getter que devuelve el horario de salida del viaje.
     * @return horarioSalida
     */
    public String getHorarioSalida() {
        return horarioSalida;
    }

    /**
     * Setter que establece el horario de salida.
     * @param horarioSalida El nuevo horarioSalida
     */
    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    /**
     * Getter que devuelve el horario de llegada del viaje.
     * @return horarioLlegada
     */
    public String getHorarioLlegada() {
        return horarioLlegada;
    }

    /**
     * Setter que establece el horario de llegada.
     * @param horarioLlegada El nuevo horarioLlegada
     */
    public void setHorarioLlegada(String horarioLlegada) {
        this.horarioLlegada = horarioLlegada;
    }

    /**
     * Getter que devuelve el origen del viaje.
     * @return origen
     */
    public Ciudad getOrigen() {
        return origen;
    }

    /**
     * Setter que establece el origen del viaje.
     * @param origen El nuevo origen
     */
    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }
    
    /**
     * Getter que devuelve el destino del viaje.
     * @return destino
     */
    public Ciudad getDestino() {
        return destino;
    }

    /**
     * Setter que establece el destino del viaje.
     * @param destino El nuevo destino
     */
    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    /**
     * Getter que devuelve el vehiculo del viaje.
     * @return vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Setter que establece el vehiculo del viaje.
     * @param vehiculo El nuevo vehiculo
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo= vehiculo;
    }

    /**
     * Getter que devuelve el chofer del viaje.
     * @return chofer
     */
    public Chofer getChofer() {
        return chofer;
    }

    /**
     * Setter que establece el chofer del viaje.
     * @param chofer El nuevo chofer
     */
    public void setChofer(Chofer chofer) {
        this.chofer= chofer;
    }

    /**
     * Metodo atributosNulos() para saber si los atributos de viaje son o no nulos
     * @return true si al menos uno de los atributos es null (nulo)
     * @return false si todos los atributos no son null (nulo)
     */
    public boolean atributosNulos() {
        return chofer == null || vehiculo == null || fecha == null || horarioSalida == null || horarioLlegada == null || origen == null || destino == null;
    }

    /**
     * Metodo toString() que retorna un string de la clase Viaje.
     * @return String
     */
    @Override
    public String toString() {
        return 
           "  • Fecha: " + fecha + "\n" +
           "  • Horario de salida: " + horarioSalida + "\n" +
           "  • Horario de llegada: " + horarioLlegada + "\n" +
           "  • Origen: " + origen.getNombre() + "\n" +
           "  • Destino: " + destino.getNombre() + "\n" +
           "  • Vehículo(patente): " + vehiculo.getPatente() + "\n" +
           "  • Chofer: " + chofer.getNombre();
    }
    public boolean estaAsignado() { return this.chofer != null && this.vehiculo != null; }
    
}