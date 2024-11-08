package tpgrupal;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import politicaCancelacion.PoliticaCancelacion;
import ranking.Rankeable;
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
	private LocalDate checkIn;
	private LocalDate checkOut;
	private List<Servicio> servicios;
	// Enum FormaDePago
	private Set<FormaDePago> formasDePago;
	// Lista de períodos con precios variables
	private Set<PrecioPorPeriodo> preciosPorPeriodos;
	private double precioPorDia;
	private PoliticaCancelacion tipoDeCancelacion;

	public double calcularPrecioTotal(LocalDate fechaInicio, LocalDate fechaFin) {
		double precioTotal = 0;
		LocalDate fechaActual = fechaInicio;

		while (!fechaActual.isAfter(fechaFin)) {
			// algunos dias podrian ser estandar y otros de periodos especiales
			precioTotal += obtenerPrecioParaFecha(fechaActual);
			fechaActual = fechaActual.plusDays(1);
		}

		return precioTotal;
	}

	private double obtenerPrecioParaFecha(LocalDate fecha) {
		return preciosPorPeriodos.stream().filter(periodo -> periodo.incluye(fecha))
				.map(PrecioPorPeriodo::getPrecioPorDia).findFirst().orElse(precioEstandar);
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Set<FormaDePago> getFormasDePago() {
		return formasDePago;
	}

	public void setFormasDePago(Set<FormaDePago> formasDePago) {
		this.formasDePago = formasDePago;
	}

	public Set<PrecioPorPeriodo> getPreciosPorPeriodos() {
		return preciosPorPeriodos;
	}

	public void setPreciosPorPeriodos(Set<PrecioPorPeriodo> preciosPorPeriodos) {
		this.preciosPorPeriodos = preciosPorPeriodos;
	}

	public PoliticaCancelacion getTipoDeCancelacion() {
		return tipoDeCancelacion;
	}

	public void setTipoDeCancelacion(PoliticaCancelacion tipoDeCancelacion) {
		this.tipoDeCancelacion = tipoDeCancelacion;
	}

	public void cambiarPolitica(PoliticaCancelacion p) { // doble encapsulamiento
		this.setTipoDeCancelacion(p);

	}

	public double getPrecioPorDia() {
		return precioPorDia;
	}

	public void setPrecioPorDia(double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	public void cambiarPrecio(Double monto) {
		this.setPrecioPorDia(monto);

	}

	public Double precio() {
		return this.getPrecioPorDia();
	}

}
