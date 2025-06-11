package com.rutassa;

import java.util.List;

/**
 * Clase Chofer con atributos nroLicencia y categoria.
 * Esta clase hereda de {@link Persona}.
 * 1 chofer puede tener varios {@link Viaje}.
 * 1 chofer tiene 1 {@link Categoria}.
 * 1 chofer puede tener varias {@link ChoferCategoria}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class Chofer extends Persona{
    private String nroLicencia;
    private List<ChoferCategoria> categoria;

    /**
     * Constructor por defecto de Chofer.
     * Inicializa dni, nombre, apellido, y nroLicencia como una cadena vacia, y categoria como null.
     */
    public Chofer() {
        super(0, "", "");
        this.nroLicencia = "";
        this.categoria = null;
    }

    /**
     * Constructor parametrizado de Chofer.
     * @param dni El dni del chofer
     * @param nombre El nombre del chofer
     * @param apellido El apellido del chofer
     * @param nroLicencia El numero de licencia del chofer
     * @param categoria La categoria del chofer
     */
    public Chofer(long dni, String nombre, String apellido, String nroLicencia, List<ChoferCategoria> categoria){
        super(dni, nombre, apellido);
        this.nroLicencia = nroLicencia;
        this.categoria = categoria;
    }

    /**
     * Getter que devuelve el numero de licencia del chofer.
     * @return nroLicencia
     */
    public String getNroLicencia() {
        return nroLicencia;
    }

    /**
     * Setter que establece el numero de licencia del chofer.
     * @param nroLicencia El nuevo nroLicencia
     */
    public void setNroLicencia(String nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    /**
     * Getter que devuelve la categoria del chofer.
     * @return categoria
     */
    public ChoferCategoria getCategoria() {
        return (ChoferCategoria) categoria;
    }
    
    /**
     * Setter que establece la categoria del chofer.
     * @param categoria La nueva categoria
     */
    public void setCategoria(List<ChoferCategoria> categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Metodo toString() que retorna un string de la clase Chofer.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + super.toString() + ", Nº de Licencia: " + nroLicencia + ", Categoría: " + categoria + "]";
    }
}