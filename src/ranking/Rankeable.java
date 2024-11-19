package ranking;

import java.util.List;

public interface Rankeable {

	public void agregarRanking(Ranking ranking);

	public List<Ranking> getRankings();

	public List<String> getComentarios();

	public double getPuntajePromedio();
	
	//public double getPuntajePromedioPorCategoria(Categoria c);
	//o public Map<Categoria, int> getPuntajePromedioPorCategoria(); ??

}
