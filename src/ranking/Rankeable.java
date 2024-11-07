package ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Rankeable {
	private List<Ranking> rankingsRecibidos = new ArrayList<>();
	/*
	 * FALTA terminar
	 */
	
	//List<CategoriaRanking> obtenerCategorias();
	//Administrador administradorDelSitio;

    public void agregarRanking(Ranking ranking) {
        rankingsRecibidos.add(ranking);
    }

    public List<Ranking> obtenerRankings() {
        return new ArrayList<>(rankingsRecibidos);
    }
    
    public List<String> obtenerComentarios() {
        return rankingsRecibidos.stream()  
                .map(Ranking::getComentario)  
                .collect(Collectors.toList());  
    }

    public double obtenerPromedioPuntaje() {
        return rankingsRecibidos.stream()          
                .mapToDouble(Ranking::calcularPromedio) 
                .average()                           
                .orElse(0.0);            
    }
}
