package com.rutassa;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.rutassa.tipoVehiculo.Colectivo;
import com.rutassa.tipoVehiculo.Minibus;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase GestionSistema que lleva a cabo todos los metodos de gestion de rutassa
 */
public class GestionSistema {
    private List<Chofer> choferes;
    private List<Vehiculo> vehiculos;
    private List<Viaje> viajes;
    private List<Ciudad> ciudades;
    private Scanner sc;
    /**
     * Esta es la fecha que usamos como "hoy" para probar.
     * Nos sirve para ver si las licencias están vencidas o si los viajes ya pasaron.
     */
    public static final String FECHA_ACTUAL_SIMULADA = "12/06/25";

    /**
     * Nos da la fecha de "hoy" que estamos usando para simular.
     * Es la misma fecha que está definida arriba.
     *
     * @return La fecha simulada en formato "DD/MM/AA".
     */
    public static String getFechaActualSimulada() {
        return FECHA_ACTUAL_SIMULADA;
    }
    
    
    
    
    public GestionSistema() {
        this.choferes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.viajes = new ArrayList<>();
        this.ciudades = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    /** 
     * metodo main para correr el programa rutassa.
     */
    public static void main(String[] args) {
        GestionSistema sistema = new GestionSistema();

        
        boolean salir = false;
        while (!salir) {
            System.out.println("\n" + "-".repeat(45) + "\n\tSistema de gestion Rutas SA\n" + "-".repeat(45));
            System.out.println("Elija una opcion: ");            
            System.out.println("0. Para salir ingrese 0");            
            System.out.println("1. Registrar choferes");
            System.out.println("2. Registrar vehiculos");
            System.out.println("3. Registrar ciudades");
            System.out.println("4. Planificar viajes entre ciudades");
            System.out.println("5. Asociar un vehiculo y un chofer a cada viaje");
            System.out.println("6. Mostrar los viajes programados con información detallada");
            System.out.println("7. Informe detallado de viajes que tiene para realizar un colectivo determinado");
            System.out.println("8. Informe de cantidad de viajes ya realizados por cada chofer de colectivos");
            
            int opcion = -1;
            boolean opcionValida = false;

            while (!opcionValida) {
                System.out.print("\nSeleccione una opcion: ");
                if (sistema.sc.hasNextInt()) {
                    opcion = sistema.sc.nextInt();
                    sistema.sc.nextLine();

                    if (opcion >= 0 && opcion <= 8) {
                        opcionValida = true;
                    } else {
                        System.out.println("Opcion invalida. Por favor ingrese un numero entre 0 y 7. Vuelva a intentar.");
                    }
                } else {
                    System.out.println("Ingreso invalido. debe ser un numero.");
                    sistema.sc.nextLine(); 
                }
            }

            switch (opcion) {
                case 0: 
                    salir = true;
                    System.out.println("\n" + "-".repeat(45) + "\n\tSaliendo del sistema de gestion\n" + "-".repeat(45));
                    break;
                case 1:
                    sistema.registrarChoferes(); //añadir a la lista de choferes un chofer
                    break;
                case 2:
                    sistema.registrarVehiculos();
                    break;
                case 4:
                    sistema.planificarViajes(); //array de viajes
                    break;
                case 3:
                    sistema.registrarCiudades();
                    break;
                case 5:
                    sistema.asociarVehiculoYChofer();
                    break;
                case 6:
                    sistema.viajesProgramados();
                    break;
                case 7:
                    sistema.informeViajesARealizarColectivo();
                    break;
                case 8:
                    sistema.informeViajesRealizadosColectivo();
                    break;
            }
        }
        sistema.sc.close();
    }
    
    /**
     * Metodo para registrar los choferes.
     */
    public void registrarChoferes() {
        System.out.println("\n" + "-".repeat(45) + "\nRegistrar choferes\n" + "-".repeat(45));
        if (!choferes.isEmpty()) {
            System.out.println("Ya hay choferes registrados.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar más choferes (1), limpiar y empezar de nuevo (2) o volver al menu (0)? ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 
                    if (opcion == 0) {
                        break;
                    } else if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'2'/'0'. Vuelva a intentar.");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                choferes.clear();
                System.out.println("Choferes anteriores eliminados.");
            }
            if (opcion == 0) {
                return; 
            }
        }

        System.out.print("Ingrese el numero de choferes a registrar: ");
        int c = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < c; i++) {
            System.out.println("\nChofer N°" + (i+1) + "/" + c);

            System.out.print("- Ingrese el Dni: ");
            long dni = sc.nextLong();
            sc.nextLine();

            System.out.print("- Ingrese el Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("- Ingrese el Apellido: ");
            String apellido = sc.nextLine();

            System.out.print("- Ingrese el Numero de licencia: ");
            String nroLicencia = sc.nextLine();

            int d;
            List<ChoferCategoria> categorias = new ArrayList<>();
            
            while(true) {
                System.out.print("- Ingrese la Categoria (1 colectivo / 2 minibus / 0 cancelar): ");
                d = sc.nextInt();
                sc.nextLine();

                if (d == 0) {
                    if (categorias.isEmpty()) {
                        System.out.println("Debe ingresar al menos una categoria para registrar el chofer.");
                        continue;
                    } else {
                        break; 
                    }
                }
                if (d != 1 && d != 2) {
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    continue; 
                }

                System.out.print("- Ingrese la Fecha de vencimiento (DD/MM/AA): ");
                String fecha = sc.nextLine();

                if (d == 1) {
                    categorias.add(new ChoferCategoria(fecha, new Categoria(CategoriaTipo.COLECTIVO, null)));
                } else {
                    categorias.add(new ChoferCategoria(fecha, new Categoria(CategoriaTipo.MINIBUS, null)));
                }
            }
            choferes.add(new Chofer(dni, nombre, apellido, nroLicencia, categorias, null));
            System.out.println("Chofer agregado correctamente.");
        }
    }

    /**
     * Metodo para registrar los vehiculos.
     */
    public void registrarVehiculos() {
        System.out.println("\n" + "-".repeat(45) + "\nRegistrar vehiculos\n" + "-".repeat(45));
        if (!vehiculos.isEmpty()) {
            System.out.println("Ya hay vehiculos registrados.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar más vehiculos (1), limpiar y empezar de nuevo (2) o volver al menu (0)? ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 0) {
                        return;
                    } 
                    if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("Ingreso inválido. Debe ser '1'/'2'/'0'. Vuelva a intentar");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                vehiculos.clear();
                System.out.println("Vehiculos anteriores eliminados.");
            }
        }

        System.out.print("Ingrese la cantidad de vehículos a registrar: ");
        int a = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < a; i++) {
            System.out.println("\nVehículo N°" + (i+1) + "/" + a);

            System.out.print("Patente: ");
            String patente = sc.nextLine();

            System.out.print("Capacidad: ");
            int capacidad = sc.nextInt();
            sc.nextLine();

            String tipo;
            while (true) {
                System.out.print("Tipo (Colectivo o Minibus): ");
                tipo = sc.nextLine().trim().toLowerCase();
                if (tipo.equals("colectivo") || tipo.equals("minibus")) break;
                    System.out.println("Tipo invalido. Debe ser 'colectivo' o 'minibus'.");
            }

            if (tipo.equals("colectivo")) {
                boolean pisoDoble = false;
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Tiene piso doble? (si/no): ");
                    String respuesta = sc.nextLine().trim().toLowerCase();
                    if (respuesta.equals("si")) {
                        pisoDoble = true;
                        respuestaValida = true;
                    } else if (respuesta.equals("no")) {
                        pisoDoble = false;
                        respuestaValida = true;
                    } else {
                        System.out.println("Respuesta invalida. Debe ingresar 'si' o 'no'. Vuelva a intentar.");
                    }
                }
                //ASI SE INSTANCIA Y CREA UN OBJETO PARA LUEGO AGREGARLO A LAS LISTAS Y NO GENERE PROBLEMAS EN LOS CONSTRUCTORES.
                Colectivo colectivo = new Colectivo();
                colectivo.setPatente(patente);
                colectivo.setCapacidad(capacidad);
                colectivo.setPisoDoble(pisoDoble);
                vehiculos.add(colectivo);
            } else {
                boolean tieneBodega = false;
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Tiene bodega? (si/no): ");
                    String respuesta = sc.nextLine().trim().toLowerCase();
                    if (respuesta.equals("si")) {
                        tieneBodega = true;
                        respuestaValida = true;
                    } else if (respuesta.equals("no")) {
                        tieneBodega = false;
                        respuestaValida = true;
                    } else {
                        System.out.println("Respuesta invalida. Debe ingresar 'si' o 'no'. Vuelva a intentar.");
                    }
                }

