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
    
 // Método para obtener los comentarios de todos los rankings
    public List<String> obtenerComentarios() {
        return rankingsRecibidos.stream()  // Crea un stream de la lista de rankings
                .map(Ranking::getComentario)  // Mapea cada ranking a su comentario
                .collect(Collectors.toList());  // Recoge los comentarios en una lista
    }

    // Método para obtener el promedio de puntajes de todos los rankings
    public double obtenerPromedioPuntaje() {
        return rankingsRecibidos.stream()          // Crea un stream de los rankings
                .mapToDouble(Ranking::calcularPromedio) // Mapea cada ranking a su promedio
                .average()                           // Calcula el promedio de los promedios
                .orElse(0.0);                        // Si no hay rankings, devuelve 0.0
    }
}
