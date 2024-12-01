package inmueble;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import observer.Notificable;
import observer.Notificador;
import observer.Subscripcion;
import politicaCancelacion.PoliticaCancelacion;
import ranking.Categoria;
import ranking.GestorRanking;
import ranking.Rankeable;
import ranking.Ranking;
import reserva.Reserva;
import reserva.ReservaCancelada;
import usuario.Inquilino;
import usuario.Propietario;

public class Inmueble implements Rankeable { // casa // departamento // lordes.casa.13 --- bruno.casa.13
	private Propietario propietario;
	private TipoInmueble tipoDeInmueble;
	private double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private int capacidad;
	private List<String> fotos; // Máximo de 5 fotos
	private LocalTime checkIn; // representa solo la hora, formato: hh:mm:ss.nnn (hora:minuto.nanosegundo).
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

	private Notificador notificador;

	private List<Inquilino> visitantes;

	public Inmueble(Propietario propietario, TipoInmueble tipoDeInmueble, int superficie, String pais, String ciudad,
			String direccion, List<Servicio> servicios, int capacidad, List<String> fotos, LocalTime checkIn,
			LocalTime checkOut, List<FormaDePago> formasDePago, double precioBasePorDia) {
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
	
	public Inmueble() {};

///////////////////////  Notificaciones ///////////////////////////
	private void notificarNuevaReserva(LocalDate fi, LocalDate ff) {
		notificador.notificarReserva("El inmueble " + tipoDeInmueble + " que te interesa, ha sido reservado desde el "
				+ fi + " hasta el " + ff + ".", this);
	}

	private void notificarCancelacionDeReserva() {
		notificador.notificarCancelacionReserva(
				"El/la " + tipoDeInmueble.getNombre() + " que te interesa se ha liberado! Corre a reservarlo!", this);
	}

	void notificarBajaDePrecioDeInmueble() {
		notificador.notificarBajaDePrecio("No te pierdas esta oferta: Un inmueble " + tipoDeInmueble + " a tan sólo "
				+ precioBasePorDia + " pesos", this);
	}

///////////////////////  Subscripcion de interesado /////////////////

	public void recibirSubscriptor(Notificable n) { // ej la AppMobile o la pagina de trivago
		Subscripcion s = new Subscripcion(n);
		s.agregarInteresEnInmuble(this);

		notificador.suscribir(s);
	}

	public void eliminarSubscriptor(Subscripcion s) {
		s.eliminarInteresEnInmuble(this);
	}
///////////////////////  Concrecion de Reserva  ///////////////////////////

	public void recibirSolicitudDeReserva(Reserva r) {

		if (r.estaPendiente() && estaDisponibleParaLasFechas(r.getFechaEntrada(), r.getFechaSalida())) {
			reservas.add(r);
			notificarNuevaReserva(r.getFechaEntrada(), r.getFechaSalida()); // pensar en clase periodo

			// si el iniquilino tiene que conocer sus reservas, entonces que se agregue aca
		} else {
			reservasEncoladas.add(r);
			// posible notificaccion para inquilino diciendo que su reserva fue encolada...
		}
	}

	public void cancelarReserva(Reserva reserva) {
		if (!reserva.estaCancelada()) { // sino esta cancelada previamente...

			reserva.cancelarReserva();
			politicaDeCancelacion.calcularPenalidad(reserva);
			notificarCancelacionDeReserva();

			ejecutarReservaEncoladas();
		}

	}

	public void ejecutarReservaEncoladas() {
		if (!reservasEncoladas.isEmpty()) { // si existen reservar encoladas...
			recibirSolicitudDeReserva(reservasEncoladas.poll()); // agarra la primera y ejecuta ciclo normal
		}
	}

	public void checkOut(Reserva reserva) {
		if (reserva.estaConfirmada()) {
			reserva.finalizarReserva();
			visitantes.add(reserva.getInquilino());
		}
	}

	public boolean fueHechoElCheckOut(Inquilino inquilino) {
		// IMPLEMENTAR, resuelto en metodo checkout , firma bach.
		return visitantes.contains(inquilino);
		// la idea seria que Inmueble tenga una lista controlada con los usuarios
		// que alquilaron anteriormente. Cuando un Usuario hace checkOut, se agrega a
		// esa lista
		// cuando
	}

	private void validarCheckOut(Inquilino inquilino) {
		if (!fueHechoElCheckOut(inquilino)) {
			throw new RuntimeException("No se puede rankear antes de hacer el check-out");
		} // supongo que para rankear basta con que haya estado una vez.
	}

	public double getPrecio(LocalDate fechaInicio, LocalDate fechaFin) {
		// calcula el precio de un rango de dias
		// algunos dias podrian ser estandar y otros de periodos especiales
		return fechaInicio.datesUntil(fechaFin.plusDays(1)).mapToDouble(this::precioParaFecha).sum();
	}

	private double precioParaFecha(LocalDate fecha) {
		return preciosPorPeriodos.stream().filter(periodo -> periodo.incluye(fecha))
				.map(PrecioPorPeriodo::getPrecioPorDia).findFirst().orElse(precioBasePorDia);
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

	private void validarPeriodo(PrecioPorPeriodo precioPorPeriodo) {
		if (interfiereConAlgunPeriodoDefinido(precioPorPeriodo)) {
			throw new RuntimeException("Interfiere con otro periodo definido anteriormente");
		}
	}

	private boolean interfiereConAlgunPeriodoDefinido(PrecioPorPeriodo precioPorPeriodo) {
		return preciosPorPeriodos.stream().anyMatch(p -> p.interfiereCon(precioPorPeriodo));
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

	// Getters y setters
	public void setPoliticaDeCancelacion(PoliticaCancelacion politica) {
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
		// L: No me parece necesario, yo pondria "agregar/eliminar servicio" en todo
		// caso
		this.servicios = servicios;
	}

	public List<FormaDePago> getFormasDePago() {
		return formasDePago;
	}

	public void setFormasDePago(List<FormaDePago> formasDePago) {
		// L: No me parece necesario, yo pondria "agregar/eliminar forma de pago" en
		// todo caso
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
		// L: No me parece necesario
		// bach: necesito este mensaje para el observer

		this.setPrecioPorDia(monto);
		notificarBajaDePrecioDeInmueble();

	}

	public double precioBasePorDia() {
		return precioBasePorDia;
	}

	public TipoInmueble getTipoDeInmueble() {
		return tipoDeInmueble;
	}

	public double getPrecioBasePorDia() {
		return precioBasePorDia;
	}

	public void setPrecioBasePorDia(double precioBasePorDia) {
		this.precioBasePorDia = precioBasePorDia;
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Queue<Reserva> getReservasEncoladas() {
		return reservasEncoladas;
	}

	public void setReservasEncoladas(Queue<Reserva> reservasEncoladas) {
		this.reservasEncoladas = reservasEncoladas;
	}

	public Notificador getNotificador() {
		return notificador;
	}

	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	}

	public List<Inquilino> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(List<Inquilino> visitantes) {
		this.visitantes = visitantes;
	}

	public PoliticaCancelacion getPoliticaDeCancelacion() {
		return politicaDeCancelacion;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public void setTipoDeInmueble(TipoInmueble tipoDeInmueble) {
		this.tipoDeInmueble = tipoDeInmueble;
	}

	public void setPreciosPorPeriodos(List<PrecioPorPeriodo> preciosPorPeriodos) {
		this.preciosPorPeriodos = preciosPorPeriodos;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}
	
	
	
	
	
	/*
	 * public void encolar(Reserva reserva) { reservasEncoladas.add(reserva); }
	 * 
	 * public void verificarEncoladas(LocalDate fechaEntrada) {
	 * 
	 * }
	 * 
	 * public void agregarReservaCondicional(Reserva reserva) {
	 * this.reservasEncoladas.add(reserva); // reserva duplicada
	 * 
	 * } public void evaluarEncoladas(LocalDate fechaInicio, LocalDate fechaFin) {
	 * if (reservasEncoladas != null && !reservasEncoladas.isEmpty()) { // Encuentra
	 * la primera reserva encolada que interfiera con las fechas // proporcionadas
	 * reservasEncoladas.stream().filter(r -> r.reservaInterfiereCon(fechaInicio,
	 * fechaFin)).findFirst() .ifPresent(reserva -> {
	 * this.getPropietario().aceptarReserva(reserva);
	 * reservasEncoladas.remove(reserva); // Elimina la reserva de la cola }); } }
	 * 
	 * 
	 * public void evaluarReservasEncoladasParaInmueble() {
	 * if(!reservasEncoladas.isEmpty())
	 * this.getPropietario().evaluarSolicitudDeReserva(reservasEncoladas.poll());
	 * 
	 * }
	 */

	

}
