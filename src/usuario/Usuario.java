package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ranking.Rankeable;
import ranking.Ranking;
import ranking.RankingInmueble;
import tpgrupal.Inmueble;
import tpgrupal.SitioWeb;

public abstract class Usuario {
	private String nombreCompleto;
	private String email;
	private String telefono;
	protected SitioWeb sitioWeb;
	private LocalDate antiguedadEnElSitio;

	public Usuario(String nombreCompleto, String email, String telefono, LocalDate antiguedadEnElSitio) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.telefono = telefono;
		this.antiguedadEnElSitio = antiguedadEnElSitio;
	}

	// MISMO METODO PARA CADA RANKEADOR pero NO para RANKEABLE
	public void rankear(Rankeable rankeable, Ranking ranking) {
		rankeable.agregarRanking(ranking);
	}

	public void mostrarHistorial() {
		// información propia del dueño, el puntaje que otros usuarios le han dado a él
		// mismo
		// y el puntaje promedio que ha obtenido
		// LO MISMO PARA INQUILINO
	};

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

	public void registrarEnWeb(SitioWeb sitioWeb) {
		this.setSitioWeb(sitioWeb);
	}

	public void ingresarAlSitio() {
		this.sitioWeb.registrarUsuario(this);
		this.darFechaInicioEnWeb();
	}

	private void darFechaInicioEnWeb() {
		this.setAntiguedadEnElSitio(LocalDate.now()); // espero que esto no de problemas en los futuros test

	}

	public void asignarWeb(SitioWeb web) {
		this.sitioWeb = web;

	}
}
