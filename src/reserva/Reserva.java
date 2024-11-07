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

    public void confirmarReserva() {
        this.estado = new ReservaConfirmada();
    }

    public void cancelarReserva() {
        this.estado = new ReservaCancelada();
    }

    private void enviarConfirmacion(){};


    public double calcularCostoTotal(){return 0d; };

    public Inmueble getInmueble() { return inmueble; }
    public Inquilino getInquilino() { return inquilino; }
    public String getFormaPago() { return formaDePago; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
}
