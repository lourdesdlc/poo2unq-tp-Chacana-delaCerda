package ranking;

import java.util.List;

import categoria.Categoria;

public interface Rankeable {

	public void agregarRanking(Ranking ranking);

	public List<Ranking> getRankings();

	public List<String> getComentarios();

	public double getPuntajePromedio();
	
	public double getPuntajePromedioEnCategoria(Categoria categoria);

}
