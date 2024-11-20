package reserva;

import java.time.LocalDate;

import Inmueble.Inmueble;
import tpgrupal.FormaDePago;
import usuario.Propietario;
import usuario.Usuario;

public class Reserva {
	private Usuario inquilino;
	private Inmueble inmueble;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private EstadoReserva estado;

	public Reserva(Usuario inquilino, Inmueble inmueble) {
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

	public double calcularPenalidadPorCancelacion(Reserva reserva) {
		// le delega la responsabilidad de calcular la penalidad al inmueble
		return inmueble.calcularPenalidadPorCancelacion(reserva);
	}

	public double getPrecioTotal() {
		// retorna el valor total de la reserva.
		// le delega el calculo al inmbueble reservado
		return inmueble.getPrecio(fechaEntrada, fechaSalida);
	}

	public void cancelarReserva() {
		this.estado.cancelar();
		//inmueble.verificarEncoladas(fechaEntrada);
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

	public Usuario getInquilino() {
		return inquilino;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(FormaDePago formaDePago) {
		this.formaDePago = formaDePago;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public void setInquilino(Usuario inquilino) {
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
/*
	public String mailPropietario() {
		// TODO Auto-generated method stub
		return this.inmueble.getPropietario().getEmail();
	}
*/
	public String ciudadDeReserva() {

		return this.getInmueble().getCiudad();
	}

	public boolean esCondicionalParaElInmueble(LocalDate fechaIncio, LocalDate fechaFin) {

		return this.getInmueble().estaDisponibleParaLasFechas(fechaIncio, fechaFin);
	}

}
