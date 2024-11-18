package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Inmueble.Inmueble;
import ranking.Ranking;

import reserva.Reserva;
import sitioWeb.SitioWeb;

public class Usuario implements Propietario, Inquilino{
	private String nombreCompleto;
	private String email;
	private String telefono;
	protected SitioWeb sitioWeb;
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
	public void cancelarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarInmueble(Inmueble inmueble) {
		inmuebles.add(inmueble);
		
	}
	
    public void puntuar(Ranking ranking) {
        //validar CheckOut
        //validar categoria del ranking
        rankings.add(ranking);
    }
	
	@Override
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void getAntiguedad() {
		//FALTA IMPLEMENTAR
	}

	public List<Reserva> getReservas() {
		return reservas;
	}
	
	public List<Reserva> getReservasFuturas() {
		LocalDate fechaDeHoy = LocalDate.now();

		return reservas.stream().
				filter(reserva -> reserva.getFechaEntrada().isAfter(fechaDeHoy))
				.toList();
	}

	public List<Reserva> getReservasPorCiudad(String ciudad) {
		return reservas.stream().
				filter(reserva -> reserva.ciudadDeReserva().equalsIgnoreCase(ciudad))
				.toList();
	}

	public List<String> getCiudadesConReservas() {
		return reservas.stream()
				.map(reserva -> reserva.ciudadDeReserva())
				.toList();
	}

	public void setReservas(List<Reserva> reservas) {
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
	
	public SitioWeb getSitioWeb() {
		return sitioWeb;
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
