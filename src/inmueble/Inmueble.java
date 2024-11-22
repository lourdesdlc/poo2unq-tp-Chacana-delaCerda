package inmueble;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import politicaCancelacion.PoliticaCancelacion;
import ranking.Categoria;
import ranking.GestorRanking;
import ranking.Rankeable;
import ranking.Ranking;
import reserva.Reserva;
import usuario.Inquilino;
import usuario.Propietario;

public class Inmueble implements Rankeable{ // casa // departamento // lordes.casa.13 --- bruno.casa.13
	private Propietario propietario;
	private TipoInmueble tipoDeInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private int capacidad;
	private List<String> fotos; // Máximo de 5 fotos
	private LocalTime checkIn; //representa solo la hora, formato: hh:mm:ss.nnn (hora:minuto.nanosegundo).
	private LocalTime checkOut; 
	private List<Servicio> servicios;
	// Enum FormaDePago
	private List<FormaDePago> formasDePago;
	// Lista de períodos con precios variables
	private List<PrecioPorPeriodo> preciosPorPeriodos;
	private double precioBasePorDia; // un valor por defecto(por si no es un periodo existente)
	private PoliticaCancelacion politicaDeCancelacion;
	private List<Ranking> rankings = new ArrayList<>();
	private Set<Reserva> reservas; // reservasFuturas

	private Queue<Reserva> reservasEncoladas;
	
	 public Inmueble(Propietario propietario, TipoInmueble tipoDeInmueble, 
			 int superficie, String pais, String ciudad, String direccion, 
			 List<Servicio> servicios, int capacidad, List<String> fotos, 
			 LocalTime checkIn, LocalTime checkOut, 
			 List<FormaDePago> formasDePago, double precioBasePorDia) {
	        this.propietario = propietario;
	        this.tipoDeInmueble = tipoDeInmueble;
	        this.superficie = superficie;
	        this.pais = pais;
	        this.ciudad = ciudad;
	        this.direccion = direccion;
	        this.servicios = servicios;
	        this.capacidad = capacidad;
	        this.fotos = fotos;
	        this.checkIn = checkIn;
	        this.checkOut = checkOut;
	        this.formasDePago = formasDePago;
	        this.precioBasePorDia = precioBasePorDia;
	    }
	
	public double getPrecio(LocalDate fechaInicio, LocalDate fechaFin) {
		// calcula el precio de un rango de dias
		// algunos dias podrian ser estandar y otros de periodos especiales
		return fechaInicio.datesUntil(fechaFin.plusDays(1))
				.mapToDouble(this::precioParaFecha).sum();
	}
	
	private double precioParaFecha(LocalDate fecha) {
		return preciosPorPeriodos.stream()
				.filter(periodo -> periodo.incluye(fecha))
				.map(PrecioPorPeriodo::getPrecioPorDia)
				.findFirst()
				.orElse(precioBasePorDia);
	}

	public double calcularPenalidadPorCancelacion(Reserva reserva) {
		// le delega la responsabilidad de calcular la penalidad a la
		// politicaDeCancelacion
		return this.politicaDeCancelacion.calcularPenalidad(reserva);
	}

	public boolean estaDisponibleParaLasFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		// Método para verificar si el rango de fechas no interfiere con las reservas
		// existentes

