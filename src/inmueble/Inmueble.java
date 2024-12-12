package inmueble;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import notificaciones.EmailSender;
import observer.Notificable;
import observer.Notificador;
import observer.Subscripcion;
import politicaCancelacion.PoliticaCancelacion;
import politicaCancelacion.PoliticaGratuita;
import ranking.Categoria;
import ranking.GestorRanking;
import ranking.Rankeable;
import ranking.Ranking;
import reserva.Reserva;

import usuario.Usuario;

public class Inmueble implements Rankeable { 
	private Usuario propietario;
	
	private TipoInmueble tipoDeInmueble;
	
	private double superficie;
	
	private String pais;
	
	private String ciudad;
	
	private String direccion;
	
	private int capacidad;
	
	private List<String> fotos;
	
	private LocalTime checkIn; 
	
	private LocalTime checkOut;
	
	private List<Servicio> servicios;
	
	private List<FormaDePago> formasDePago;
	// Lista de períodos con precios variables
	private List<PrecioPorPeriodo> preciosPorPeriodos;
	
	private double precioBasePorDia; // un valor por defecto (por si no es un periodo existente)
	
	private PoliticaCancelacion politicaDeCancelacion;
	
	private List<Ranking> rankings;

	private List<Reserva> reservas;

	private Queue<Reserva> reservasEncoladas;

	private Notificador notificador;

	private List<Usuario> visitantes;

	private EmailSender email; // pagina 4 del tp, es solo para cumplir el envio de email

	public Inmueble() {
	};

	// Constructor simplificado
	public Inmueble(Usuario propietario, TipoInmueble tipoDeInmueble, String pais, String ciudad, String direccion) {
		this.propietario = propietario;
		this.tipoDeInmueble = tipoDeInmueble;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.capacidad = 0; // valor por defecto, si es necesario ajustarlo
		this.fotos = new ArrayList<>(); // Inicia la lista vacía
		this.checkIn = LocalTime.of(15, 0); // ejemplo: por defecto a las 15:00
		this.checkOut = LocalTime.of(11, 0); // ejemplo: por defecto a las 11:00
		this.servicios = new ArrayList<>();
		this.formasDePago = new ArrayList<>();
		this.preciosPorPeriodos = new ArrayList<>();
		this.precioBasePorDia = 0.0; // valor por defecto
		this.politicaDeCancelacion = new PoliticaGratuita(); // ejemplo: se crea por defecto
		this.rankings = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.reservasEncoladas = new LinkedList<>();
		this.notificador = new Notificador(); 
		this.visitantes = new ArrayList<>();
	}

///////////////////////  Notificaciones ///////////////////////////
	void notificarNuevaReserva(LocalDate fi, LocalDate ff) {
		notificador.notificarReserva("El inmueble " + tipoDeInmueble + " que te interesa, ha sido reservado desde el "
				+ fi + " hasta el " + ff + ".", this);
	}

	void notificarCancelacionDeReserva() {
		notificador.notificarCancelacionReserva(
				"El/la " + tipoDeInmueble.getNombre() + " que te interesa se ha liberado! Corre a reservarlo!", this);
	}

	void notificarBajaDePrecioDeInmueble() {
		notificador.notificarBajaDePrecio("No te pierdas esta oferta: Un inmueble " + tipoDeInmueble.getNombre()
				+ " a tan sólo " + precioBasePorDia + " pesos", this);
	}

///////////////////////  Subscripcion de interesado /////////////////

	public void recibirSubscriptor(Notificable n) { // ej. la AppMobile o la pagina de trivago
		Subscripcion s = new Subscripcion(n);
		s.agregarInteresEnInmuble(this);

		notificador.suscribir(s);
	}

	public void eliminarSubscriptor(Subscripcion s) {
		s.eliminarInteresEnInmuble(this);
	}

///////////////////////  Concrecion de Reserva  ///////////////////////////

	// el propietario tiene tiempo de revisar la solicitud
	public void recibirSolicitudDeReserva(Reserva r) { 
		if (r.estaPendiente()) {
			email.enviarMail(propietario.getEmail(), "Nueva solicitud de reserva para uno de sus inmuebles", r);
			propietario.agregarReserva(r);
		}
	}

