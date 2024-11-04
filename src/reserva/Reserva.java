package reserva;

import java.sql.Date;

import tpgrupal.Inmueble;
import usuario.Inquilino;
import usuario.Usuario;

public class Reserva {
    private Inquilino inquilino;
    private Inmueble inmueble;
    private Date checkIn;
    private Date checkOut;
    private String formaDePago;
	private EstadoReserva estado;

    public Reserva(Inquilino inquilino, Inmueble inmueble, Date fechaEntrada, 
    		Date fechaSalida, String formaDePago) {
        this.inquilino = inquilino;
        this.inmueble = inmueble;
        this.checkIn = fechaEntrada;
        this.checkOut = fechaSalida;
        this.formaDePago = formaDePago;
        this.estado = new ReservaPendiente(); // Estado inicial
    }

    // Método para que el propietario confirme la reserva
    public void confirmarReserva() {
        this.estado = new ReservaConfirmada();
    }

 // Método para que el propietario cancelar la reserva
    public void cancelarReserva() {
        this.estado = new ReservaCancelada();
    }
    // Enviar confirmación de reserva al inquilino
    private void enviarConfirmacion(){};

    // Calcular costo total en base a los días y períodos
    public double calcularCostoTotal(){return 0d; };

    // Otros getters y setters
    public Inmueble getInmueble() { return inmueble; }
    public Inquilino getInquilino() { return inquilino; }
    public String getFormaPago() { return formaDePago; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
}
