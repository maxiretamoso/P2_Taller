package com.rutassa;

/**
 * Clase ChoferCategoria con el atributo fechaVencimiento y categoria.
 * Cada ChoferCategoria tiene 1 {@link CategoriaTipo}.
 * @author MaximoRetamoso, MartinZanandrea
 * @version 1.0
 */
public class ChoferCategoria {
    private String fechaVencimiento;
    private CategoriaTipo categorias;

    /**
     * Constructor por defecto de ChoferCategoria.
     * Inicializa fechaVencimiento como una cadena vacia, y categoria como null.
     */
    public ChoferCategoria(){
        this.fechaVencimiento = "";
        this.categorias = null;
    }

    /**
     * Constructor parametrizado de ChoferCategoria.
     * @param fechaVencimiento La fecha de vencimiento de la categoria del chofer
     * @param categoria La categoria del chofer
     */
    public ChoferCategoria(String fechaVencimiento,CategoriaTipo categoria) {
        this.fechaVencimiento = fechaVencimiento;
        this.categorias=categoria;
    }
    
    /**
     * Getter que devuelve la fecha de vencimiento de la categoria del chofer.
     * @return fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Setter que establece la fecha de vencimiento de la categoria del chofer.
     * @param fechaVencimiento la nueva fechaVencimiento
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Getter que devuelve la categoria del chofer.
     * @return categoria
     */
    public CategoriaTipo getCategoria() {
        return categorias;
    }

    /**
     * Setter que establece la categoria del chofer.
     * @param categoria la nueva categoria
     */
    public void setCategoria(CategoriaTipo categoria) {
        this.categorias = categoria;
    }

    /**
     * Metodo toString() que retorna un string de la clase ChoferCategoria.
     * @return String
     */
    @Override
    public String toString() {
        return "[FechaVencimiento: " + fechaVencimiento + ", Categoria: " + categorias + "]";
    }
}