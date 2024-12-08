package reserva;

import java.time.LocalDate;

import inmueble.FormaDePago;
import inmueble.Inmueble;
import usuario.Propietario;
import usuario.Usuario;

public class Reserva {
	private Usuario inquilino;
	private Inmueble inmueble;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private Concretable estado;

	public Reserva(Usuario inquilino, Inmueble inmueble) {
		this.inquilino = inquilino;
		this.inmueble = inmueble;
		this.estado = new ReservaPendiente(); // Estado inicial, vincula la reserva con su estado...
	}

	public Reserva() {
	}

	public boolean fueHechoCheckOutPara(Usuario u) {
		return (this.propietarioAsigando() == u) && this.estaFinalizada(); // chequear.
	}

	public Propietario propietarioAsigando() {
		return this.inmueble.getPropietario();
	}

	public void confirmarReserva() {
		this.estado.confirmar(this);
	}

	public void finalizarReserva() {
		this.estado.finalizar(this);

	}

	public void cancelarReserva() {
		this.estado.cancelar(this);
		// inmueble.verificarEncoladas(fechaEntrada);
	}

	public boolean estaCancelada() {
		return this.estado.esCancelada();
	}

	public boolean estaPendiente() {

		return estado.esPendiente();
	}

	public boolean estaConfirmada() {

		return estado.esConfirmada();
	}

	public boolean estaFinalizada() {

		return estado.esFinalizada();
	}

	public boolean interfiereCon(LocalDate nuevaFechaEntrada, LocalDate nuevaFechaSalida) {
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

	public Inmueble inmueble() {

		return this.getInmueble();
	}

	public String mailInquilino() {

		return this.inquilino.getEmail();
	}

	public String mailPropietario() {
		return this.inmueble.getPropietario().getEmail();
	}

	public Usuario getPropietario() {
		return inmueble.getPropietario();
	}

	public String ciudadDeReserva() {

		return this.getInmueble().getCiudad();
	}

	public boolean esCondicionalParaElInmueble(LocalDate fechaIncio, LocalDate fechaFin) {

		return this.getInmueble().estaDisponibleParaLasFechas(fechaIncio, fechaFin);
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

	public Concretable getEstado() {
		return estado;
	}

	public void setEstado(Concretable estado) {
		this.estado = estado;
	}

	public void setInquilino(Usuario inquilino) {
		this.inquilino = inquilino;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public void cambiarEstado(Concretable mockEstado) {
		this.setEstado(mockEstado);

	}

}
