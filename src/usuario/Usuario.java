package usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Inmueble.Inmueble;
import categoria.Categoria;
import ranking.GestorRanking;
import ranking.Ranking;

import reserva.Reserva;

public class Usuario implements Propietario, Inquilino{
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
	public void cancelarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void agregarInmueble(Inmueble inmueble) {
		inmuebles.add(inmueble);
		
	}
	
    public void rankear(Ranking ranking) {
    	validarCheckOut(ranking);
        rankings.add(ranking);
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
	
	@Override
	public void agregarRanking(Ranking ranking) {
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
    
    private void validarCheckOut(Ranking ranking) {
    	//IMPLEMENTAR
    }

    //getters y setters
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
