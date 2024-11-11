package reserva;

import java.sql.Date;
import java.time.LocalDate;

import tpgrupal.Inmueble;
import usuario.Inquilino;
import usuario.Propietario;
import usuario.Usuario;

public class Reserva {
	private Inquilino inquilino;
	private Inmueble inmueble;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private String formaDePago;
	private EstadoReserva estado;

	public Reserva(Inquilino inquilino, Inmueble inmueble) {
		this.inquilino = inquilino;
		this.inmueble = inmueble;
		this.estado = new ReservaPendiente(); // Estado inicial
	}

	public Propietario getPropietario() {
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

	public void cancelarReserva() {
		this.estado.cancelar();
	}

	private void enviarConfirmacion() {
	};

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

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
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

	public void establecerModoDePago(String formaDePago) {

		this.setFormaDePago(formaDePago);

	}

	public Inmueble inmueble() {

		return this.getInmueble();
	}

	public String mailInquilino() {

		return this.inquilino.getEmail();
	}

	public String mailPropietario() {
		// TODO Auto-generated method stub
		return  this.inmueble.getPropietario().getEmail();
	}
}
