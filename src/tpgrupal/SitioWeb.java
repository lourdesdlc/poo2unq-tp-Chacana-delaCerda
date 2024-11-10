package tpgrupal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Observadores.BajaDePrecio;
import filtroDeBusqueda.CriterioBusqueda;
import ranking.Rankeable;
import ranking.Ranking;
import usuario.Propietario;
import usuario.Usuario;

public class SitioWeb {
	private AdminWeb admin;
	private Set<Inmueble> inmuebles;
	private Set<Usuario> usuarios;
	private Set<Ranking> rankeados;
	private Set<Interesado> interesados;

	public void registrarUsuario(Usuario usuario) {

		usuario.ingresarAlSitio(this);// vinculacion a mismo sitioWeb
		usuarios.add(usuario);
	}

	// pensar en clase Validador.
	public void registrarInmuebleDe(Inmueble inmueble, Propietario propietario) {
		if (usuarios.contains(propietario)) {
			inmuebles.add(inmueble);
		} else {
			this.registrarUsuario(propietario);
			inmuebles.add(inmueble);
		}

	}

	public List<Inmueble> buscarInmuebles(CriterioBusqueda criterio) {
		// Filtra la lista de inmuebles usando el criterio recibido como parámetro
		return criterio.buscar(inmuebles);
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

	// OBSERVER COMPORTAMIENTO

	public void registrarIntereado(Interesado interesado) {

		this.interesados.add(interesado);

	}

	public void desubscribirIntereado(Interesado interesado) {
		this.interesados.remove(interesado);

	}

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

	public void notificarReservaDeInmueble(Inmueble i) { // Este es el general? lo recbien todos?
		String mensaje = "Se reservo el Inmueble " + i.getTipo();
		enviarNotificacion(mensaje, i.getTipo());
	}

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

}