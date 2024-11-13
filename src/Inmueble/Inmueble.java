package Inmueble;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import politicaCancelacion.PoliticaCancelacion;
import reserva.Reserva;
import tpgrupal.FormaDePago;
import tpgrupal.PrecioPorPeriodo;
import tpgrupal.Servicio;
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
	private LocalDate checkIn; // 9 am
	private LocalDate checkOut; // 10pm
	private List<Servicio> servicios;
	// Enum FormaDePago
	private Set<FormaDePago> formasDePago;
	// Lista de períodos con precios variables
	private Set<PrecioPorPeriodo> preciosPorPeriodos;
	private double precioPorDia; // un valor por defecto(por si no es un periodo existente)
	private PoliticaCancelacion politicaDeCancelacion;
	private Set<Reserva> reservas;
	
	private Set<Reserva> reservasEncoladas;

	public double calcularPrecioParaRango(LocalDate fechaInicio, LocalDate fechaFin) {
		// calcula el precio de un rango de dias
		// algunos dias podrian ser estandar y otros de periodos especiales
		return fechaInicio.datesUntil(fechaFin.plusDays(1)).mapToDouble(this::obtenerPrecioParaFecha).sum();
	}

	private double obtenerPrecioParaFecha(LocalDate fecha) {
		return preciosPorPeriodos.stream().filter(periodo -> periodo.incluye(fecha))
				.map(PrecioPorPeriodo::getPrecioPorDia).findFirst().orElse(precioPorDia);
	}

	public double calcularPenalidadPorCancelacion(LocalDate fechaEntrada, LocalDate fechaSalida, double precioTotal) {
		// le delega la responsabilidad de calcular la penalidad a la
		// politicaDeCancelacion
		return this.politicaDeCancelacion.calcularPenalidad(fechaEntrada, fechaSalida, precioTotal);
	}

	public boolean estaDisponibleParaLasFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		// Método para verificar si el rango de fechas no interfiere con las reservas
		// existentes

		return reservas.stream().noneMatch(reserva -> reserva.reservaInterfiereCon(fechaEntrada, fechaSalida));
	}

	public void encolar(Reserva reserva) {
		reservasEncoladas.add(reserva);
		
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
		return politicaDeCancelacion;
	}

	public void setTipoDeCancelacion(PoliticaCancelacion tipoDeCancelacion) {
		this.politicaDeCancelacion = tipoDeCancelacion;
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

	public void verificarEncoladas(LocalDate fechaEntrada) {
		
		
	}


}
