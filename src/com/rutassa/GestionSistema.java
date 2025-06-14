package com.rutassa;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.rutassa.tipoVehiculo.Colectivo;
import com.rutassa.tipoVehiculo.Minibus;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase GestionSistema que lleva a cabo todos los metodos de gestion de rutassa
 */
public class GestionSistema {
    private static List<Chofer> choferes = new ArrayList<>();
    private static List<Vehiculo> vehiculos = new ArrayList<>();
    private static List<Viaje> viajes = new ArrayList<>();
    private static List<Ciudad> ciudades = new ArrayList<>();
    private static List<Viaje> viajesRealizados = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    /** 
     * metodo main para correr el programa rutassa.
     */
    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n" + "-".repeat(45) + "\n\tSistema de gestion Rutas SA\n" + "-".repeat(45));
            System.out.println("Elija una opcion: ");            
            System.out.println("0. Para salir ingrese 0");            
            System.out.println("1. Registrar choferes");
            System.out.println("2. Registrar vehiculos");
            System.out.println("3. Planificar viajes entre ciudades");
            System.out.println("4. Asociar un vehiculo y un chofer a cada viaje");
            System.out.println("5. Mostrar los viajes programados con información detallada");
            System.out.println("6. Informe detallado de viajes que tiene para realizar un colectivo determinado");
            System.out.println("7. Informe de cantidad de viajes ya realizados por cada chofer de colectivos");
            
            int opcion = -1;
            boolean opcionValida = false;

            while (!opcionValida) {
                System.out.print("\nSeleccione una opcion: ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();

                    if (opcion >= 0 && opcion <= 7) {
                        opcionValida = true;
                    } else {
                        System.out.println("Opcion invalida. Por favor ingrese un numero entre 0 y 7. Vuelva a intentar.");
                    }
                } else {
                    System.out.println("Ingreso invalido. debe ser un numero.");
                    sc.nextLine(); 
                }
            }

