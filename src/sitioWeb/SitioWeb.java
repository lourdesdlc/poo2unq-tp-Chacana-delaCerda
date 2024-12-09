package sitioWeb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

import filtroDeBusqueda.CriterioBusqueda;
import inmueble.Inmueble;
import inmueble.Servicio;
import inmueble.TipoInmueble;
import ranking.Categoria;
import ranking.TipoRankeable;

import usuario.Propietario;
import usuario.Usuario;

public class SitioWeb {
	private List<Usuario> usuarios;
	private List<Servicio> serviciosInmuebles = new ArrayList<>();
	private List<TipoInmueble> tiposDeInmueble = new ArrayList<>();
	private List<Categoria> categorias = new ArrayList<>();

	public SitioWeb() {
		this.usuarios = new ArrayList<>();
		this.serviciosInmuebles = new ArrayList<>();
		this.tiposDeInmueble = new ArrayList<>();
		this.categorias = new ArrayList<>();

	}

	public void registrarUsuario(Usuario usuario) {
		if (!estaRegistrado(usuario)) {
			usuarios.add(usuario);
		} else {
			throw new RuntimeException("El usuario ya está registrado");
		}
	}

	public boolean estaRegistrado(Usuario usuario) {
		return usuarios.contains(usuario);
	}

	public List<Inmueble> buscarInmuebles(CriterioBusqueda criterio) {
		return this.getInmuebles().stream().filter(criterio::cumple).collect(Collectors.toList());
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

	public List<Usuario> topTenInquilinosQueMasHanAlquilado() {
		return usuarios.stream().sorted(Comparator.comparingInt(Usuario::cantidadDeAlquileres).reversed()).limit(10)
				.collect(Collectors.toList());
	}

	public List<Inmueble> inmueblesLibres() {

		return getInmuebles().stream().filter(i -> i.estaDisponibleParaLasFechas(LocalDate.now(), LocalDate.now()))
				.toList();
	}

	public double tasaOcupacion() {

		List<Inmueble> inmuebles = getInmuebles();

		return ((double) inmuebles.stream().filter(i -> i.estaDisponibleParaLasFechas(LocalDate.now(), LocalDate.now()))
				.count() / inmuebles.size()) * 100;
	}

	public void darDeAltaInmueble(Propietario p, Inmueble i) {
		p.agregarInmueble(i);
	}

	public void eliminarInmueble(Usuario u, Inmueble i) {
		u.removerInmueble(i);
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

	public List<TipoInmueble> getTiposDeInmueble() {
		return tiposDeInmueble;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

/////////// " Visualizacion " ///////

	public String mostrarDatosDe(Inmueble i) {
		StringBuilder datos = new StringBuilder();

		// Manejar lista de comentarios
		datos.append("Comentarios del inmueble:\n");
		i.getComentarios().forEach(comentario -> datos.append("- ").append(comentario).append("\n"));

		// Puntaje promedio
		datos.append("Puntaje promedio: ").append(i.getPuntajePromedio()).append("\n");

		// Puntaje promedio en categoría
		datos.append("Puntaje promedio en categoría: ").append(i.getPuntajePromedioEnCategoria(new Categoria()))
				.append("\n");

		return datos.toString();
	}

	public String mostrarDatosDelPropietarioDe(Inmueble i) {
		Usuario propietario = i.getPropietario();

		StringBuilder datos = new StringBuilder();

		datos.append("Propietario del inmueble:\n").append("Puntaje promedio del propietario: ")
				.append(propietario.getPuntajePromedio()).append("\n").append("Antigüedad del propietario: ")
				.append(propietario.getAntiguedad()).append(" años\n")
				.append("Cantidad de alquileres realizados por el propietario: ")
				.append(propietario.cantidadDeAlquileres()).append("\n")
				.append("Cantidad de alquileres de este inmueble: ").append(i.cantidadDeAlquileres()).append("\n")
				.append("Inmuebles alquilados por el propietario:\n");

		// Manejar lista de inmuebles alquilados
		propietario.inmueblesAlquilados().forEach(alquilado -> datos.append("- ").append(alquilado).append("\n"));

		return datos.toString();
	}

}