package com.rutassa;

/**
 * Clase Ciudad con atributos nombre y provincia.
 * Cada ciudad puede tener de origen o destino de varios {@link Viaje}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class Ciudad {
    private String nombre;
    private Provincia provincia;

    /**
     * Constructor por defecto de Ciudad.
     * Inicializa nombre como una cadena vacia, y provincia como null.
     */
    public Ciudad(){
        this.nombre="";
        this.provincia = null;
    }

    /**
     * Constructor parametrizado de Viaje.
     * @param nombre El nombre de la ciudad
     * @param provincia La provincia
     */
    public Ciudad(String nombre, Provincia provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
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
     * Metodo toString() que retorna un string de la clase Ciudad.
     * @return String
     */
    @Override
    public String toString() {
        return "[Nombre: " + nombre + ", Provincia: " + provincia + "]";
    }
}