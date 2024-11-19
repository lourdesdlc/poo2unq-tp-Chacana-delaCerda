package ranking;

import categoria.Categoria;
import usuario.Usuario;

public class RankingUsuario extends Ranking {

	private Usuario usuarioRankeado; // a quien se califica

	public RankingUsuario(Categoria categoria, int puntajesPorCategoria, String comentario, Usuario usarioRankeado) {
		super(categoria, puntajesPorCategoria, comentario);
		this.usuarioRankeado = usarioRankeado;
	}

}
