package com.rutassa;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.rutassa.tipoVehiculo.Colectivo;
import com.rutassa.tipoVehiculo.Minibus;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Clase GestionSistema que lleva a cabo todos los metodos de gestion de rutassa.
 * Tiene como atributos listas de choferes, vehiculos, viajes, ciudades, y el Scanner.
 * @author Gonzalez Paz, Retamoso Maximo, Zanandrea Martin
 */


public class GestionSistema {


    private List<Chofer> choferes;
    private List<Vehiculo> vehiculos;
    private List<Viaje> viajes;
    private List<Ciudad> ciudades;
    private Scanner sc;

    /**
     * Esta es la fecha "actual" que usamos para probar.
     * Nos sirve para ver si las licencias están vencidas o si los viajes ya pasaron.
     */
    public static final String FECHA_ACTUAL_SIMULADA = "12/06/25";

    /**
     * Metodo getter que devuelve la fecha de "hoy" que utilizamos para simular.
     * @return FECHA_ACTUAL_SIMULADA
     */
    public static String getFechaActualSimulada() {
        return FECHA_ACTUAL_SIMULADA;
    }
    
    /**
     * Constructor por defecto de GestionSistema.
     * inicializamos las listas de choferes, vehiculos, viajes, ciudades y, el Scanner para el ingreso por pantalla 
     */
    public GestionSistema() {
        this.choferes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.viajes = new ArrayList<>();
        this.ciudades = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }


    //////////////////////////////--------------------------------------------
    private void cargarDatosDePrueba() {
    // Crear objetos Choferes
        List<ChoferCategoria> categorias1 = new ArrayList<>();
        categorias1.add(new ChoferCategoria("15/12/25", new Categoria(CategoriaTipo.COLECTIVO, null)));
        Chofer chofer1 = new Chofer(12345678L, "Juan", "Perez", "123456", categorias1, null);

        List<ChoferCategoria> categorias2 = new ArrayList<>();
        categorias2.add(new ChoferCategoria("20/11/26", new Categoria(CategoriaTipo.MINIBUS, null)));
        Chofer chofer2 = new Chofer(87654321L, "Maria", "Lopez", "654321", categorias2, null);

        // Agregar choferes a la lista
        choferes.add(chofer1);
        choferes.add(chofer2);

        // Crear Vehiculos
        Colectivo colectivo1 = new Colectivo();
        colectivo1.setPatente("AB123CD");
        colectivo1.setCapacidad(50);
        colectivo1.setPisoDoble(true);
        
        Minibus minibus1 = new Minibus();
        minibus1.setPatente("XYZ123");
        minibus1.setCapacidad(20);
        minibus1.setTieneBodega(false);
        minibus1.setAireAcondicionado(true);

        // Agregar vehiculos a la lista
        vehiculos.add(colectivo1);
        vehiculos.add(minibus1);

        // Crear Ciudades
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setNombre("Buenos Aires");
        ciudad1.setProvincia(Provincia.BUENOS_AIRES); 

        Ciudad ciudad2 = new Ciudad();
        ciudad2.setNombre("Cordoba");
        ciudad2.setProvincia(Provincia.CORDOBA);

        // Agregar a la lista
        ciudades.add(ciudad1);
        ciudades.add(ciudad2);

        // Crear viajes
        Viaje viaje1 = new Viaje("12/06/25", "08:00", "12:00", ciudad1, ciudad2, vehiculos.get(0), choferes.get(0)); 
        Viaje viaje2 = new Viaje("15/06/25", "14:00", "18:00", ciudad2, ciudad1, vehiculos.get(1), choferes.get(1));

        // Agregar viajes a la lista principal
        viajes.add(viaje1);
        viajes.add(viaje2);
    }
    /////////////////////////////-------------------------------------
    
