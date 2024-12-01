package usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import inmueble.Inmueble;
import ranking.Categoria;
import ranking.GestorRanking;
import ranking.Ranking;
import ranking.TipoRankeable;
import reserva.Reserva;

public class Usuario implements Propietario, Inquilino {
	private String nombreCompleto;
	private String email;
	private String telefono;
	private LocalDate fechaDeCreacion;
	private List<Reserva> reservas;
	private List<Inmueble> inmuebles;
	private List<Ranking> rankings;

	public Usuario(String nombre, String email, String telefono) {
		setNombreCompleto(nombre);
		setEmail(email);
		setTelefono(telefono);
		fechaDeCreacion = LocalDate.now();
		reservas = new ArrayList<>();
		inmuebles = new ArrayList<>();
		rankings = new ArrayList<>();
	}

	@Override
	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
	}

	@Override
	public void agregarInmueble(Inmueble inmueble) {
		inmuebles.add(inmueble);
	}

	@Override
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public int getAntiguedad() {
		return Period.between(fechaDeCreacion, LocalDate.now()).getYears();
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public List<Reserva> getReservasFuturas() {
		LocalDate fechaDeHoy = LocalDate.now();

		return reservas.stream().filter(reserva -> reserva.getFechaEntrada().isAfter(fechaDeHoy)).toList();
	}

	public List<Reserva> getReservasPorCiudad(String ciudad) {
		return reservas.stream().filter(reserva -> reserva.ciudadDeReserva().equalsIgnoreCase(ciudad)).toList();
	}

	public List<String> getCiudadesConReservas() {
		return reservas.stream().map(reserva -> reserva.ciudadDeReserva()).toList();
	}

	public int cantidadDeAlquileres() {
		return (int) reservas.stream().filter(Reserva::estaFinalizada).count();
	}

	public List<Inmueble> inmueblesAlquilados() {
	    return reservas.stream()
	                   .filter(Reserva::estaFinalizada) 
	                   .map(Reserva::getInmueble)      
	                   .distinct()                   
	                   .collect(Collectors.toList()); 
	}

	
//RANKING/////////////////////////////////////////////////////////
	@Override
	public void agregarRanking(Ranking ranking) {
		validarCheckOut(ranking);
		rankings.add(ranking);
	}

	@Override
	public List<Ranking> getRankings() {
		return rankings;
	}

	@Override
	public List<String> getComentarios() {
		return GestorRanking.getComentarios(rankings);
	}

	@Override
	public double getPuntajePromedio() {
		return GestorRanking.getPuntajePromedio(rankings);
	}

	@Override
	public double getPuntajePromedioEnCategoria(Categoria categoria) {
		return GestorRanking.getPuntajePromedioEnCategoria(rankings, categoria);
	}

	public List<String> getComentariosComoPropietario() {
		return GestorRanking.getComentariosPorRol(rankings, TipoRankeable.PROPIETARIO);
	}

	public List<String> getComentariosComoInquilino() {
		return GestorRanking.getComentariosPorRol(rankings, TipoRankeable.INQUILINO);
	}

	////////////////////////////////////////////////////////////////////// 7
	private void validarCheckOut(Ranking ranking) {
		// IMPLEMENTAR
	}

	// getters y setters
	public String getEmail() {
		return email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getTelefono() {
		return telefono;
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

	public LocalDate getfechaDeCreacion() {
		return fechaDeCreacion;
	}



}
