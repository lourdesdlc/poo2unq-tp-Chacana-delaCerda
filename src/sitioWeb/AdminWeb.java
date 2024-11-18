package sitioWeb;

import java.util.List;

import Inmueble.Inmueble;
import Inmueble.TipoInmueble;
import categoria.Categoria;
import ranking.Ranking;
import tpgrupal.Servicio;
import usuario.Usuario;

public class AdminWeb { // ESTO SEGURO SE PUEDE MEJORAR MAS ADELANTE.
	private List<Servicio> serviciosInmuebles;
	private List<TipoInmueble> tiposDeInmueble;
	private List<Ranking> rankings;
	
	private List<Categoria> categoriasPropietario;
	private List<Categoria> categoriasInquilino;
	private List<Categoria> categoriasInmueble;

	public AdminWeb(List<TipoInmueble> tiposDeInmueble, List<Categoria> categoriasPropietario,
			List<Categoria> categoriasInquilino, List<Categoria> categoriasInmueble, List<Servicio> serviciosInmuebles) {
		this.tiposDeInmueble.addAll(tiposDeInmueble);
		this.categoriasPropietario.addAll(categoriasPropietario);
		this.categoriasInquilino.addAll(categoriasInquilino);
		this.categoriasInmueble.addAll(categoriasInmueble);
		this.serviciosInmuebles.addAll(serviciosInmuebles);
		
	}
	
	public void darDeAltaTipoInmueble(TipoInmueble tipoInmueble) {
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
	
	public void eliminarTipoInmueble(TipoInmueble tipoInmueble) {
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
	
	public List<Usuario> topTenInquilinos() {
		//FALTA IMPLEMENTAR 
		return null;
	}
	public List<Inmueble> inmueblesLibres() {
		//FALTA IMPLEMENTAR  
		return null;
	}
	public double tasaOcupacion() {
		//FALTA IMPLEMENTAR  
		return 0;
	}
/*
	public void validarRanking(Usuario u, String comentario, int puntaje, String categoria) {
		if (categorias.contains(categoria)) {
			RankingUsuario r = new RankingUsuario(comentario, puntaje, categoria, u); // DAR DE ALTA
			rankings.add(r);
		} else {
			throw new CategoriaException("La categoria no está registrada en el sistema.");
		}
	}

	public void validarRankingDeInmueble(Inmueble i, String comentario, int puntaje, String categoria) {
		if (categorias.contains(categoria)) {
			RankingInmueble r = new RankingInmueble(categoria, puntaje, comentario, i); // DAR DE ALTA
			rankings.add(r);
		} else {
			throw new IllegalArgumentException("La categoria no está registrada en el sistema.");
		}
	}
*/
}
