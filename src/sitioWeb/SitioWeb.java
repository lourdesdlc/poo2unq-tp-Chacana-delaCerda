package sitioWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import filtroDeBusqueda.CriterioBusqueda;
import inmueble.Inmueble;
import inmueble.Servicio;
import inmueble.TipoInmueble;
import ranking.Categoria;
import ranking.TipoRankeable;
import usuario.Usuario;

public class SitioWeb {
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Servicio> serviciosInmuebles = new ArrayList<>();
	private List<TipoInmueble> tiposDeInmueble = new ArrayList<>();
	private List<Categoria> categorias = new ArrayList<>();
	private Set<Inmueble> inmuebles; // tiene sentido que esten los inmuebles publicados, con esta lista podemos
									// obtener mucha informacion, ej todas las reservas, inmueble mas alquilado etc

	public SitioWeb(List<TipoInmueble> tiposDeInmueble, List<Categoria> categorias, List<Servicio> serviciosInmuebles) {
		this.tiposDeInmueble.addAll(tiposDeInmueble);
		this.categorias.addAll(categorias);
		this.serviciosInmuebles.addAll(serviciosInmuebles);

	}

	public void registrarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public List<Inmueble> buscarInmuebles(CriterioBusqueda criterio) {
		return this.getInmuebles().stream().filter(criterio::cumple).collect(Collectors.toList());
	}

	public void mostrarDetallesInmueble(Inmueble inmueble) {

	}

	public List<Categoria> getListaDeCategoriasPara(TipoRankeable tipoRankeable) {
		return categorias.stream().filter(categoria -> categoria.perteneceATipoRankeable(tipoRankeable)).toList();
	}

	public boolean esCategoriaValida(Categoria categoria) {
		// evalua si es una categoria bien construida
		return getListaDeCategoriasPara(categoria.getTipoRankeable()).contains(categoria);
	}

	public void darDeAltaTipoInmueble(TipoInmueble tipoInmueble) {
		this.tiposDeInmueble.add(tipoInmueble);
	}

	public void darDeAltaCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public void darDeAltaServicioInmueble(Servicio servicio) {
		this.serviciosInmuebles.add(servicio);
	}

	public void eliminarTipoInmueble(TipoInmueble tipoInmueble) {
		this.tiposDeInmueble.remove(tipoInmueble);
	}

	public void eliminarCategoria(Categoria categoria) {
		this.categorias.remove(categoria);
	}

	public void eliminarServicioInmueble(Servicio servicio) {
		this.serviciosInmuebles.remove(servicio);
	}

	public List<Usuario> topTenInquilinos() {
		// FALTA IMPLEMENTAR
		return null;
	}

	public List<Inmueble> inmueblesLibres() {
		// FALTA IMPLEMENTAR
		return null;
	}

	public double tasaOcupacion() {
		// FALTA IMPLEMENTAR
		return 0;
	}

