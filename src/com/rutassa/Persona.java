package com.rutassa;

/**
 * Clase Persona con atributos dni, nombre, y apellido.
 * Es la clase padre de {@link Chofer}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public abstract class Persona {
    private long dni;
    private String nombre;
    private String apellido;

    /**
     * Constructor por defecto de Persona.
     * Inicializa dni en 0, nombre y apellido como una cadena vacia.
     */
    public Persona() {
        this.dni = 0;
        this.nombre = "";
        this.apellido = "";
    }

    /**
     * Constructor parametrizado de Persona.
     * @param dni El dni de la persona
     * @param nombre El nombre de la persona
     * @param apellido El apellido de la persona
     */
    public Persona(long dni, String nombre, String apellido){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /** 
     * Getter que devuelve el dni de la persona.
     * @return dni 
     */
    public long getDni() {
        return dni;
    }

    /**
     * Setter que establece el dni de la persona.
     * @param dni El nuevo dni
     */
    public void setDni(long dni) {
        this.dni = dni;
    }
    
    /** 
     * Getter que devuelve el nombre de la persona. 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter que establece el nombre de la persona.
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** 
     * Getter que devuelve el apellido de la persona.     
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * Setter que establece el apellido de la persona. 
     * @param apellido El nuevo apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Metodo toString() que retorna un string de la clase Persona.
     * @return String
     */
    @Override
    public String toString() {
        return "DNI: " + dni + ", Nombre: " + nombre + ", Apellido: " + apellido;
    }
}