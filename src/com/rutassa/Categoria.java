package com.rutassa;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Categoria con el atributo tipo, y categoriasChoferes .
 * 1 o mas categorias puede tener varios {@link ChoferCategoria}.
 * @author Gonzalez Paz, Retamoso Maximo, Zanandrea Martin,
 */
public class Categoria {
    private CategoriaTipo tipo;
    private List<Chofer> categoriasChoferes;

    /**
     * Constructor por defecto de Categoria.
     * Inicializa el tipo de categoria con valor null, e instancia la lista de categoriasChoferes
     */
    public Categoria() {
        this.tipo = null;
        categoriasChoferes = new ArrayList<>();
    }

    /**
     * Constructor parametrizado de Categoria.
     * @param tipo El tipo de categoria 
     * @param categoriasChoferes Las categorias de los choferes
     */
    public Categoria(CategoriaTipo tipo, List<Chofer> categoriasChoferes) {
        this.tipo = tipo;
        this.categoriasChoferes = categoriasChoferes;
    }

    /**
     * Getter que devuelve el tipo de categoria.
     * @return tipo
     */
    public CategoriaTipo getTipo() {
        return tipo;
    }

    /**
     * Setter que establece el tipo de categoria.
     * @param tipo El nuevo tipo
     */
    public void setTipo(CategoriaTipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Getter que devuelve las categorias de los choferes.
     * @return categoriasChoferes
     */
    public List<Chofer> getCategoriasChoferes() {
        return categoriasChoferes;
    }

    /**
     * Setter que establece las categorias de los choferes.
     * @param categoriasChoferes Las categorias de choferes
     */
    public void setCategoriasChoferes(List<Chofer> categoriasChoferes) {
        this.categoriasChoferes = categoriasChoferes;
    }

    /**
     * Metodo toString() que retorna un string de la clase Categoria.
     * @return String
     */
    @Override
    public String toString() {
        return "[Tipo: " + tipo + ", Categorias choferes: " + categoriasChoferes + "]";
    }
}