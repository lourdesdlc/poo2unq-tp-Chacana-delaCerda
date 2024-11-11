package tpgrupal;

import reserva.Reserva;

public interface EmailSender {
	public void enviarMail(String email, String cuerpo, Reserva reserva);
	//Pagina 4 del tp "Debe ser posible enviar por email alguna de las reservas realizadas" 
}
