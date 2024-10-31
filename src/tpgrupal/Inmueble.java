package tpgrupal;

import java.util.List;
import java.util.Map;

import usuario.Propietario;

public class Inmueble {
    private String tipo;
    private double superficie;
    private String pais;
    private String ciudad;
    private String direccion;
    private List<String> servicios;
    private int capacidad;
    private List<String> fotos; // Máximo de 5 fotos
    private String checkIn;
    private String checkOut;
    
    //Podria ser un enum?
    private List<String> formasDePago; 
    /////////////////////////////////////////7
    
    private Map<String, Double> preciosPorPeriodo; // Almacena precios por distintos períodos
    private Propietario propietario;
    
    public Inmueble(String tipo, double superficie, String pais, String ciudad, String direccion,
                    List<String> servicios, int capacidad, List<String> fotos,
                    String checkIn, String checkOut, List<String> formasDePago,
                    Map<String, Double> preciosPorPeriodo) {
        this.tipo = tipo;
        this.superficie = superficie;
        this.pais = pais;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.servicios = servicios;
        this.capacidad = capacidad;
        this.fotos = fotos;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.formasDePago = formasDePago;
        this.preciosPorPeriodo = preciosPorPeriodo;
    }

    // Getters y setters
    public String getTipo() { return tipo; }
    public double getSuperficie() { return superficie; }
    public String getDireccion() { return direccion; }
    public List<String> getServicios() { return servicios; }
    public int getCapacidad() { return capacidad; }
    public List<String> getFotos() { return fotos; }
    public Map<String, Double> getPrecios() { return preciosPorPeriodo; }
    public Propietario getPropietario() { return propietario; }
    // Agregar métodos adicionales para verificar disponibilidad, calcular precios, etc.

	public String getPais() {
		return pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}
}
