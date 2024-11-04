package tpgrupal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import usuario.Propietario;

public class Inmueble {
	private Propietario propietario;
    private String tipo;
    private double superficie;
    private String pais;
    private String ciudad;
    private String direccion;
    private int capacidad;
    private List<String> fotos; // Máximo de 5 fotos
    private String checkIn;
    private String checkOut;
    private List<Servicio> servicios;
    
    //Enum FormaDePago
    private List<FormaDePago> formasDePago; 
    /////////////////////////////////////////
    
    // Lista de períodos con precios variables
    private List<PrecioPorPeriodo> preciosPorPeriodos;
	private double precioEstandar; 
    ////////////////////////////////////////////////
    
	public Inmueble(String tipo, double superficie, String pais, String ciudad, String direccion,
			List<Servicio> servicios, int capacidad, List<String> fotos,
			String checkIn, String checkOut, List<FormaDePago> formasDePago,
			List<PrecioPorPeriodo> preciosPorPeriodos, int precioEstandar) {
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
		this.preciosPorPeriodos = preciosPorPeriodos;
		this.precioEstandar = precioEstandar;
	}
    
    public double calcularPrecioTotal(LocalDate fechaInicio, LocalDate fechaFin) {
        double precioTotal = 0;
        LocalDate fechaActual = fechaInicio;

        while (!fechaActual.isAfter(fechaFin)) {
        //algunos dias podrian ser estandar y otros de periodos especiales
            precioTotal += obtenerPrecioParaFecha(fechaActual);
            fechaActual = fechaActual.plusDays(1);
        }

        return precioTotal;
    }

    private double obtenerPrecioParaFecha(LocalDate fecha) {
        return preciosPorPeriodos.stream()
                .filter(periodo -> periodo.incluye(fecha))
                .map(PrecioPorPeriodo::getPrecioPorDia)
                .findFirst()
                .orElse(precioEstandar);
    }
    

    // Getters y setters
    public Propietario getPropietario() { return propietario; }
    public String getTipo() { return tipo; }
    public double getSuperficie() { return superficie; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public String getDireccion() { return direccion; }
    public int getCapacidad() { return capacidad; }
    public List<String> getFotos() { return fotos; }
	public String getCheckIn() { return checkIn; }
	public String getCheckOut() { return checkOut; }
	public List<Servicio> getServicios() { return servicios; }
	public List<FormaDePago> getFormasDePago() { return formasDePago; }
	public List<PrecioPorPeriodo> getpreciosPorPeriodos() { return preciosPorPeriodos; }
}
