package ranking;
import java.util.Map;

 public class Ranking {
	 /*
	  * FALTA terminar
	  */
	 
    private Rankeable entidadRanqueada; 
    private Rankeable entidadRanqueadora;  
    private Map<String, Double> puntajesPorCategoria; 
    private String comentario;  

    public Ranking(Rankeable entidadRanqueada, Rankeable entidadRanqueadora, Map<String, Double> puntajesPorCategoria, String comentario) {
        this.entidadRanqueada = entidadRanqueada;
        this.entidadRanqueadora = entidadRanqueadora;
        this.puntajesPorCategoria = puntajesPorCategoria;
        this.comentario = comentario;
    }

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