                boolean aireAcondicionado = false;
                respuestaValida = false;
                while (!respuestaValida) {
                    System.out.print("¿Tiene aire acondicionado? (si/no): ");
                    String respuesta = sc.nextLine().trim().toLowerCase();
                    if (respuesta.equals("si")) {
                        aireAcondicionado = true;
                        respuestaValida = true;
                    } else if (respuesta.equals("no")) {
                        aireAcondicionado = false;
                        respuestaValida = true;
                    } else {
                        System.out.println("Respuesta invalida. Debe ingresar 'si' o 'no'. Vuelva a intentar.");
                    }
                }
                Minibus minibus = new Minibus();
                minibus.setAireAcondicionado(aireAcondicionado);
                minibus.setCapacidad(capacidad);
                minibus.setPatente(patente);
                minibus.setTieneBodega(tieneBodega);
                vehiculos.add(minibus);
            }
        } 
    }
    /**
     * Metodo para planificar viajes entre ciudades.
     */
    public void planificarViajes() {
    System.out.println("\n" + "-".repeat(45));
    System.out.println("Planificar viajes");
    System.out.println("-".repeat(45));

    if (ciudades.size() < 2) {
        System.out.println("Debe haber al menos dos ciudades registradas para planificar un viaje.");
        return;
    }

    // Mostrar ciudades disponibles con índice
    System.out.println("\nCiudades registradas:");
    for (int i = 0; i < ciudades.size(); i++) {
        Ciudad c = ciudades.get(i);
        System.out.println(i + " - " + c.getNombre() + " (" + c.getProvincia() + ")");
    }

    // Seleccionar ciudad origen
    Ciudad origen = null;
    while (origen == null) {
        System.out.print("Seleccione el número de la ciudad de origen: ");
        try {
            int indiceOrigen = Integer.parseInt(sc.nextLine());
            if (indiceOrigen >= 0 && indiceOrigen < ciudades.size()) {
                origen = ciudades.get(indiceOrigen);
            } else {
                System.out.println("Índice inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
        }
    }

    // Seleccionar ciudad destino (diferente a origen)
    Ciudad destino = null;
    while (destino == null) {
        System.out.print("Seleccione el número de la ciudad de destino: ");
        try {
            int indiceDestino = Integer.parseInt(sc.nextLine());
            if (indiceDestino >= 0 && indiceDestino < ciudades.size()) {
                Ciudad seleccionada = ciudades.get(indiceDestino);
                if (seleccionada.getNombre().equalsIgnoreCase(origen.getNombre()) &&
                    seleccionada.getProvincia() == origen.getProvincia()) {
                    System.out.println("La ciudad destino no puede ser igual a la ciudad origen.");
                } else {
                    destino = seleccionada;
                }
            } else {
                System.out.println("Índice inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
        }
    }

    // Solicitar fecha del viaje
    System.out.print("Ingrese la fecha del viaje (formato DD-MM-AAAA): ");
    String fecha = sc.nextLine(); // Validación de formato puede hacerse luego

    // Crear viaje sin chofer ni vehículo aún
    Viaje viaje = new Viaje();
    viaje.setOrigen(origen);
    viaje.setDestino(destino);
    viaje.setFecha(fecha);

    // Agregar a listas correspondientes
    origen.setOrigenesViajes(viaje);
    destino.setDestinosViajes(viaje);
    viajes.add(viaje);

    System.out.println("Viaje planificado exitosamente.");
    }

      

    /**
     * Metodo para asociar un vehiculo y un chofer a cada viaje.
     */
        public void asociarVehiculoYChofer() {
    System.out.println("\n" + "-".repeat(45) + "\nAsociar vehículo y chofer a cada viaje\n" + "-".repeat(45));

    if (viajes.isEmpty()) {
        System.out.println("No hay viajes registrados.");
        return;
    }

    for (Viaje viaje : viajes) {
        if (viaje.getChofer() != null && viaje.getVehiculo() != null) continue;

        System.out.println("\nViaje de " + viaje.getOrigen().getNombre() + " a " + viaje.getDestino().getNombre());

        // Mostrar vehículos disponibles
        System.out.println("Vehículos disponibles:");
        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.println((i + 1) + ". " + vehiculos.get(i));
        }

        System.out.print("Seleccione el número del vehículo: ");
        int indiceVehiculo = Integer.parseInt(sc.nextLine()) - 1;
        Vehiculo vehiculoSeleccionado = vehiculos.get(indiceVehiculo);

        // Determinar la categoría requerida
        CategoriaTipo categoriaRequerida;
        if (vehiculoSeleccionado instanceof Colectivo) {
            categoriaRequerida = CategoriaTipo.COLECTIVO;
        } else if (vehiculoSeleccionado instanceof Minibus) {
            categoriaRequerida = (CategoriaTipo.MINIBUS); ;
        } else {
            System.out.println("Tipo de vehículo desconocido.");
            continue;
        }

        // Mostrar choferes disponibles
        System.out.println("Choferes disponibles:");
        for (int i = 0; i < choferes.size(); i++) {
            System.out.println((i + 1) + ". " + choferes.get(i));
        }

        System.out.print("Seleccione el número del chofer: ");
        int indiceChofer = Integer.parseInt(sc.nextLine()) - 1;
        Chofer choferSeleccionado = choferes.get(indiceChofer);

        // Validar si tiene la categoría requerida vigente
        boolean tieneLicenciaValida = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        for (ChoferCategoria cc : choferSeleccionado.getCategorias()) {
            if (cc.getCategoria().getTipo() == categoriaRequerida) {
                if (esFechaValida(cc.getFechaVencimiento())) {
                    System.out.println("ERROR: Fecha inválida en licencia del chofer.");
                    continue;
                }
                LocalDate vencimiento = LocalDate.parse(cc.getFechaVencimiento(), DateTimeFormatter.ofPattern("dd/MM/yy"));
                if (!vencimiento.isBefore(LocalDate.now())) {
                    tieneLicenciaValida = true;
                    break;
                }
        }
    }


        if (!tieneLicenciaValida) {
            System.out.println("ERROR: El chofer no tiene una licencia vigente para manejar un " + categoriaRequerida);
            continue;
        }

        // Asignar
        viaje.setVehiculo(vehiculoSeleccionado);
        viaje.setChofer(choferSeleccionado);
        vehiculoSeleccionado.setVehiculoViajes(viaje);
        choferSeleccionado.getViajesChofer().add(viaje);

        System.out.println("Vehículo y chofer asignados correctamente.");
    }
}



    /**
     * Metodo para mostrar los viajes programados con informacion detallada.
     */
    public void viajesProgramados() {
        System.out.println("\n" + "-".repeat(45) + "\nViajes programados\n" + "-".repeat(45));

        if (viajes.isEmpty()) {
            System.out.println("No hay viajes programados.");
            int opcion = 0;

            while (opcion != 1 && opcion != 2) {
                System.out.println("Seleccione una opción:\n1. Volver al menu principal\n2. Ir a registrar viajes");

                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 

                    if (opcion == 1) {
                        return; 
                    } else if (opcion == 2) {
                        planificarViajes(); 
                        return;
                    } else {
                        System.out.println("Opción invalida. Debe ser '1' o '2'. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Entrada invalida. Debe ingresar 1 o 2.");
                    sc.nextLine();
                }
            }
            return;
        }
        for (int i = 0; i < viajes.size(); i++) {
            System.out.println(viajes.get(i));
        }
    }

    /**
     * Metodo para mostrar un informe detallado de viajes que tiene para realizar un colectivo determinado.
     */
    public void informeViajesARealizarColectivo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la patente del colectivo: ");
        String patente = sc.nextLine();
        System.out.println("\n" + "-".repeat(45) + "\nInforme de viajes de un colectivo determinado\n" + "-".repeat(45));
        boolean encontrado= false;
        for(Viaje viaje:viajes){
            if(viaje.getVehiculo() instanceof Colectivo){
                Colectivo colectivo = (Colectivo) viaje.getVehiculo();
                if(colectivo.getPatente().equalsIgnoreCase(patente)){
                    System.out.println(viaje);
                    encontrado=true;
                }
            }
        }
        if(!encontrado){
            System.out.println("No se han encontrado viajes asociados a la patente: "+patente);
            System.out.println("Volviendo al menu principal..");
        }
        return;
    }

    /**
     * Metodo para mostrar un informe de cantidad de viajes ya realizados por cada chofer de colectivos
     */
    public void informeViajesRealizadosColectivo() {
        System.out.println("\n" + "-".repeat(45) + "\nInforme de cantidad de viajes realizados por cada chofer de colectivos\n" + "-".repeat(45));
        String date="15/06/25";
        

    }
    public  void registrarCiudades(){
    Scanner sc = new Scanner(System.in);
    System.out.println("\n" + "-".repeat(45));
    System.out.println("Registro de Ciudades");
    System.out.println("-".repeat(45));

    System.out.print("¿Cuántas ciudades desea registrar?: ");
    int cantidad = Integer.parseInt(sc.nextLine());

    for (int i = 0; i < cantidad; i++) {
        System.out.println("\nCiudad #" + (i + 1));

        System.out.print("Nombre de la ciudad: ");
        String nombreCiudad = sc.nextLine();

        // Mostrar todas las provincias disponibles
        System.out.println("Provincias disponibles:");
        for (Provincia p : Provincia.values()) {
            System.out.println(" - " + p.name());
        }

        Provincia provincia = null;
        while (provincia == null) {
            System.out.print("Ingrese el nombre exacto de la provincia (mayúsculas y guiones bajos como se muestra): ");
            String nombreProvincia = sc.nextLine().toUpperCase();
            try {
                provincia = Provincia.valueOf(nombreProvincia);
            } catch (IllegalArgumentException e) {
                System.out.println("Provincia no válida. Intente nuevamente.");
            }
        }

        // Verificar si ya existe ciudad con el mismo nombre y provincia
        boolean yaExiste = false;
        for (Ciudad c : ciudades) {
            if (c.getNombre().equalsIgnoreCase(nombreCiudad) && c.getProvincia() == provincia) {
                yaExiste = true;
                break;
            }
        }

        if (yaExiste) {
            System.out.println("La ciudad '" + nombreCiudad + "' ya está registrada en la provincia '" + provincia + "'. No se agregará.");
            i--; // Para que vuelva a intentar esta ciudad
            continue;
        }

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(nombreCiudad);
        ciudad.setProvincia(provincia);
        ciudades.add(ciudad);

        System.out.println("Ciudad registrada con éxito.");
    }
}
    
    











    
    
    //Validaciones de fecha.
    public static boolean esFechaValida(String fecha) {
    if (!esFormatoFechaValido(fecha)) return false;

    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate.parse(fecha, formatter);
        return true;
    } catch (DateTimeParseException e) {
        return false;
        }
    }
    public static boolean esFormatoFechaValido(String fecha) {
    return fecha.matches("\\d{2}/\\d{2}/\\d{2}");
    }
}