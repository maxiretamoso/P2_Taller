package com.rutassa;

/**
 * Clase Categoria con el atributo tipo.
 * 1 Categoria puede tener muchos {@link ChoferCategoria}.
 * @author MaximoRetamoso, MartinZanandrea
 */
public class Categoria{
    private CategoriaTipo tipo;

    /**
     * Constructor por defecto de Categoria.
     * Inicializa el tipo de categoria con valor null.
     */
    public Categoria() {
        this.tipo = null;
    }

    /**
     * Constructor parametrizado de Categoria.
     * @param tipo El tipo de categoria 
     */
    public Categoria(CategoriaTipo tipo) {
        this.tipo = tipo;
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
}
