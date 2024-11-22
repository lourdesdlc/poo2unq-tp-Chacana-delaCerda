package ranking;

import java.util.List;

public class GestorRanking {
	
	public static double getPuntajePromedio(List<Ranking> rankings){
		return rankings.stream()          
		        .mapToDouble(Ranking::getPuntajePromedio) 
		        .average()                           
		        .orElse(0.0);
	}

    public static double getPuntajePromedioEnCategoria(List<Ranking> rankings, Categoria categoria) {
        return rankings.stream() 
                .flatMap(ranking -> ranking.getPuntajePorCategoria().stream())
                .filter(puntajePorCategoria -> puntajePorCategoria.getCategoria().equals(categoria))
                .mapToDouble(PuntajePorCategoria::getPuntaje) 
                .average() 
                .orElse(0.0); 
    }

    public static List<String> getComentarios(List<Ranking> rankings) {
        return rankings.stream()
                        .map(Ranking::getComentario)
                        .toList();
    }
    
    public static List<String> getComentariosPorRol(List<Ranking> rankings, TipoRankeable tipoCategoria) {
        return rankings.stream()
                .filter(ranking -> ranking.getTipoRankeable() == tipoCategoria)
                .map(Ranking::getComentario)
                .toList();
    }

}
