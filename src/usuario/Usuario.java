package usuario;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import Inmueble.Inmueble;
import ranking.Rankeable;
import ranking.Ranking;

import reserva.Reserva;
import sitioWeb.SitioWeb;

public abstract class Usuario {
	private String nombreCompleto;
	private String email;
	private String telefono;
	protected SitioWeb sitioWeb;
	private LocalDate antiguedadEnElSitio; // se establece una vez que se logea
	private Set<Reserva> reservas;

	public Usuario(String nombreCompleto, String email, String telefono) {

		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.telefono = telefono;

	}

	public void mostrarHistorial() {
		// información propia del dueño, el puntaje que otros usuarios le han dado a él
		// mismo
		// y el puntaje promedio que ha obtenido
		// LO MISMO PARA INQUILINO
	};

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getEmail() {
		return email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void rankearA(Usuario u, String categoria, String comentario, int puntaje) {
		sitioWeb.validarRankingPara(u, comentario, puntaje, categoria);
	}

	public void rankearA(Inmueble i, String categoria, String comentario, int puntaje) {
		sitioWeb.validarRankingPara(i, comentario, puntaje, categoria);
	}

	public SitioWeb getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(SitioWeb sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getAntiguedadEnElSitio() {
		return antiguedadEnElSitio;
	}

	public void setAntiguedadEnElSitio(LocalDate localTime) {
		this.antiguedadEnElSitio = localTime;
	}

	public void registrarseEnSitioWeb(SitioWeb sitioWeb) {
		this.setSitioWeb(sitioWeb);
		sitioWeb.registrarUsuario(this);
	}

	public void darFechaInicioEnWeb() {
		this.setAntiguedadEnElSitio(LocalDate.now()); // espero que esto no de problemas en los futuros test

	}

	public void asignarWeb(SitioWeb web) {
		this.sitioWeb = web;

	}

	public Set<Reserva> verReservasPropias() {
		return this.getReservas();
	}

	// Las reservas futuras: muestra aquellas reservas con fecha de ingreso

	public Set<Reserva> verReservasFuturas() {
		LocalDate fechaDeHoy = LocalDate.now();

		return this.getReservas().stream().filter(reserva -> reserva.getFechaEntrada().isAfter(fechaDeHoy))
				.collect(Collectors.toSet());
	}

	public Set<Reserva> verReservasPorCiudad(String ciudad) {
		return this.getReservas().stream().filter(reserva -> reserva.ciudadDeReserva().equalsIgnoreCase(ciudad))
				.collect(Collectors.toSet());
	}

	public Set<String> verCiudadesConReservas() {
		return this.getReservas().stream().map(reserva -> reserva.ciudadDeReserva()).collect(Collectors.toSet());
	}
}
