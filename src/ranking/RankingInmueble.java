package ranking;

import tpgrupal.Inmueble;
import usuario.Usuario;

public class RankingInmueble extends Ranking {
	private Inmueble inmueble;

	public RankingInmueble(String categoria, int puntajesPorCategoria, String comentario, Inmueble inmueble) {
		super(categoria, puntajesPorCategoria, comentario);
		this.inmueble = inmueble;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

}