		return reservas.stream().noneMatch(reserva -> reserva.interfiereCon(fechaEntrada, fechaSalida));
	}
	
	public void agregarPreciosPorPeriodo(PrecioPorPeriodo precioPorPeriodo) {
		validarPeriodo(precioPorPeriodo);
		preciosPorPeriodos.add(precioPorPeriodo);
	}
	
	private void validarPeriodo(PrecioPorPeriodo precioPorPeriodo){
        if (interfiereConAlgunPeriodoDefinido(precioPorPeriodo)){
        	throw new RuntimeException("Interfiere con otro periodo definido anteriormente");
        }
    }
	
	private boolean interfiereConAlgunPeriodoDefinido(PrecioPorPeriodo precioPorPeriodo){
		return preciosPorPeriodos.stream().anyMatch(p -> p.interfiereCon(precioPorPeriodo));
	}
	
	public void checkOut(Reserva reserva){
        //validar que se pueda hacer checkOut
        reservas.remove(reserva);
    }
	
	public boolean fueHechoElCheckOut(Inquilino inquilino){
		//IMPLEMENTAR
        return true;
        //la idea seria que Inmueble tenga una lista controlada con los usuarios 
        //que alquilaron anteriormente. Cuando un Usuario hace checkOut, se agrega a esa lista
        //cuando
    }
	
	private void validarCheckOut(Inquilino inquilino) {
        if(!fueHechoElCheckOut(inquilino)){
            throw new RuntimeException("No se puede rankear antes de hacer el check-out");
        }
    }
	
//RANKING//////////////////////////////////////////////////
	@Override
	public void agregarRanking(Ranking ranking) {
		validarCheckOut(ranking.getRankeador());
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
///////////////////////////////////////////////////////////////////////////////
    
	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
		
	}
	
	public void eliminarReserva(Reserva reserva) {
		reservas.remove(reserva);
		
	}

	public int getCantidadDeVecesAlquilada() {
        // IMPLEMENTAR
        return 0;
    }

	//Getters y setters
	public void setPoliticaDeCancelacion(PoliticaCancelacion politica){
		this.politicaDeCancelacion = politica;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}

	public TipoInmueble getTipo() {
		return tipoDeInmueble;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

	public LocalTime getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalTime checkIn) {
		this.checkIn = checkIn;
	}

	public LocalTime getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalTime checkOut) {
		this.checkOut = checkOut;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		//L: No me parece necesario, yo pondria "agregar/eliminar servicio" en todo caso
		this.servicios = servicios;
	}

	public List<FormaDePago> getFormasDePago() {
		return formasDePago;
	}

	public void setFormasDePago(List<FormaDePago> formasDePago) {
		//L: No me parece necesario, yo pondria "agregar/eliminar forma de pago" en todo caso
		this.formasDePago = formasDePago;
	}

	public List<PrecioPorPeriodo> getPreciosPorPeriodos() {
		return preciosPorPeriodos;
	}

	public PoliticaCancelacion getTipoDeCancelacion() {
		return politicaDeCancelacion;
	}

	public double getPrecioPorDia() {
		return precioBasePorDia;
	}

	public void setPrecioPorDia(double precioPorDia) {
		this.precioBasePorDia = precioPorDia;
	}

	public void cambiarPrecio(Double monto) {
		//L: No me parece necesario
		this.setPrecioPorDia(monto);

	}

	public double precioBasePorDia() {
		return precioBasePorDia;
	}
	
	public TipoInmueble getTipoDeInmueble() {
		return tipoDeInmueble;
	}
/*
	public void encolar(Reserva reserva) {
		reservasEncoladas.add(reserva);
	}

	public void verificarEncoladas(LocalDate fechaEntrada) {

	}

	public void agregarReservaCondicional(Reserva reserva) {
		this.reservasEncoladas.add(reserva); // reserva duplicada

	}
	public void evaluarEncoladas(LocalDate fechaInicio, LocalDate fechaFin) {
		if (reservasEncoladas != null && !reservasEncoladas.isEmpty()) {
			// Encuentra la primera reserva encolada que interfiera con las fechas
			// proporcionadas
			reservasEncoladas.stream().filter(r -> r.reservaInterfiereCon(fechaInicio, fechaFin)).findFirst()
					.ifPresent(reserva -> {
						this.getPropietario().aceptarReserva(reserva);
						reservasEncoladas.remove(reserva); // Elimina la reserva de la cola
					});
		}
	}


	public void evaluarReservasEncoladasParaInmueble() {
			if(!reservasEncoladas.isEmpty())
			this.getPropietario().evaluarSolicitudDeReserva(reservasEncoladas.poll());
			
	}
 */


}