            switch (opcion) {
                case 0: 
                    salir = true;
                    System.out.println("\n" + "-".repeat(45) + "\n\tSaliendo del sistema de gestion\n" + "-".repeat(45));
                    break;
                case 1:
                    registrarChoferes();
                    break;
                case 2:
                    registrarVehiculos();
                    break;
                case 3:
                    planificarViajes();
                    break;
                case 4:
                    asociarVehiculoYChofer();
                    break;
                case 5:
                    viajesProgramados();
                    break;
                case 6:
                    informeViajesARealizarColectivo();
                    break;
                case 7:
                    informeViajesRealizadosColectivo();
                    break;
            }
        }
        sc.close();
    }
    
    /**
     * Metodo para registrar los choferes.
     */
    public static void registrarChoferes() {
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
                        System.out.println("Debe ingresar al menos una categoraa para registrar el chofer.");
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
                    categorias.add(new ChoferCategoria(fecha, CategoriaTipo.COLECTIVO));
                } else {
                    categorias.add(new ChoferCategoria(fecha, CategoriaTipo.MICROBUS));
                }
            }
            choferes.add(new Chofer(dni, nombre, apellido, nroLicencia, categorias));
            System.out.println("Chofer agregado correctamente.");
        }
    }

    /**
     * Metodo para registrar los vehiculos.
     */
    public static void registrarVehiculos() {
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
                vehiculos.add(new Colectivo(patente, capacidad, pisoDoble));
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

                vehiculos.add(new Minibus(patente, capacidad, tieneBodega, aireAcondicionado));
            }
        }
    }

    /**
     * Metodo para planificar viajes entre ciudades.
     */
    public static void planificarViajes() {
        System.out.println("\n" + "-".repeat(45) + "\nPlanificar viajes\n" + "-".repeat(45));

        if (!viajes.isEmpty()) {
            System.out.println("Ya hay viajes planificados.");
            int opcion = -1;
            while (opcion != 0 && opcion != 1 && opcion != 2) {
                System.out.print("¿Desea agregar mas viajes (1), limpiar y empezar de nuevo (2) o volver al menú (0)? ");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion == 0) {
                        return;
                    } else if (opcion == 1 || opcion == 2) {
                        break;
                    } else {
                        System.out.println("Opcion invalida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("Ingreso inválido. Debe ser '1'/'2'/'0'. Vuelva a intentar.");
                    sc.nextLine();
                }
            }
            if (opcion == 2) {
                viajes.clear();
                System.out.println("Viajes anteriores eliminados.");
            }
        }

        System.out.print("Ingrese la cantidad de ciudades a registrar: ");
        int numCiudades = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numCiudades; i++) {
            System.out.println("\nRegistro de ciudad N°" + (i + 1) + "/" + numCiudades);

            String nombreCiudad;
            Provincia provincia = null;

            while (true) {
                System.out.print("Nombre de la ciudad: ");
                nombreCiudad = sc.nextLine();

                boolean existe = false;
                for (Ciudad c : ciudades) {
                    if (c.getNombre().equalsIgnoreCase(nombreCiudad)) {
                        existe = true;
                        break;
                    }
                }

                if (existe) {
                    System.out.println("Esta ciudad ya fue registrada. Ingrese otra.");
                } else {
                    break;
                }
            }

            System.out.println("Provincias disponibles:");
            for (Provincia p : Provincia.values()) {
                System.out.println("- " + p);
            }

            while (provincia == null) {
                System.out.print("Ingrese la provincia (como se encuentra en la lista): ");
                try {
                    provincia = Provincia.valueOf(sc.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Provincia invalida. Intente de nuevo.");
                }
            }

            ciudades.add(new Ciudad(nombreCiudad, provincia));
        }

        System.out.print("Ingrese la cantidad de viajes a planificar: ");
        int v = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < v; i++) {
            System.out.println("\nPlanificar viaje N°" + (i + 1) + "/" + v + ":");

            System.out.print("Ingrese la fecha: ");
            String fecha = sc.nextLine();

            System.out.print("Ingrese el horario de salida: ");
            String horarioSalida = sc.nextLine();

            System.out.print("Ingrese el horario de llegada: ");
            String horarioLlegada = sc.nextLine();

            Ciudad ciudadOrigen = null;
            while (ciudadOrigen == null) {
                System.out.println("Ciudades disponibles para origen:");
                for (int j = 0; j < ciudades.size(); j++) {
                    System.out.println((j + 1) + ". " + ciudades.get(j).getNombre() + ", " + ciudades.get(j).getProvincia());
                }
                System.out.print("Seleccione el numero de la ciudad de origen: ");
                int indiceOrigen = sc.nextInt();
                sc.nextLine();

                if (indiceOrigen >= 1 && indiceOrigen <= ciudades.size()) {
                    ciudadOrigen = ciudades.get(indiceOrigen - 1);
                } else {
                    System.out.println("Numero invalido. Intente nuevamente.");
                }
            }

            List<Ciudad> ciudadesDestinoDisponibles = new ArrayList<>();
            for (Ciudad ciudad : ciudades) {
                boolean esIgualOrigen = ciudad.getNombre().equalsIgnoreCase(ciudadOrigen.getNombre()) && ciudad.getProvincia() == ciudadOrigen.getProvincia();
                boolean yaUsadaComoDestino = false;
                for (Viaje viaje : viajes) {
                    if (ciudad.getNombre().equalsIgnoreCase(viaje.getDestino().getNombre()) && ciudad.getProvincia() == viaje.getDestino().getProvincia()) {
                        yaUsadaComoDestino = true;
                        break;
                    }
                }

                if (!esIgualOrigen && !yaUsadaComoDestino) {
                    ciudadesDestinoDisponibles.add(ciudad);
                }
            }

            if (ciudadesDestinoDisponibles.isEmpty()) {
                System.out.println("No hay mas ciudades registradas para planificar viajes.");
                return;
            }

            Ciudad ciudadDestino = null;
            while (ciudadDestino == null) {
                System.out.println("Ciudades disponibles para destino:");
                for (int j = 0; j < ciudadesDestinoDisponibles.size(); j++) {
                    System.out.println((j + 1) + ". " + ciudadesDestinoDisponibles.get(j).getNombre() + ", " + ciudadesDestinoDisponibles.get(j).getProvincia());
                }
                System.out.print("Seleccione el numero de la ciudad de destino: ");
                int indiceDestino = sc.nextInt();
                sc.nextLine();

                if (indiceDestino >= 1 && indiceDestino <= ciudadesDestinoDisponibles.size()) {
                    ciudadDestino = ciudadesDestinoDisponibles.get(indiceDestino - 1);
                } else {
                    System.out.println("Numero invalido. Intente nuevamente.");
                }
            }

            Viaje nuevoViaje = new Viaje(fecha, horarioSalida, horarioLlegada, ciudadOrigen, ciudadDestino,null,null);
            viajes.add(nuevoViaje);
            System.out.println("Viaje planificado.");
        }
    }

    /**
     * Metodo para asociar un vehiculo y un chofer a cada viaje.
     */
    public static void asociarVehiculoYChofer() {
        System.out.println("\n" + "-".repeat(45) + "\nAsociar vehículo y chofer a cada viaje\n" + "-".repeat(45));
        
        if (choferes.isEmpty()) {
            System.out.println("No hay choferes registrados.");
            int opcion = 0;

            while (opcion != 1 && opcion != 2) {
                System.out.println("Seleccione una opcion:\n1. Volver al menu principal\n2. Ir a registrar choferes");

                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 

                    if (opcion == 1) {
                        return; 
                    } else if (opcion == 2) {
                        registrarChoferes();
                        return; 
                    } else {
                        System.out.println("Opción invalida. Debe ser '1'/'2'. Vuelva a intentar.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser 1 o 2. Vuelve a intentar.");
                    sc.nextLine(); 
                }
            }
        }

        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            int opcion = 0;

            while (opcion != 1 && opcion != 2) {
                System.out.println("Seleccione una opcion:\n1. Volver al menu principal\n2. Ir a registrar vehiculos");

                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 

                    if (opcion == 1) {
                        return; 
                    } else if (opcion == 2) {
                        registrarVehiculos();
                        return; 
                    } else {
                        System.out.println("Opción invalida. Debe ser '1'/'2'. Vuelva a intentar.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser 1 o 2. Vuelve a intentar.");
                    sc.nextLine(); 
                }
            }
        }

        if (viajes.isEmpty()) {
            System.out.println("No hay viajes planificados.");
            int opcion = 0;

            while (opcion != 1 && opcion != 2) {
                System.out.println("Seleccione una opcion:\n1. Volver al menú principal\n2. Ir a planificar viajes");

                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine(); 

                    if (opcion == 1) {
                        return; 
                    } else if (opcion == 2) {
                        planificarViajes();
                        return; 
                    } else {
                        System.out.println("Opción invalida. Debe ser '1'/'2'. Vuelva a intentar.");
                    }
                } else {
                    System.out.println("Ingreso invalido. Debe ser 1 o 2. Vuelve a intentar.");
                    sc.nextLine(); 
                }
            }
        }

        System.out.println("Lista de choferes disponibles:\n" + choferes);
        System.out.println("Ingrese el numero numero de DNI:");
        long dni = sc.nextLong();
        sc.nextLine();

        Chofer chofer=null;
        for (int i = 0; i < choferes.size(); i++){
            if(choferes.get(i).getDni()==dni){
                chofer = choferes.get(i);
                System.out.println("Chofer guardado,continue.");
            }
        }

        System.out.println("Lista de Vehiculos:\n" + vehiculos);
        System.out.println("Ingrese el numero de patente");  
        String patente = sc.nextLine();
        Vehiculo vehiculo = null;
        patente= patente.replaceAll("[\\s\\-]", "");
        for (int i=0;i<vehiculos.size();i++){
            if(patente.equalsIgnoreCase(vehiculos.get(i).getPatente().toLowerCase().trim())){
                vehiculo = vehiculos.get(i);
                System.out.println("Vehiculo guardado,continue.");
            }
        }
    }

    /**
     * Metodo para mostrar los viajes programados con informacion detallada.
     */
    public static void viajesProgramados() {
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
    public static void informeViajesARealizarColectivo() {
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
    public static void informeViajesRealizadosColectivo() {
        System.out.println("\n" + "-".repeat(45) + "\nInforme de cantidad de viajes realizados por cada chofer de colectivos\n" + "-".repeat(45));
        // Mapa para contar los viajes por chofer
        Map<Chofer, Integer> viajesPorChofer = new HashMap<>();

        for (Viaje viaje : viajes) {
            Vehiculo vehiculo = viaje.getVehiculo();
            Chofer chofer = viaje.getChofer();

            if (vehiculo instanceof Colectivo) {
                viajesPorChofer.put(chofer, viajesPorChofer.getOrDefault(chofer, 0) + 1);
            }
        }

        if (viajesPorChofer.isEmpty()) {
            System.out.println("No hay viajes realizados por choferes de colectivos.");
        } else {
            for (Map.Entry<Chofer, Integer> entry : viajesPorChofer.entrySet()) {
                Chofer chofer = entry.getKey();
                int cantidad = entry.getValue();
                System.out.println("Chofer: " + chofer.getNombre() + " - Viajes realizados: " + cantidad);
            }
        }
    }

    public static void MarcarComoRealizado(){
        System.out.println("\n" + "-".repeat(45) + "\nViajes \n" + "-".repeat(45));
        Viaje viajeListo = null;
        for (Viaje viaje : viajes) {
            if (viaje.atributosNulos()) {
                continue;
            } else {
                viajesRealizados.add(viaje);
            }
        }
        System.out.println("Viajes disponibles:");
        for(int i=0;i<viajesRealizados.size();i++){
            String choferViajeDisponible = viajesRealizados.get(i).getChofer().getNombre();
            long dniViajeDisponible = viajesRealizados.get(i).getChofer().getDni();
            CategoriaTipo categoriaViajeDisponible = viajesRealizados.get(i).getChofer().getCategorias().getCategoria();

            System.out.println(choferViajeDisponible + "\n" + "=".repeat(30) + "\nViajes para marcar como realizados:\n" + "=".repeat(30));
            System.out.println("");
            long dni = sc.nextLong();

            
        }
    } 
}