package ranking;
import java.util.Map;

 public class Ranking {
	 /*
	  * FALTA terminar
	  */
	 
    private Rankeable entidadRanqueada;  // En este caso, el inmueble.
    private Rankeable entidadRanqueadora;  // El inquilino.
    private Map<String, Double> puntajesPorCategoria; // Puntajes por categoría.
    private String comentario;  // Comentario del inquilino.

    public Ranking(Rankeable entidadRanqueada, Rankeable entidadRanqueadora, Map<String, Double> puntajesPorCategoria, String comentario) {
        this.entidadRanqueada = entidadRanqueada;
        this.entidadRanqueadora = entidadRanqueadora;
        this.puntajesPorCategoria = puntajesPorCategoria;
        this.comentario = comentario;
    }

    // Método para calcular el promedio
    public double calcularPromedio() {
        double suma = 0;
        for (Double puntaje : puntajesPorCategoria.values()) {
            suma += puntaje;
        }
        return suma / puntajesPorCategoria.size();
    }

    // Métodos getters
    public Rankeable getEntidadRanqueada() {
        return entidadRanqueada;
    }

    public Rankeable getEntidadRanqueadora() {
        return entidadRanqueadora;
    }

    public Map<String, Double> getPuntajesPorCategoria() {
        return puntajesPorCategoria;
    }

    public String getComentario() {
        return comentario;
    }
}
