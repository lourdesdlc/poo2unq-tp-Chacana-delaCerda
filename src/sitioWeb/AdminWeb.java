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
	private Set<Servicio> serviciosInmuebles;

	public AdminWeb(Set<String> categorias, SitioWeb web, Set<Servicio> serviciosInmuebles) {
		super();
		this.categorias = categorias;
		this.web = web;
		this.serviciosInmuebles = serviciosInmuebles;
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