	// Getters y setters
	private List<Inmueble> getInmuebles() {
		return usuarios.stream().flatMap(u -> u.getInmuebles().stream()).collect(Collectors.toList());
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Servicio> getServiciosDeInmuebles() {
		return serviciosInmuebles;
	}

	public List<Servicio> getTiposDeInmueble() {
		return serviciosInmuebles;
	}

	public void darDeAltaInmueble(Inmueble i) {

		inmuebles.add(i);
	}
//COMENTADO PARA QUE NO DE ERROR

	// pensar en clase Validador.
	/*
	 * public void registrarInmuebleDe(Inmueble inmueble, Propietario propietario) {
	 * if (esUsuarioRegistrado(propietario)) { inmuebles.add(inmueble); } else {
	 * throw new
	 * UsuarioException("Error, debes registrarte en la Web para publicar"); } }
	 */
	/*
	 * public void validarRankingPara(Usuario u, String comentario, int puntaje,
	 * String categoria) { admin.validarRanking(u, comentario, puntaje, categoria);
	 * 
	 * }
	 * 
	 * // acoplamiento de mensaje? public void validarRankingPara(Inmueble i, String
	 * comentario, int puntaje, String categoria) {
	 * admin.validarRankingDeInmueble(i, comentario, puntaje, categoria);
	 * 
	 * }
	 * 
	 * public void registrarIntereado(Interesado interesado) {
	 * 
	 * //this.interesados.add(interesado);
	 * 
	 * }
	 * 
	 * public void desubscribirIntereado(Interesado interesado) {
	 * //this.interesados.remove(interesado);
	 * 
	 * }
	 * 
	 * 
	 * //COMENTADO PARA QUE NO DE ERROR
	 * 
	 * 
	 * /* // OBSERVER COMPORTAMIENTO // Método general para enviar notificaciones
	 * private void enviarNotificacion(String mensaje, String tipoInmueble) {
	 * interesados.stream().forEach(interesado ->
	 * interesado.recibirNotificacionDe(mensaje, tipoInmueble)); }
	 * 
	 * public void notificarBajaDePrecio(Inmueble i, Double monto) { // usar en
	 * donde corresponda. String mensaje = "No te pierdas esta oferta: Un inmueble "
	 * + i.getTipo() + " a tan sólo " + monto + " pesos.";
	 * enviarNotificacion(mensaje, i.getTipo());
	 * 
	 * } public void notificarCancelacionDeInmueble(Inmueble i) { String mensaje =
	 * "El/la " + i.getTipo() +
	 * " que te interesa se ha liberado! Corre a reservarlo!";
	 * enviarNotificacion(mensaje, i.getTipo());
	 * 
	 * }
	 * 
	 * public void notificarReservaConcretadaDeInmueble(Inmueble i) { // Este es el
	 * general? lo recbien todos? String mensaje = "Se reservo el Inmueble " +
	 * i.getTipo(); enviarNotificacion(mensaje, i.getTipo()); }
	 * 
	 * // COMPORTAMIENTO DE RESERVAS
	 * 
	 * public void solicitarReserva(Reserva reserva) { if
	 * (esUsuarioRegistrado(reserva.getInquilino())) {
	 * reserva.propietarioAsigando().evaluarSolicitudDeReserva(reserva); } else {
	 * throw new RuntimeException("Error, Usuario no regitrada"); } }
	 * 
	 * public void consolidarReserva(Reserva reserva) { // Dic 3 if
	 * (this.esReservaCondicional(reserva)) { // camino con conflicto
	 * 
	 * this.encolarReservaEn(reserva, reserva.getInmueble());
	 * 
	 * }
	 * 
	 * if (this.esUsuarioRegistrado(reserva.propietarioAsigando())) { // camino
	 * libre
	 * 
	 * this.reservas.add(reserva);
	 * 
	 * reserva.getInmueble().agregarReserva(reserva); // reservas generales del
	 * inmueble
	 * 
	 * reserva.confirmarReserva(); // email al inquilino
	 * email.enviarMail(reserva.mailInquilino(), "Reserva Confirmada", reserva); //
	 * notificacion a obvservadores
	 * this.notificarReservaConcretadaDeInmueble(reserva.getInmueble());
	 * 
	 * } else { throw new RuntimeException("Error, Reserva no regitrada"); } }
	 * 
	 * private void encolarReservaEn(Reserva reserva, Inmueble inmueble) {
	 * 
	 * inmueble.agregarReservaCondicional(reserva);
	 * 
	 * }
	 * 
	 * public void anularReserva(Reserva reserva) { if
	 * (this.esReservaRegistrada(reserva)) {
	 * 
	 * reserva.cancelarReserva();
	 * 
	 * //subaterea reserva.getInmueble().eliminarReserva(reserva);
	 * 
	 * //reserva.getInmueble().evaluarEncoladas(reserva.getFechaEntrada(),
	 * reserva.getFechaSalida());
	 * 
	 * reserva.getInmueble().evaluarReservasEncoladasParaInmueble();
	 * 
	 * // email al Propietario email.enviarMail(reserva.mailPropietario(),
	 * "Reserva Cancelada", reserva); // notificacion a obvservadores
	 * this.notificarCancelacionDeInmueble(reserva.getInmueble()); } } //
	 * Validaciones
	 * 
	 * private boolean esUsuarioRegistrado(Usuario u) {
	 * 
	 * return this.validador.validarRegistro(u, this.getUsuarios()); }
	 * 
	 * private boolean esReservaRegistrada(Reserva reserva) {
	 * 
	 * return this.validador.validarRegistro(reserva, this.getReservas()); }
	 * 
	 * public void solicitarReservaCondicional(Reserva reserva) { if
	 * (this.esUsuarioRegistrado(reserva.getInquilino()) &&
	 * this.esReservaCondicional(reserva)) {
	 * reserva.getInmueble().getPropietario().evaluarSolicitudDeReserva(reserva);
	 * //reserva.getInmueble().encolar(reserva); } }
	 * 
	 * private boolean esReservaCondicional(Reserva reserva) { // usuario que se
	 * quiere encolar
	 * 
	 * return reserva.esCondicionalParaElInmueble(reserva.getFechaEntrada(),
	 * reserva.getFechaSalida()); }
	 */
}