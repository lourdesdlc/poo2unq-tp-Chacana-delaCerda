package tpgrupal;

import java.util.Set;


import ranking.RankingInmueble;
import ranking.RankingUsuario;
import usuario.Usuario;

public class AdminWeb { // ESTO SEGURO SE PUEDE MEJORAR MAS ADELANTE. 
	private Set<String> categorias;
	private SitioWeb web;

	public void validarRanking(Usuario u, String comentario, int puntaje, String categoria) {
		if (categorias.contains(categoria)) {
			RankingUsuario r = new RankingUsuario(comentario, puntaje, categoria, u); // DAR DE ALTA
			web.agregarRanking(r);
		} else {
			throw new IllegalArgumentException("La categoria no está registrada en el sistema.");
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
