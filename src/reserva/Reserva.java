package reserva;

import java.sql.Date;

import tpgrupal.Inmueble;
import usuario.Inquilino;
import usuario.Propietario;
import usuario.Usuario;

public class Reserva {
	private Inquilino inquilino;
	private Inmueble inmueble;
	private Date fechaEntrada;
	private Date fechaSalida;
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
		this.inmueble.noDisponible
		this.estado.confirmar();
	}

	public boolean estaDisponible() {
		
		
		
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
}