	public void aceptarReserva(Reserva r) { 

		if (estaDisponibleParaLasFechas(r.getFechaEntrada(), r.getFechaSalida())) {

			r.confirmarReserva();

			reservas.add(r);

			notificarNuevaReserva(r.getFechaEntrada(), r.getFechaSalida());

			email.enviarMail(r.mailInquilino(), "Su reserva ha sido aceptada", r);

			r.getInquilino().agregarReserva(r);
			
		} else {
			
			reservasEncoladas.add(r);
			
			email.enviarMail(r.mailInquilino(), "Su reserva ha sido encolada", r);
		}
	}

	public void cancelarReserva(Reserva reserva) {
		if (!reserva.estaCancelada()) {
			
			reserva.cancelarReserva();

			politicaDeCancelacion.calcularPenalidad(reserva);
			
			notificarCancelacionDeReserva();
			
			email.enviarMail(propietario.getEmail(), "El inquilino ha cancelado la reserva", reserva);
			
			ejecutarReservaEncoladas();
		}

	}

	public void ejecutarReservaEncoladas() {
		if (!reservasEncoladas.isEmpty()) { 
			recibirSolicitudDeReserva(reservasEncoladas.poll()); 
		}
	}

	public void checkOut(Reserva reserva) {
		if (reserva.estaConfirmada()) {
			reserva.finalizarReserva();
			visitantes.add(reserva.getInquilino());
		}
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
		return this.politicaDeCancelacion.calcularPenalidad(reserva);
	}

	public boolean estaDisponibleParaLasFechas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return reservas.stream().noneMatch(reserva -> reserva.interfiereCon(fechaEntrada, fechaSalida));
	}

	public void agregarPreciosPorPeriodo(PrecioPorPeriodo precioPorPeriodo) {
		validarPeriodo(precioPorPeriodo);
		preciosPorPeriodos.add(precioPorPeriodo);
	}

	void validarPeriodo(PrecioPorPeriodo precioPorPeriodo) {
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

	public boolean fueHechoElCheckOut(Usuario inquilino) {	
		return getVisitantes().contains(inquilino);
	}

	void validarCheckOut(Usuario inquilino) {
		if (!fueHechoElCheckOut(inquilino)) {
			throw new RuntimeException("No se puede rankear antes de hacer el check-out");
		} 
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

	public int cantidadDeAlquileres() {
		return (int) reservas.stream().filter(Reserva::estaFinalizada).count();
		
	}

	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);

	}

	public void eliminarReserva(Reserva reserva) {
		reservas.remove(reserva);

	}
	
	public void agregarFoto(String foto) {
		validarMaximoDeFotos();
		fotos.add(foto);
	}
	
	public void validarMaximoDeFotos() {
		if(fotos.size() >= 5) {
			throw new RuntimeException("No se pueden agregar mas de 5 fotos.");
		}
	}
	
	// Getters y setters

	public String getNombreDeTipoInmueble() {
		return tipoDeInmueble.getNombre();
	}

	public void setPoliticaDeCancelacion(PoliticaCancelacion politica) {
		this.politicaDeCancelacion = politica;
	}

	public Usuario getPropietario() {
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

	public void eliminarServicio(Servicio servicio) {
		servicios.remove(servicio);
	}
	
	public void agregarServicio(Servicio servicio) {
		servicios.add(servicio);
	}
	
	public List<FormaDePago> getFormasDePago() {
		return formasDePago;
	}

	public void eliminarFormaDePago(FormaDePago formaDePago) {
		formasDePago.remove(formaDePago);
	}
	
	public void agregarFormaDePago(FormaDePago formaDePago) {
		formasDePago.add(formaDePago);
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

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
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

	public List<Usuario> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(List<Usuario> visitantes) {
		this.visitantes = visitantes;
	}

	public PoliticaCancelacion getPoliticaDeCancelacion() {
		return politicaDeCancelacion;
	}

	public void setPropietario(Usuario propietario) {
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

	public void setEmailSender(EmailSender email) {
		this.email = email;

	}

}
