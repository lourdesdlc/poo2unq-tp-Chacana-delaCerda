package sitioWeb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Inmueble.Inmueble;
import Observadores.Interesado;
import exepciones.FiltroException;
import exepciones.UsuarioException;
import filtroDeBusqueda.FiltroCompuesto;
import notificaciones.EmailSender;
import ranking.Ranking;
import reserva.Reserva;
import usuario.Propietario;
import usuario.Usuario;

public class SitioWeb {
	private AdminWeb admin;
	private Set<Inmueble> inmuebles = new HashSet<>();
	private Set<Usuario> usuarios = new HashSet<>();
	private Set<Ranking> rankeados = new HashSet<>();
	private Set<Interesado> interesados = new HashSet<>();
	private Set<Reserva> reservas = new HashSet<>();
	private EmailSender email;

	// GETTERS
	public void agregarRanking(Ranking ranking) {
		this.rankeados.add(ranking);

	}

	public AdminWeb getAdmin() {
		return admin;
	}

	public void setAdmin(AdminWeb admin) {
		this.admin = admin;
	}

	public Set<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(Set<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Ranking> getRankingsRecibidos() {
		return rankeados;
	}

	public void setRankingsRecibidos(Set<Ranking> rankingsRecibidos) {
		this.rankeados = rankingsRecibidos;

	}

	public void registrarUsuario(Usuario u) {
		u.asignarWeb(this);
		this.registrarNuevoUsuario(u);
	}

	private void registrarNuevoUsuario(Usuario u) {
		usuarios.add(u);
	}

	private boolean esUsuarioRegistrado(Usuario u) {
		return usuarios.contains(u);
	}

	// pensar en clase Validador.
	public void registrarInmuebleDe(Inmueble inmueble, Propietario propietario) {
		if (esUsuarioRegistrado(propietario)) {
			inmuebles.add(inmueble);
		} else {
			throw new UsuarioException("Error, debes registrarte en la Web para publicar");
		}
	}

	public List<Inmueble> buscarInmuebles(FiltroCompuesto filtro) { // testear

		if (filtro.tieneFiltrosObligatorios())
			return inmuebles.stream().filter(inmueble -> filtro.cumple(inmueble)).toList();
		else {
			throw new FiltroException("Error, Falta un filtro obligatorio");
		}
	}

	public void mostrarDetallesInmueble(Inmueble inmueble) {
	}

	public void validarRankingPara(Usuario u, String comentario, int puntaje, String categoria) {
		admin.validarRanking(u, comentario, puntaje, categoria);

	}

	// acoplamiento de mensaje?
	public void validarRankingPara(Inmueble i, String comentario, int puntaje, String categoria) {
		admin.validarRankingDeInmueble(i, comentario, puntaje, categoria);

	}

	public void registrarIntereado(Interesado interesado) {

		this.interesados.add(interesado);

	}

	public void desubscribirIntereado(Interesado interesado) {
		this.interesados.remove(interesado);

	}

	// OBSERVER COMPORTAMIENTO
	// Método general para enviar notificaciones
	private void enviarNotificacion(String mensaje, String tipoInmueble) {
		interesados.stream().forEach(interesado -> interesado.recibirNotificacionDe(mensaje, tipoInmueble));
	}

	public void notificarBajaDePrecio(Inmueble i, Double monto) { // usar en donde corresponda.
		String mensaje = "No te pierdas esta oferta: Un inmueble " + i.getTipo() + " a tan sólo " + monto + " pesos.";
		enviarNotificacion(mensaje, i.getTipo());

	}

	public void notificarCancelacionDeInmueble(Inmueble i) {
		String mensaje = "El/la " + i.getTipo() + " que te interesa se ha liberado! Corre a reservarlo!";
		enviarNotificacion(mensaje, i.getTipo());

	}

	public void notificarReservaConcretadaDeInmueble(Inmueble i) { // Este es el general? lo recbien todos?
		String mensaje = "Se reservo el Inmueble " + i.getTipo();
		enviarNotificacion(mensaje, i.getTipo());
	}

	// COMPORTAMIENTO DE RESERVAS

	public void solicitarReserva(Reserva reserva) {
		if (esUsuarioRegistrado(reserva.getInquilino())) {
			reserva.getPropietario().evaluarSolicitudDeReserva(reserva);
		} else {
			throw new RuntimeException("Error, Usuario no regitrada");
		}
	}

	public void consolidarReserva(Reserva reserva) {
		if (this.reservas.contains(reserva)) {

			this.reservas.add(reserva);

			reserva.confirmarReserva();
			// email al inquilino
			email.enviarMail(reserva.mailInquilino(), "Reserva Confirmada", reserva);
			// notificacion a obvservadores
			this.notificarReservaConcretadaDeInmueble(reserva.getInmueble());

		} else {
			throw new RuntimeException("Error, Reserva no regitrada");
		}
	}

	public void anularReserva(Reserva reserva) {
		if (this.reservas.contains(reserva)) {
			reserva.cancelarReserva();
			// email al Propietario
			email.enviarMail(reserva.mailPropietario(), "Reserva Cancelada", reserva);
			// notificacion a obvservadores
			this.notificarCancelacionDeInmueble(reserva.getInmueble());

		}

	}

}
