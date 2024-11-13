package reserva;

import java.time.LocalDate;

import Inmueble.Inmueble;
import usuario.Inquilino;
import usuario.Propietario;

public class Reserva {
	private Inquilino inquilino;
	private Inmueble inmueble;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private String formaDePago;
	private EstadoReserva estado;

	private double precioTotal;

	public Reserva(Inquilino inquilino, Inmueble inmueble) {
		this.inquilino = inquilino;
		this.inmueble = inmueble;
		this.estado = new ReservaPendiente(); // Estado inicial
	}

	public Propietario propietarioAsigando() {
		return this.inmueble.getPropietario();
	}

	public void confirmarReserva() {
		this.estado.confirmar();
	}

	public boolean reservaInterfiereCon(LocalDate nuevaFechaEntrada, LocalDate nuevaFechaSalida) {
		// Verificamos si el rango de fechas de la nueva reserva interfiere con el rango
		// actual
		return !nuevaFechaSalida.isBefore(fechaEntrada) && !nuevaFechaEntrada.isAfter(fechaSalida);
	}

	public double calcularPenalidadPorCancelacion(LocalDate fechaEntrada, LocalDate fechaSalida) {
		// le delega la responsabilidad de calcular la penalidad al inmueble
		return inmueble.calcularPenalidadPorCancelacion(fechaEntrada, fechaSalida, precioTotal);
	}

	public double precioTotal() {
		// retorna el valor total de la reserva.
		// le delega el calculo al inmbueble reservado
		return inmueble.calcularPrecioParaRango(fechaEntrada, fechaSalida);
	}

	public void cancelarReserva() {
		this.estado.cancelar();
		inmueble.verificarEncoladas(fechaEntrada);
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double calcularCostoTotal() {
		return 0d;
	};

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Inquilino getInquilino() {
		return inquilino;
	}

	public String getFormaPago() {
		return formaDePago;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public Inmueble inmueble() {

		return this.getInmueble();
	}

	public String mailInquilino() {

		return this.inquilino.getEmail();
	}

	public String mailPropietario() {
		// TODO Auto-generated method stub
		return this.inmueble.getPropietario().getEmail();
	}

	public String ciudadDeReserva() {

		return this.getInmueble().getCiudad();
	}

	public boolean esCondicionalEn(LocalDate fechaIncio, LocalDate fechaFin) {

		return this.getInmueble().estaDisponibleParaLasFechas(fechaIncio, fechaFin);
	}

}
