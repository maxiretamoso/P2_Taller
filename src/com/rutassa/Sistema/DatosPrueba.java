package com.rutassa.Sistema;
import java.util.ArrayList;
import java.util.List;
import com.rutassa.Categoria.*;
import com.rutassa.Chofer.*;
import com.rutassa.Ubicacion.*;
import com.rutassa.Vehiculo.*;
import com.rutassa.Vehiculo.tipoVehiculo.*;
import com.rutassa.Viaje.*;

public class DatosPrueba {

    public static List<Ciudad> crearCiudades() {
        Ciudad c1 = new Ciudad("Rosario", Provincia.SANTA_FE, new ArrayList<>(), new ArrayList<>());
        Ciudad c2 = new Ciudad("Cordoba", Provincia.CORDOBA, new ArrayList<>(), new ArrayList<>());
        Ciudad c3 = new Ciudad("Concordia", Provincia.ENTRE_RIOS, new ArrayList<>(), new ArrayList<>());
        Ciudad c4 = new Ciudad("Resistencia", Provincia.CHACO, new ArrayList<>(), new ArrayList<>());    

        List<Ciudad> ciudades = new ArrayList<>();
        ciudades.add(c1);
        ciudades.add(c2);
        ciudades.add(c3);
        ciudades.add(c4);

        return ciudades;
    }

    public static List<Vehiculo> crearVehiculos() {
        Colectivo colectivo1 = new Colectivo("AB123CD", 50, new ArrayList<>(), false);
        Colectivo colectivo2 = new Colectivo("EF456GH", 80, new ArrayList<>(), true);

        Minibus minibus1 = new Minibus("IJ789KL", 20, new ArrayList<>(), true, true);
        Minibus minibus2 = new Minibus("MN234OP", 18, new ArrayList<>(), false, false);

        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(colectivo1);
        vehiculos.add(colectivo2);
        vehiculos.add(minibus1);
        vehiculos.add(minibus2);

        return vehiculos;
    }

    public static List<Chofer> crearChoferes() {
        Categoria catColectivo = new Categoria(CategoriaTipo.COLECTIVO, new ArrayList<>());
        Categoria catMinibus = new Categoria(CategoriaTipo.MINIBUS, new ArrayList<>());

        List<ChoferCategoria> categoriasChofer1 = new ArrayList<>();
        List<Viaje> viajesChofer1 = new ArrayList<>();
        Chofer chofer1 = new Chofer(12345678, "Juan", "Perez", "1234", categoriasChofer1, viajesChofer1);
        ChoferCategoria choferCat1 = new ChoferCategoria("31/12/28", catColectivo);
        chofer1.getCategorias().add(choferCat1); 

        List<ChoferCategoria> categoriasChofer2 = new ArrayList<>();
        List<Viaje> viajesChofer2 = new ArrayList<>();
        Chofer chofer2 = new Chofer(2345678, "Maria", "Gomez", "5678", categoriasChofer2, viajesChofer2);
        ChoferCategoria choferCat2 = new ChoferCategoria("30/06/27", catMinibus);
        chofer2.getCategorias().add(choferCat2);

        List<ChoferCategoria> categoriasChofer3 = new ArrayList<>();
        List<Viaje> viajesChofer3 = new ArrayList<>();
        Chofer chofer3 = new Chofer(3456789, "Carlos", "Lopez", "1312", categoriasChofer3, viajesChofer3);
        ChoferCategoria choferCat3 = new ChoferCategoria("29/12/26", catColectivo);
        chofer3.getCategorias().add(choferCat3);

        List<Chofer> choferes = new ArrayList<>();
        choferes.add(chofer1);
        choferes.add(chofer2);
        choferes.add(chofer3);

        return choferes;
    }

    public static List<Viaje> crearViajes(List<Ciudad> ciudades, List<Vehiculo> vehiculos, List<Chofer> choferes) {
        Ciudad c1 = ciudades.get(0);
        Ciudad c2 = ciudades.get(1);
        Ciudad c3 = ciudades.get(2);
        Ciudad c4 = ciudades.get(3); 

        Colectivo colectivo1 = null;
        Colectivo colectivo2 = null;
        Minibus minibus1 = null;
        

        for (Vehiculo v : vehiculos) {
            if (v instanceof Colectivo && colectivo1 == null) colectivo1 = (Colectivo) v;
            if (v instanceof Colectivo && colectivo2 == null) colectivo2 = (Colectivo) v;
            if (v instanceof Minibus && minibus1 == null) minibus1 = (Minibus) v;
        }

        Chofer chofer1 = choferes.get(0);
        Chofer chofer2 = choferes.get(1);
        Chofer chofer3 = choferes.get(2);

        Viaje viaje1 = new Viaje();
        viaje1.setOrigen(c1);
        viaje1.setDestino(c2);
        viaje1.setFecha("23/12/25");
        viaje1.setHorarioSalida("08:00");
        viaje1.setHorarioLlegada("12:00");
        viaje1.setChofer(chofer1);
        viaje1.setVehiculo(colectivo1);
        
        Viaje viaje2 = new Viaje();
        viaje2.setOrigen(c2);
        viaje2.setDestino(c1);
        viaje2.setFecha("12/09/25");
        viaje2.setHorarioSalida("12:00");
        viaje2.setHorarioLlegada("18:00");
        viaje2.setChofer(chofer2);
        viaje2.setVehiculo(minibus1);

        Viaje viaje3 = new Viaje();
        viaje3.setOrigen(c3);
        viaje3.setDestino(c4);
        viaje3.setFecha("12/04/25");
        viaje3.setHorarioSalida("10:00");
        viaje3.setHorarioLlegada("20:00");
        viaje3.setChofer(chofer3);
        viaje3.setVehiculo(colectivo2);

        Viaje viaje4 = new Viaje();
        viaje4.setOrigen(c1);
        viaje4.setDestino(c3);
        viaje4.setFecha("12/04/24");
        viaje4.setHorarioSalida("05:00");
        viaje4.setHorarioLlegada("12:00");
        viaje4.setChofer(chofer3);
        viaje4.setVehiculo(colectivo2);

        colectivo1.setVehiculoViajes(viaje1);
        chofer1.getViajesChofer().add(viaje1);
        c1.getOrigenesViajes().add(viaje1);
        c2.getDestinosViajes().add(viaje1);

        minibus1.setVehiculoViajes(viaje2);
        chofer2.getViajesChofer().add(viaje2);
        c2.getOrigenesViajes().add(viaje2);
        c1.getDestinosViajes().add(viaje2);

        colectivo1.setVehiculoViajes(viaje3);
        chofer3.getViajesChofer().add(viaje3);
        c3.getOrigenesViajes().add(viaje3);
        c4.getDestinosViajes().add(viaje3);

        colectivo1.setVehiculoViajes(viaje4);
        chofer3.getViajesChofer().add(viaje4);
        c1.getOrigenesViajes().add(viaje4);
        c3.getDestinosViajes().add(viaje4);

        List<Viaje> viajes = new ArrayList<>();
        viajes.add(viaje1);
        viajes.add(viaje2);
        viajes.add(viaje3); 
        viajes.add(viaje4);

        return viajes;
    }
}