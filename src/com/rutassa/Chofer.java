package com.rutassa;
import java.util.List;
import java.util.ArrayList;


/**
 * Clase Chofer con atributos nroLicencia y categoria.
 * Esta clase hereda de {@link Persona}.
 * 1 chofer puede tener varios {@link Viaje}.
 * 1 chofer tiene 1 {@link CategoriaTipo}.
 * 1 chofer puede tener varias {@link ChoferCategoria}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class Chofer extends Persona{
    private String nroLicencia;
    private List<ChoferCategoria> categorias;

    /**
     * Constructor por defecto de Chofer.
     * Inicializa dni, nombre, apellido, y nroLicencia como una cadena vacia, y se instancia la lista de categorias.
     */
    public Chofer() {
        super(0, "", "");
        this.nroLicencia = "";
        categorias = new ArrayList<>();
    }

    /**
     * Constructor parametrizado de Chofer.
     * @param dni El dni del chofer
     * @param nombre El nombre del chofer
     * @param apellido El apellido del chofer
     * @param nroLicencia El numero de licencia del chofer
     * @param categorias Las categorias del chofer
     */
    public Chofer(long dni, String nombre, String apellido, String nroLicencia, List<ChoferCategoria> categorias){
        super(dni, nombre, apellido);
        this.nroLicencia = nroLicencia;
        this.categorias = categorias;
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
     * @return categorias
     */
    public List<ChoferCategoria> getCategorias() {
        return categorias;
    }
    
    /**
     * Setter que establece la categoria del chofer.
     * @param categorias Las nuevas categorias
     */
    public void setCategorias(List<ChoferCategoria> categorias) {
        this.categorias = categorias;
    }
    
    /**
     * Metodo toString() que retorna un string de la clase Chofer.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + super.toString() + ", Nº de Licencia: " + nroLicencia + ", Categoría: " + categorias + "]";
    }
}