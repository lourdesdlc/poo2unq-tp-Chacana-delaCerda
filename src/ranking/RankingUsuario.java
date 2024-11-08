package ranking;

import usuario.Usuario;

public class RankingUsuario extends Ranking {

	private Usuario usarioRankeado; // a quien se califica

	public RankingUsuario(String categoria, int puntajesPorCategoria, String comentario, Usuario usarioRankeado) {
		super(categoria, puntajesPorCategoria, comentario);
		this.usarioRankeado = usarioRankeado;
	}

}