    /** 
     * metodo main para correr el programa rutassa.
     */
    public static void main(String[] args) {
        /**
         * Inicializamos un objeto de tipo GestionSistema para poder crear objetos y llamar a los metodos de la clase.
         */
        GestionSistema sistema = new GestionSistema();
        sistema.cargarDatosDePrueba();

        /**
         * Menu para que el usuario pueda seleccionar que operacion realizar.
         */
        boolean salir = false;
        while (!salir) {
            System.out.println("\n" + "-".repeat(45) + "\n\tSistema de gestion Rutas S.A.\n" + "-".repeat(45));           
            System.out.println("0. Para salir ingrese 0");            
            System.out.println("1. Registrar choferes");
            System.out.println("2. Registrar vehiculos");
            System.out.println("3. Registrar ciudades");
            System.out.println("4. Planificar viajes entre ciudades");
            System.out.println("5. Asociar un vehiculo y un chofer a cada viaje"); 
            System.out.println("6. Mostrar los viajes programados con información detallada");
            System.out.println("7. Informe detallado de viajes que tiene para realizar un colectivo determinado");
            System.out.println("8. Informe de cantidad de viajes ya realizados por cada chofer de colectivos");
            System.out.println("-".repeat(80));
            
            int opcion = -1;
            boolean opcionValida = false;

            while (!opcionValida) {
                System.out.print("Seleccione una opcion: ");
                String input = sistema.sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Ingreso vacio. Se debe ingresar un numero entre 0 y 8. Vuelva a intentarlo.");
                    continue;
                }

                try {
                    opcion = Integer.parseInt(input);
                    if (opcion >= 0 && opcion <= 8) {
                        opcionValida = true;
                    } else {
                        System.out.println("Se debe ingresar un numero entre 0 y 8. Vuelva a intentarlo");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida. Debe ser un número. Vuelva a intentarlo.");
                }
            }

            /**
             * Switch para salir del programa o ingresar a los metodos dependiendo del ingreso del usuario.
             */
            switch (opcion) {
                case 0: 
                    salir = true;
                    System.out.println("\n" + "-".repeat(45) + "\n\tSaliendo del sistema de gestion\n" + "-".repeat(45));
                    break;
                case 1:
                    sistema.registrarChoferes(); 
                    break;
                case 2:
                    sistema.registrarVehiculos();
                    break;
                case 3:
                    sistema.registrarCiudades();
                    break;
                case 4:
                    sistema.planificarViajes(); 
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
     * El usuario ingresa los datos de cada chofer para ser añadido.
     */
    public void registrarChoferes() {
        System.out.println("\n" + "-".repeat(45) + "\nRegistrar choferes\n" + "-".repeat(45));

        //Verificar si hay choferes registrados
        if (!choferes.isEmpty()) {
            System.out.println("Ya hay choferes registrados.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar mas choferes (1), limpiar y registrar de 0 (2) o volver al menu (0)? ");
                
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 
                    if (opcion == 0) {
                        break;
                    } else if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'2'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                choferes.clear();
                System.out.println("Choferes anteriores eliminados.");
            }
            if (opcion == 0) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de choferes\n" + "-".repeat(45));
                return; 
            }
        }

        //Ingreso de chofer/es
        int i = 1;
        while (true) {
            System.out.println("\nChofer N" + i + ":");

            //Ingreso de DNI
            long dni = 0;
            while (true) {
                System.out.print("- Ingrese el DNI: ");
                String inputDni = sc.nextLine();

                if (inputDni.contains(" ")) {
                    System.out.println("El dni no debe tener espacios. Vuelva a intentarlo.");
                    continue;
                }

                if (!inputDni.matches("\\d+")) {
                    System.out.println("El dni solo contiene numeros. Vuelva a intentarlo.");
                    continue;
                } else if (inputDni.length() < 7 || inputDni.length() > 8) {
                    System.out.println("El dni debe tener 7 u 8 digitos. Vuelva a intentarlo.");
                    continue;
                }
    
                dni = Long.parseLong(inputDni);

                boolean dniRepetido = false;
                for (Chofer chofer : choferes) {
                    if (chofer.getDni() == dni) {
                        dniRepetido = true;
                        break;
                    }
                }

                if (dniRepetido) {
                    System.out.println("Ya existe un chofer con ese DNI. Ingrese otro.");
                    continue;
                }

                break;
            }

            //Ingreso de nombre
            String nombre = "";
            while (true) {
                System.out.print("- Ingrese el Nombre: ");
                nombre = sc.nextLine().trim();

                if (nombre.isEmpty()){
                    System.out.println("El nombre no puede estar vacio. vuelva a intentarlo.");
                } else if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+")){
                    System.out.println("El nombre solo debe contener letras y espacios. vuelva a intentarlo.");
                } else{
                    break;
                }
            }

            //Ingreso de apellido
            String apellido = "";
            while (true) {
                System.out.print("- Ingrese el Apellido: ");
                apellido = sc.nextLine().trim();

                if (apellido.isEmpty()){
                    System.out.println("El apellido no puede estar vacio. Vuelva a intentarlo.");
                }
                else if(!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+")){
                    System.out.println("El apellido solo debe contener letras y espacios. Vuelva a intentarlo.");
                }else{
                    break;
                }
            }

            //Ingreso de numero de licencia
            String nroLicencia = "";
            while (true) {
                System.out.print("- Ingrese el Numero de licencia: ");
                nroLicencia = sc.nextLine().replaceAll("\\s+", "");;

                if (nroLicencia.isEmpty()) {
                    System.out.println("El numero de licencia no puede estar vacio. Vuelva a intentarlo.");
                } else if (!nroLicencia.matches("\\d+")) {
                    System.out.println("El numero de licencia solo debe contener numeros. Vuelva a intentarlo.");
                } else if (nroLicencia.matches("0+")) { 
                    System.out.println("El numero de licencia no puede ser 0. Vuelva a intentarlo.");
                } else {
                    boolean licenciaRepetida = false;
                    for (Chofer chofer : choferes) {
                        if (chofer.getNroLicencia().equals(nroLicencia)) {
                            licenciaRepetida = true;
                            break;
                        }
                    }
                    if (licenciaRepetida) {
                        System.out.println("Ya existe un chofer con ese numero de licencia. Ingrese otro.");
                        continue;
                    }
                    break;
                }
            }

            //Ingreso de categoria/s
            List<ChoferCategoria> categorias = new ArrayList<>();
            while (true) {  
                int d = -1;
                while (true) {
                    System.out.print("- Ingrese la Categoria (1 Colectivo / 2 Minibus): ");
                    String input = sc.nextLine().trim();

                    try {
                        d = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("Ingreso invalido. Debe ser '1'/'2'. Vuelva a intentarlo.");
                        continue;
                    }

                    if (d != 1 && d != 2) {
                        System.out.println("Ingreso invalido. Debe ser '1'/'2'. Vuelva a intentarlo.");
                        continue;
                    }
                    break;
                }

                boolean categoriaYaIngresada = false;
                for (ChoferCategoria cat : categorias) {
                    if ((d == 1 && cat.getCategoria().getTipo() == CategoriaTipo.COLECTIVO) || 
                        (d == 2 && cat.getCategoria().getTipo() == CategoriaTipo.MINIBUS)) {
                        categoriaYaIngresada = true;
                        break;
                    }
                }
                if (categorias.size() == 2) {
                    System.out.println("Ya se ingresaron las dos categorias.");
                    break;  
                }
                if (categoriaYaIngresada) {
                    System.out.println("Ya ingreso esta categoria. Ingrese otra.");
                    continue;  
                }

                // Ingreso de fecha de vencimiento
                String fecha = "";
                LocalDate fechaLic = null;
                while (true) {
                    System.out.print("- Ingrese la Fecha de vencimiento (DD/MM/AA): ");
                    fecha = sc.nextLine().trim();

                    if (esFechaValida(fecha)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                        fechaLic = LocalDate.parse(fecha, formatter);

                        if (fechaLic.isBefore(LocalDate.now())) {
                            System.out.println("La fecha ingresada está vencida.");
                            System.out.print("¿Desea ingresar otra fecha o cancelar esta categoría? ('1' para nueva fecha / '0' para cancelar): ");
                            String eleccion = sc.nextLine().trim();
                            if (eleccion.equals("1")) {
                                continue; 
                            } else {
                                System.out.println("Categoria cancelada.");
                                fechaLic = null;
                                break; 
                            }
                        }
                        break; 
                    } else {
                        System.out.println("Ingrese una fecha válida en formato DD/MM/AA (Ej: 18/06/25).");
                    }
                }

                if (fechaLic != null) {
                    if (d == 1) {
                        categorias.add(new ChoferCategoria(fecha, new Categoria(CategoriaTipo.COLECTIVO, null)));
                    } else {
                        categorias.add(new ChoferCategoria(fecha, new Categoria(CategoriaTipo.MINIBUS, null)));
                    }
                }

                String respuesta = "";
                while (true) {
                    System.out.print("- ¿Desea agregar otra categoria? (si/no): ");
                    respuesta = sc.nextLine().trim().toLowerCase();

                    if (respuesta.equals("si") || respuesta.equals("no")) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Ingrese 'si'/'no'. Vuelva a intentarlo.");
                    }
                }
                if (respuesta.equals("no")) {
                    if (categorias.isEmpty()) {
                        System.out.println("Debe ingresar al menos una categoria para el chofer.");
                    } else {
                        break; 
                    }
                }
            }

            choferes.add(new Chofer(dni, nombre, apellido, nroLicencia, categorias, null));
            System.out.println("Chofer registrado: [Nombre: " + nombre + ", apellido: " + apellido + "]");

            String respuesta;
            while (true) {
                System.out.print("¿Quiere registrar otro chofer? (si/no): ");
                respuesta = sc.nextLine().trim().toLowerCase();

                if (respuesta.equals("si") || respuesta.equals("no")) {
                    break; 
                } else {
                    System.out.println("Ingreso invalido. Debe ingresar 'si'/'no'. Vuelva a intentarlo.");
                }
            }

            if (respuesta.equals("no")) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de choferes\n" + "-".repeat(45));
                break;
            }
            i++;
        }
    }

    /**
     * Metodo para registrar los vehiculos.
     * El usuario ingresa los datos de cada vehiculo para ser añadido.
     */
    public void registrarVehiculos() {
        System.out.println("\n" + "-".repeat(45) + "\nRegistrar vehiculos\n" + "-".repeat(45));

        if (!vehiculos.isEmpty()) {
            System.out.println("Hay vehiculos registrados.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar mas vehiculos (1), limpiar y registrar de 0 (2) o volver al menu (0)? ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 0) {
                        break;
                    } 
                    if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'2'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                vehiculos.clear();
                System.out.println("Vehiculos anteriores eliminados.");
            }
            if (opcion == 0) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de vehiculos\n" + "-".repeat(45));
                return; 
            }
        }

        //Ingreso de datos del vehiculo
        int i = 1;
        while (true) {
            System.out.println("\nVehiculo N°" + i + ":");

            //ingreso de patente
            String patente = "";
            while (true) {
                System.out.print("- Ingrese la Patente (Ej: AA123BB): ");
                patente = sc.nextLine().trim().toUpperCase();

                if (patente.isEmpty()) {
                    System.out.println("La patente no puede estar vacia. Vuelva a intentarlo.");
                    continue;
                }

                if (patente.contains(" ")) {
                    System.out.println("La patente no puede contener espacios. Vuelva a intentarlo.");
                    continue;
                }

                if (!patente.matches("^[A-Z]{2}\\d{3}[A-Z]{2}$")) {
                    System.out.println("Ingreso invalido. Debe tener 7 caracteres (2 letras, 3 números y 2 letras). Vuelva a intentarlo.");
                    continue;
                }

                boolean patenteRepetida = false;
                for (Vehiculo v : vehiculos) {
                    if (v.getPatente().equalsIgnoreCase(patente)) {
                        patenteRepetida = true;
                        break;
                    }
                }

                if (patenteRepetida) {
                    System.out.println("Ya existe un vehiculo con esa patente. Ingrese otra.");
                    continue;
                }
                break;
            }
            
            //ingreso capacidad
            int capacidad = 0;
            while (true) {
                System.out.print("- Ingrese la Capacidad: ");
                String entrada = sc.nextLine().trim();

                if (entrada.matches("\\d+")) {
                    capacidad = Integer.parseInt(entrada);
                    if (capacidad > 0 && capacidad < 200) {
                        break;
                    } else {
                        System.out.println("La capacidad debe ser un número mayor a cero y menor a 200. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Debe ingresar solo numeros enteros positivos. Vuelva a intentarlo.");
                }
            }

            //ingreso tipo
            int tipo = -1;
            while (true) {
                System.out.print("- Ingrese el Tipo (1 Colectivo / 2 Minibus): ");
                String entrada = sc.nextLine().trim();

                if (entrada.equals("1") || entrada.equals("2")) {
                    tipo = Integer.parseInt(entrada);
                    break;
                } else {
                    System.out.println("Ingreso invalido. Debe ingresar '1'/'2'. Vuelva a intentarlo.");
                }
            }

            if (tipo == 1) {
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
                        System.out.println("Respuesta invalida. Debe ingresar 'si'/'no'. Vuelva a intentarlo.");
                    }
                }

                Colectivo colectivo = new Colectivo();
                colectivo.setPatente(patente);
                colectivo.setCapacidad(capacidad);
                colectivo.setPisoDoble(pisoDoble);
                vehiculos.add(colectivo);
                System.out.println("Colectivo registrado: [Patente: " + patente + ", Capacidad: " + capacidad + "]");

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
                        System.out.println("Respuesta invalida. Debe ingresar 'si'/'no'. Vuelva a intentarlo.");
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
                        System.out.println("Respuesta invalida. Debe ingresar 'si'/'no'. Vuelva a intentarlo.");
                    }
                }

                Minibus minibus = new Minibus();
                minibus.setAireAcondicionado(aireAcondicionado);
                minibus.setCapacidad(capacidad);
                minibus.setPatente(patente);
                minibus.setTieneBodega(tieneBodega);
                vehiculos.add(minibus);
                System.out.println("Minibus registrado: [Patente: " + patente + ", Capacidad: " + capacidad + "]");
            }
                
            String respuesta;
            while (true) {
                System.out.print("¿Desea registrar otro vehículo? (si/no): ");
                respuesta = sc.nextLine().trim().toLowerCase();

                if (respuesta.equals("si") || respuesta.equals("no")) {
                    break; 
                } else {
                    System.out.println("Ingreso invalido. Debe ingresar 'si'/'no'. Vuelva a intentarlo.");
                }
            }

            if (respuesta.equals("no")) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de vehiculos\n" + "-".repeat(45));
                break;
            }
            i++;
        }
    } 
    
    /**
     * Metodo para registrar las ciudades
     */
    public  void registrarCiudades(){
        System.out.println("\n" + "-".repeat(45) + "\nRegistro de Ciudades\n" + "-".repeat(45));

        if (!ciudades.isEmpty()) {
            System.out.println("Ya hay ciudades registradas.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar mas ciudades (1), limpiar y registrar de 0 (2) o volver al menu (0)? ");
                
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 
                    if (opcion == 0) {
                        break;
                    } else if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'2'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                ciudades.clear();
                System.out.println("ciudades anteriores eliminadas.");
            }
            if (opcion == 0) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de ciudades\n" + "-".repeat(45));
                return; 
            }
        }

        int i = 1;
        while (true) {
            System.out.println("\nCiudad N" + i + ":");

            String nombreCiudad = "";
            while(true){
                System.out.print("- Ingrese el nombre de la ciudad: ");
                nombreCiudad = sc.nextLine().trim().replaceAll("\\s+", " ");
                if (nombreCiudad.isEmpty()) {
                    System.out.println("El nombre no puede estar vacio. Vuelva a intentarlo.");
                } else {
                    break;
                }
            }

            // Mostrar todas las provincias disponibles
            System.out.println("Provincias disponibles:");
            for (Provincia p : Provincia.values()) {
                System.out.println(" - " + p.name());
            }

            Provincia provincia = null;
            while (provincia == null) {
                System.out.print("Ingrese el nombre exacto de la provincia (mayusculas y guiones bajos como se muestra): ");
                String nombreProvincia = sc.nextLine().trim().toUpperCase();
                try {
                    provincia = Provincia.valueOf(nombreProvincia);
                } catch (IllegalArgumentException e) {
                    System.out.println("Provincia invalida. Vuelva a intentarlo.");
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
                System.out.println("La ciudad '" + nombreCiudad + "' ya está registrada en la provincia '" + provincia + ".");
            } else {
                Ciudad ciudad = new Ciudad();
                ciudad.setNombre(nombreCiudad);
                ciudad.setProvincia(provincia);
                ciudades.add(ciudad);
                System.out.println("Ciudad registrada: [Nombre: " + nombreCiudad + ", Provincia: " + provincia + "]");
            }

            String respuesta = "";
            while (true) {
                System.out.print("¿Desea registrar otra ciudad? (si/no): ");
                respuesta = sc.nextLine().trim().toLowerCase();
                if (respuesta.equals("si") || respuesta.equals("no")) {
                    break;
                } else {
                    System.out.println("Respuesta invalida. Debe ingresar 'si' o 'no'. Vuelva a intentarlo.");
                }
            }
            if (respuesta.equals("no")) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de ciudades\n" + "-".repeat(45));
                break;
            }

            i++;
        }
    }

    /**
     * Metodo para planificar viajes entre ciudades.
     * El usuario ingresa los datos de cada viaje para planificarlo.
     */
    public void planificarViajes() {
        System.out.println("\n" + "-".repeat(45) + "\nPlanificar viajes\n" + "-".repeat(45));

        while (choferes.isEmpty()) {
            int opcion = -1;
            System.out.println("No hay choferes registrados.");
            while (opcion != 0 && opcion != 1) {
                System.out.print("Registrar choferes (1) o volver al menú (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 1) {
                        registrarChoferes();
                    } else if (opcion == 0) {
                        return; 
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                        opcion = -1;
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
        }

        while (vehiculos.isEmpty()) {
            int opcion = -1;
            System.out.println("No hay vehiculos registrados.");
            while (opcion != 0 && opcion != 1) {
                System.out.print("Registrar vehiculos (1) o volver al menu (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 1) {
                        registrarVehiculos();
                    } else if (opcion == 0) {
                        return; 
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                        opcion = -1;
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
        }

        while (ciudades.isEmpty() || ciudades.size() < 2) {
            int opcion = -1;
            if (ciudades.isEmpty()) {
                System.out.println("No hay ciudades registradas.");
            } else {
                System.out.println("No hay al menos dos ciudades registradas (origen y destino).");
            }
            while (opcion != 0 && opcion != 1) {
                System.out.print("Registrar ciudades (1) o volver al menu (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 
                    if (opcion == 1) {
                        registrarCiudades();
                    } else if (opcion == 0) {
                        return;  
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                        opcion = -1; 
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'0'. Vuelva a intentarlo.");
                    sc.nextLine(); 
                }
            }
        }

        if (!viajes.isEmpty()) {
            System.out.println("Ya hay viajes planificados.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar mas viajes (1), limpiar y registrar de 0 (2) o volver al menu (0)? ");
                
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 
                    if (opcion == 0) {
                        break;
                    } else if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'2'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                viajes.clear();
                System.out.println("Viajes anteriores eliminados.");
            }
            if (opcion == 0) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo del registro de Viajes\n" + "-".repeat(45));
                return; 
            }
        }
        while(true) {
            //Mostrar ciudades disponibles
            System.out.println("\nCiudades registradas:");
            for (int i = 0; i < ciudades.size(); i++) {
                Ciudad c = ciudades.get(i);
                System.out.println(i + " - " + c.getNombre() + " (" + c.getProvincia() + ")");
            }

            //ingresar ciudad origen
            Ciudad origen = null;
            while (origen == null) {
                System.out.print("Seleccione el numero de la ciudad de origen: ");
                try {
                    int indiceOrigen = Integer.parseInt(sc.nextLine());
                    if (indiceOrigen >= 0 && indiceOrigen < ciudades.size()) {
                        origen = ciudades.get(indiceOrigen);
                    } else {
                        System.out.println("Indice invalido. Vuelva a intentarlo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un numero valido. Vuelva a intentarlo.");
                }
            }

            //ingresar ciudad destino
            Ciudad destino = null;
            while (destino == null) {
                System.out.print("Seleccione el numero de la ciudad de destino: ");
                try {
                    int indiceDestino = Integer.parseInt(sc.nextLine());
                    if (indiceDestino >= 0 && indiceDestino < ciudades.size()) {
                        Ciudad seleccionada = ciudades.get(indiceDestino);
                        if (seleccionada.getNombre().equalsIgnoreCase(origen.getNombre()) &&
                            seleccionada.getProvincia().equals(origen.getProvincia())) {
                            System.out.println("La ciudad destino no puede ser igual a la ciudad origen. Vuelva a intentarlo.");
                        } else {
                            destino = seleccionada;
                        }
                    } else {
                        System.out.println("Indice invalido. Vuelva a intentarlo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un numero valido. Vuelva a intentarlo.");
                }
            }

            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd/MM/yy");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");

            LocalDate fechaViaje = null;
            LocalTime horarioSalida = null;
            String fechaStr = "";

            //ingreso fecha
            while (true) {
                System.out.print("Ingrese la fecha del viaje (formato DD/MM/AA): ");
                fechaStr = sc.nextLine().trim().replaceAll("\\s+", " ");

                if (fechaStr.isEmpty()) {
                    System.out.println("La fecha no puede estar vacia. Vuelva a intentarlo.");
                    continue;
                }

                if (!esFormatoFechaValido(fechaStr)) {
                    System.out.println("Formato incorrecto. Debe usar formato DD/MM/AA. Vuelva a intentarlo.");
                    continue;
                }

                if (!esFechaValida(fechaStr)) {
                    System.out.println("Fecha invalida. Debe ser una fecha real. Vuelva a intentarlo.");
                    continue;
                }

                fechaViaje = LocalDate.parse(fechaStr, formatterFecha);
                break;
            }

            //ingreso horario salida
            while (horarioSalida == null) {
                System.out.print("Ingrese el horario de salida (HH:mm): ");
                String inputHora = sc.nextLine().trim();
                try {
                    horarioSalida = LocalTime.parse(inputHora, formatterHora);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato invalido. Debe ser en formato HH:mm (por ejemplo, 14:30). Vuelva a intentarlo.");
                }
            }

            // Verificar si la fecha y hora combinadas están en el pasado
            LocalDateTime salida = LocalDateTime.of(fechaViaje, horarioSalida);

            if (salida.isBefore(LocalDateTime.now())) {
                System.out.println("La fecha y hora de salida ya pasaron.");
                while (true) {
                    System.out.print("¿Desea ingresar otra fecha y hora? ('1' Si / '0' cancelar): ");
                    String eleccion = sc.nextLine().trim();
                    if (eleccion.equals("1")) {
                        planificarViajes(); 
                        return;
                    } else if (eleccion.equals("0")) {
                        System.out.println("Planificacion cancelada.");
                        return;
                    } else {
                        System.out.println("Opcion inválida. Ingrese '1'/'0'. Vuelva a intentarlo.");
                    }
                }
            }

            //Crear viaje sin chofer ni vehículo aún
            Viaje viaje = new Viaje();
            viaje.setOrigen(origen);
            viaje.setDestino(destino);
            viaje.setFecha(fechaViaje.format(formatterFecha));
            viaje.setHorarioSalida(horarioSalida.format(formatterHora));
            
            //Agregar a listas correspondientes
            origen.setOrigenesViajes(viaje);
            destino.setDestinosViajes(viaje);
            viajes.add(viaje);

            System.out.println("Viaje planificado: [Origen: " + origen + ", Destino: " + destino + ", Fecha: " + fechaViaje + "]");

            String respuesta = "";
            while (true) {
                System.out.print("¿Desea planificar otro viaje? (si/no): ");
                respuesta = sc.nextLine().trim().toLowerCase();
                if (respuesta.equals("si") || respuesta.equals("no")) {
                    break;
                } else {
                    System.out.println("Ingreso invalido. Debe ingresar 'si'/'no'. Vuelva a intentarlo.");
                }
            }

            if (respuesta.equals("no")) {
                break; 
            }
        }
    }

    /**
     * Metodo para asociar un vehiculo y un chofer a cada viaje.
     */
    public void asociarVehiculoYChofer() {
        System.out.println("\n" + "-".repeat(45) + "\nAsociar vehículo y chofer a cada viaje\n" + "-".repeat(45));

        if (choferes.isEmpty()) {
            int opcion = -1;
            while (opcion != 0 && opcion != 1) {
                System.out.print("No hay choferes registrados. Registrar choferes (1) o volver al menu (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 1) {
                        registrarChoferes();
                        break;
                    } else if (opcion == 0) {
                        return;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
        }

        if (vehiculos.isEmpty()) {
            int opcion = -1;
            while (opcion != 0 && opcion != 1) {
                System.out.print("No hay vehiculos registrados. Registrar vehiculos (1) o volver al menu (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 1) {
                        registrarVehiculos();
                        break;
                    } else if (opcion == 0) {
                        return;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
        }

        if (viajes.isEmpty()) {
            int opcion = -1;
            while (opcion != 0 && opcion != 1) {
                System.out.print("No hay viajes planificados. Planificar viajes (1) o volver al menu (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 1) {
                        planificarViajes();
                        break;
                    } else if (opcion == 0) {
                        return;
                    } else {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser '1'/'0'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }
        }

        // Verificar si ya hay asociaciones
        boolean asociacionesExisten = false;
        for (Viaje v : viajes) {
            if (v.getChofer() != null || v.getVehiculo() != null) {
                asociacionesExisten = true;
                break;
            }
        }

        if (asociacionesExisten) {
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("Ya existen asociaciones. Agregar mas (1), limpiar y registrar de 0 (2) o volver al menu (0): ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion != 0 && opcion != 1 && opcion != 2) {
                        System.out.println("Opcion invalida. Vuelva a intentarlo.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ingresar '0'/'1'/'2'. Vuelva a intentarlo.");
                    sc.nextLine();
                }
            }

            if (opcion == 0) {
                System.out.println("\n" + "-".repeat(45) + "\nSaliendo de asociacion choferes y vehiculos a cada viaje\n" + "-".repeat(45));
                return;
            }

            if (opcion == 2) {
                for (Viaje v : viajes) {
                    v.setChofer(null);
                    v.setVehiculo(null);
                }
                System.out.println("Se eliminaron todas las asociaciones anteriores.");
            }
            // si opcion == 1 seguimos para agregar más sin borrar
        }

        for (Viaje viaje : viajes) {
            if (viaje.getChofer() != null || viaje.getVehiculo() != null) {
                System.out.println("El viaje de " + viaje.getOrigen().getNombre() + " a " + viaje.getDestino().getNombre() + " ya tiene chofer o vehículo asignado.");
                System.out.print("¿Desea modificarlo? (si/no): ");
                String respuestaMod = sc.nextLine().trim().toLowerCase();
                if (!respuestaMod.equals("si")) {
                    continue; // salto a siguiente viaje
                }
            }

            // Vehículos disponibles para esta fecha y hora
            List<Vehiculo> vehiculosDisponibles = new ArrayList<>();
            for (Vehiculo v : vehiculos) {
                boolean disponible = true;
                for (Viaje vj : v.getVehiculoViajes()) {
                    if (vj.getFecha().equals(viaje.getFecha())
                        && vj.getHorarioSalida().equals(viaje.getHorarioSalida())) {
                        disponible = false;
                        break;
                    }
                }
                if (disponible) {
                    vehiculosDisponibles.add(v);
                }
            }

            if (vehiculosDisponibles.isEmpty()) {
                System.out.println("No hay vehículos disponibles para la fecha y horario del viaje " + viaje);
                continue;
            }

            System.out.println("Vehículos disponibles para el viaje " + viaje + ":");
            for (int i = 0; i < vehiculosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + vehiculosDisponibles.get(i));
            }

            int indiceVehiculo = -1;
            while (true) {
                System.out.print("Seleccione el numero del vehículo: ");
                try {
                    indiceVehiculo = Integer.parseInt(sc.nextLine()) - 1;
                    if (indiceVehiculo >= 0 && indiceVehiculo < vehiculosDisponibles.size()) {
                        break;
                    } else {
                        System.out.println("Numero invalido. Vuelva a intentarlo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un numero válido. Vuelva a intentarlo.");
                }
            }
            Vehiculo vehiculoSeleccionado = vehiculosDisponibles.get(indiceVehiculo);

            CategoriaTipo categoriaRequerida;
            if (vehiculoSeleccionado instanceof Colectivo) {
                categoriaRequerida = CategoriaTipo.COLECTIVO;
            } else if (vehiculoSeleccionado instanceof Minibus) {
                categoriaRequerida = CategoriaTipo.MINIBUS;
            } else {
                System.out.println("Tipo de vehículo desconocido.");
                continue;
            }

            // Choferes disponibles para la fecha y hora
            List<Chofer> choferesDisponibles = new ArrayList<>();
            for (Chofer c : choferes) {
                boolean disponible = true;
                for (Viaje vj : c.getViajesChofer()) {
                    if (vj.getFecha().equals(viaje.getFecha())
                        && vj.getHorarioSalida().equals(viaje.getHorarioSalida())) {
                        disponible = false;
                        break;
                    }
                }
                if (disponible) {
                    choferesDisponibles.add(c);
                }
            }

            if (choferesDisponibles.isEmpty()) {
                System.out.println("No hay choferes disponibles para la fecha y horario del viaje " + viaje);
                continue;
            }

            System.out.println("Choferes disponibles para el viaje " + viaje + ":");
            for (int i = 0; i < choferesDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + choferesDisponibles.get(i));
            }

            int indiceChofer = -1;
            while (true) {
                System.out.print("Seleccione el numero del chofer: ");
                try {
                    indiceChofer = Integer.parseInt(sc.nextLine()) - 1;
                    if (indiceChofer >= 0 && indiceChofer < choferesDisponibles.size()) {
                        break;
                    } else {
                        System.out.println("Numero invalido. Vuelva a intentarlo.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un numero válido. Vuelva a intentarlo.");
                }
            }
            Chofer choferSeleccionado = choferesDisponibles.get(indiceChofer);

            // Validar categoría
            boolean tieneCategoriaValida = false;
            for (ChoferCategoria choferCat : choferSeleccionado.getCategorias()) {
                Categoria categoria = choferCat.getCategoria();
                if (categoria != null && categoria.getTipo() == categoriaRequerida && !choferCat.estaVencida()) {
                    tieneCategoriaValida = true;
                    break;
                }
            }

            if (!tieneCategoriaValida) {
                System.out.println("El chofer no tiene la categoria requerida y/o esta vencida. Seleccione otro.");
                continue;
            }

            viaje.setVehiculo(vehiculoSeleccionado);
            viaje.setChofer(choferSeleccionado);
            vehiculoSeleccionado.setVehiculoViajes(viaje);
            choferSeleccionado.getViajesChofer().add(viaje);

            System.out.println("Vehículo y chofer asociados para el viaje: " + viaje);

            // Preguntar si seguir con siguiente viaje o salir
            String respuesta = "";
            while (true) {
                System.out.print("¿Desea seguir asociando el siguiente viaje? (si/no): ");
                respuesta = sc.nextLine().trim().toLowerCase();
                if (respuesta.equals("si") || respuesta.equals("no")) {
                    break;
                } else {
                    System.out.println("Ingreso inválido. Debe ingresar 'si' o 'no'. Vuelva a intentarlo.");
                }
            }
            if (respuesta.equals("no")) {
                break;
            }
        }
        System.out.println("\n" + "-".repeat(45) + "\nSaliendo de asociacion choferes y vehiculos a cada viaje\n" + "-".repeat(45));
    }

    /**
    * Metodo para mostrar los viajes programados con informacion detallada.
    */
    public void viajesProgramados() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        boolean hayViajesIncompletos = false;

        for (Viaje viaje : viajes) {
            boolean datosCompletos = true;

            // Validar fecha
            if (!esFechaValida(viaje.getFecha())) {
                System.out.println("Viaje con fecha inválida: " + viaje.getFecha());
                datosCompletos = false;
            }

            // Validar otros datos
            if (viaje.getChofer() == null || viaje.getVehiculo() == null || viaje.getOrigen() == null || viaje.getDestino() == null) {
                datosCompletos = false;
            }

            if (datosCompletos) {
                System.out.println("\nViaje:");
                System.out.println("Fecha: " + viaje.getFecha());
                System.out.println("Origen: " + viaje.getOrigen().getNombre() + " (" + viaje.getOrigen().getProvincia() + ")");
                System.out.println("Destino: " + viaje.getDestino().getNombre() + " (" + viaje.getDestino().getProvincia() + ")");
                System.out.println("Chofer: " + viaje.getChofer().getNombre() + " " + viaje.getChofer().getApellido());
                System.out.println("Vehículo: " + viaje.getVehiculo().getPatente() + " - Capacidad: " + viaje.getVehiculo().getCapacidad());
            } else {
                hayViajesIncompletos = true;
            }
        }

        if (viajes.isEmpty()) {
            System.out.println("No hay viajes programados.");
        }

        if (hayViajesIncompletos) {
            System.out.println("\nHay viajes incompletos que no se pueden mostrar con detalle.");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Volver al menú principal");
            System.out.println("2. Planificar un nuevo viaje");

            int opcion;
            do {
                System.out.print("Ingrese una opción: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Ingrese un número válido: ");
                    sc.next();
                }
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.println("Volviendo al menu principal...");
                        return;
                    case 2:
                        this.planificarViajes();
                        return;
                    default:
                        System.out.println("Opcion invalida. Intente nuevamente.");
                }
            } while (true);
        }
    }

    /**
    * Metodo para mostrar un informe detallado de viajes que tiene para realizar un colectivo determinado.
    */
    public void informeViajesARealizarColectivo() {
        System.out.println("\n" + "-".repeat(45) + "\nInforme de viajes pendientes de un colectivo\n" + "-".repeat(45));

        System.out.print("Ingrese la patente del colectivo: ");
        String patenteBuscada = sc.nextLine().trim();

        Vehiculo vehiculoEncontrado = null;

        // Buscar vehículo por patente y validar que sea un Colectivo
        for (Vehiculo v : vehiculos) {
            if (v.getPatente().equalsIgnoreCase(patenteBuscada) && v instanceof Colectivo) {
                vehiculoEncontrado = v;
                break;
            }
        }

        if (vehiculoEncontrado == null) {
            System.out.println("No se encontró un colectivo con esa patente.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate fechaSimulada = LocalDate.parse(FECHA_ACTUAL_SIMULADA, formatter);
        boolean hayViajesPendientes = false;

        for (Viaje viaje : viajes) {
            if (viaje.getVehiculo() != null && viaje.getVehiculo().equals(vehiculoEncontrado)) {
                if (!esFechaValida(viaje.getFecha())) {
                    System.out.println("Viaje con fecha inválida: " + viaje.getFecha());
                    continue;
                }

                LocalDate fechaViaje = LocalDate.parse(viaje.getFecha(), formatter);

                if (!fechaViaje.isBefore(fechaSimulada)) {
                    hayViajesPendientes = true;
                    System.out.println("\nViaje programado:");
                    System.out.println("Fecha: " + viaje.getFecha());
                    System.out.println("Origen: " + viaje.getOrigen().getNombre() + " (" + viaje.getOrigen().getProvincia() + ")");
                    System.out.println("Destino: " + viaje.getDestino().getNombre() + " (" + viaje.getDestino().getProvincia() + ")");
                    System.out.println("Chofer: " + viaje.getChofer().getNombre() + " " + viaje.getChofer().getApellido()); 
                }
            }
        }

        if (!hayViajesPendientes) {
            System.out.println("No hay viajes pendientes para ese colectivo.");
        }
    }

    /**
    * Metodo para mostrar un informe de cantidad de viajes ya realizados por cada chofer de colectivos
    */
    public void informeViajesRealizadosColectivo() {
        System.out.println("\n" + "-".repeat(45) + "\nInforme de cantidad de viajes realizados por cada chofer de colectivos\n" + "-".repeat(45));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate fechaSimulada = LocalDate.parse(FECHA_ACTUAL_SIMULADA, formatter);

        Map<Chofer, Integer> viajesPorChofer = new HashMap<>();

        for (Viaje viaje : viajes) {
            if (viaje.getVehiculo() instanceof Colectivo && viaje.getChofer() != null) {
                try {
                    LocalDate fechaViaje = LocalDate.parse(viaje.getFecha(), formatter);
                    if (fechaViaje.isBefore(fechaSimulada)) {
                        Chofer chofer = viaje.getChofer();
                        viajesPorChofer.put(chofer, viajesPorChofer.getOrDefault(chofer, 0) + 1);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Fecha inválida en viaje: " + viaje.getFecha());
                }
            }
        }

        if (viajesPorChofer.isEmpty()) {
            System.out.println("No hay viajes realizados por choferes de colectivos.");
        } else {
            for (Map.Entry<Chofer, Integer> entry : viajesPorChofer.entrySet()) {
                Chofer chofer = entry.getKey();
                int cantidad = entry.getValue();
                System.out.println("Chofer: " + chofer.getNombre()+","+chofer.getApellido() +"\n" + "- Cantidad de viajes: " + cantidad);
            }
        }
            
    }
    
    /**
     * Metodo para validar la fecha ingresada
     * @param fecha 
     * @return true/false 
     */
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

