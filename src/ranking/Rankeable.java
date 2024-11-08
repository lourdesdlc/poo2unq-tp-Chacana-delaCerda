package ranking;

public interface Rankeable {

	public void agregarRanking(Ranking ranking);

	public Ranking obtenerRankings(Ranking ranking);

	public String obtenerComentariosDe(Ranking ranking);

	public double obtenerPromedioPuntajeDe(Ranking ranking);

}
