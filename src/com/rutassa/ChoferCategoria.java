package com.rutassa;

/**
 * Clase ChoferCategoria con el atributo fechaVencimiento y categoria.
 * Cada ChoferCategoria tiene 1 {@link Categoria}.
 * @author Gonzalez Paz, Retamoso Maximo, Zanandrea Martin
 * @version 1.0
 */
public class ChoferCategoria {
    private String fechaVencimiento;
    private Categoria categoria;

    /**
     * Constructor por defecto de ChoferCategoria.
     * Inicializa fechaVencimiento como una cadena vacia, y categoria como null.
     */
    public ChoferCategoria(){
        this.fechaVencimiento = "";
        this.categoria = null;
    }

    /**
     * Constructor parametrizado de ChoferCategoria.
     * @param fechaVencimiento La fecha de vencimiento de la categoria del chofer
     * @param categoria La categoria del chofer
     */
    public ChoferCategoria(String fechaVencimiento, Categoria categoria) {
        this.fechaVencimiento = fechaVencimiento;
        this.categoria = categoria;
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
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Setter que establece la categoria del chofer.
     * @param categoria la nueva categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo toString() que retorna un string de la clase ChoferCategoria.
     * @return String
     */
    @Override
    public String toString() {
        return "[Fecha de vencimiento: " + fechaVencimiento + ", Categoria: " + categoria + "]";
    }

    /**
     * Metodo estaVencida() para verificar si la categoria vencio comparando la fecha de vencimiento con la actual
     * @return true si la fecha es null, vacia, o si la fecha de vencimiento es menor (mas vieja) a la fecha actual
     * @return false si no tiene un tipo, ya que no es una licencia valida, o si la fecha es mayor (mas reciente) a la fecha actual
     */
    public boolean estaVencida() {
        //Obtenemos la fecha actual que la aplicación está usando para simular la fecha actual.
        String fechaActual = GestionSistema.getFechaActualSimulada();

        if (fechaVencimiento == null || fechaVencimiento.isEmpty()) {
            return true;
        }
        if (categoria == null) {
            return true; 
        }

        // Comparamos las fechas como si fueran palabras para ver cual va antes en el calendario.
        return this.fechaVencimiento.compareTo(fechaActual) < 0;
    }
}   