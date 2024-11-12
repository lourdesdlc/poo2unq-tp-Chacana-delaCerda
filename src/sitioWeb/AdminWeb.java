package sitioWeb;

import java.util.Set;

import Inmueble.Inmueble;
import Inmueble.TipoDeInmueble;
import categoria.Categoria;
import exepciones.CategoriaException;
import ranking.RankingInmueble;
import ranking.RankingUsuario;
import tpgrupal.Servicio;
import usuario.Inquilino;
import usuario.Usuario;

public class AdminWeb { // ESTO SEGURO SE PUEDE MEJORAR MAS ADELANTE.
	private Set<String> categorias;
	private SitioWeb web;

	private Set<TipoDeInmueble> tiposDeInmueble;
	private Set<Categoria> categoriasPropietario;
	private Set<Categoria> categoriasInquilino;
	private Set<Categoria> categoriasInmueble;
	private Set<Servicio> serviciosInmuebles;

	public AdminWeb(Set<TipoDeInmueble> tiposDeInmueble, Set<Categoria> categoriasPropietario,
			Set<Categoria> categoriasInquilino, Set<Categoria> categoriasInmueble, Set<Servicio> serviciosInmuebles) {
		this.tiposDeInmueble.addAll(tiposDeInmueble);
		this.categoriasPropietario.addAll(categoriasPropietario);
		this.categoriasInquilino.addAll(categoriasInquilino);
		this.categoriasInmueble.addAll(categoriasInmueble);
		this.serviciosInmuebles.addAll(serviciosInmuebles);

	}

	public void darDeAltaTipoInmueble(TipoDeInmueble tipoInmueble) {
		this.tiposDeInmueble.add(tipoInmueble);
	}

	public void darDeAltaCategoriaPropietario(Categoria categoria) {
		this.categoriasPropietario.add(categoria);
	}

	public void darDeAltaCategoriaInquilino(Categoria categoria) {
		this.categoriasInquilino.add(categoria);
	}

	public void darDeAltaCategoriaInmueble(Categoria categoria) {
		this.categoriasInmueble.add(categoria);
	}

	public void darDeAltaServicioInmueble(Servicio servicio) {
		this.serviciosInmuebles.add(servicio);
	}

	public void eliminarTipoInmueble(TipoDeInmueble tipoInmueble) {
		this.tiposDeInmueble.remove(tipoInmueble);
	}

	public void eliminarCategoriaPropietario(Categoria categoria) {
		this.categoriasPropietario.remove(categoria);
	}

	public void eliminarCategoriaInquilino(Categoria categoria) {
		this.categoriasInquilino.remove(categoria);
	}

	public void eliminarCategoriaInmueble(Categoria categoria) {
		this.categoriasInmueble.remove(categoria);
	}

	public void eliminarServicioInmueble(Servicio servicio) {
		this.serviciosInmuebles.remove(servicio);
	}

	public Set<Inquilino> topTenInquilinos() {
		// FALTA IMPLEMENTAR
		return null;
	}

	public Set<Inmueble> inmueblesLibres() {
		// FALTA IMPLEMENTAR
		return null;
	}

	public double tasaOcupacion() {
		// FALTA IMPLEMENTAR
		return 0;
	}

	public void validarRanking(Usuario u, String comentario, int puntaje, String categoria) {
		if (categorias.contains(categoria)) {
			RankingUsuario r = new RankingUsuario(comentario, puntaje, categoria, u); // DAR DE ALTA
			web.agregarRanking(r);
		} else {
			throw new CategoriaException("La categoria no está registrada en el sistema.");
		}
	}

	public void validarRankingDeInmueble(Inmueble i, String comentario, int puntaje, String categoria) {
		if (categorias.contains(categoria)) {
			RankingInmueble r = new RankingInmueble(categoria, puntaje, comentario, i); // DAR DE ALTA
			web.agregarRanking(r);
		} else {
			throw new IllegalArgumentException("La categoria no está registrada en el sistema.");
		}
	}

}
