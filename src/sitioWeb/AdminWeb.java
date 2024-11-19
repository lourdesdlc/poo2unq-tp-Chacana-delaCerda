package sitioWeb;

import java.util.List;

import Inmueble.Inmueble;
import Inmueble.TipoInmueble;
import categoria.Categoria;
import categoria.TipoRankeable;
import ranking.Ranking;
import tpgrupal.Servicio;
import usuario.Usuario;

public class AdminWeb { // ESTO SEGURO SE PUEDE MEJORAR MAS ADELANTE.
	//L: Para mi hay que eliminar esta clase y colocar los metodos de dar de alta en sitio
	private List<Servicio> serviciosInmuebles;
	private List<TipoInmueble> tiposDeInmueble;
	private List<Ranking> rankings;
	
	private List<Categoria> categorias;

	public AdminWeb(List<TipoInmueble> tiposDeInmueble, List<Categoria> categorias, List<Servicio> serviciosInmuebles) {
		this.tiposDeInmueble.addAll(tiposDeInmueble);
		this.categorias.addAll(categorias);
		this.serviciosInmuebles.addAll(serviciosInmuebles);
		
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
	
	public List<Categoria> getListaDeCategoriasValidas(TipoRankeable tipoRankeable) {
        return categorias.stream()
                         .filter(categoria -> categoria.getTipoRankeable() == (tipoRankeable))
                         .toList();
    }
	
	public boolean esCategoriaValida(Categoria categoria){
        return getListaDeCategoriasValidas(categoria.getTipoRankeable())
        		.contains(categoria);
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
