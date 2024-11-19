package ranking;

import java.util.List;


public class Ranking {

	private List<PuntajePorCategoria> puntajePorCategoria; 
	private String comentario;
	
	public Ranking(List<PuntajePorCategoria> puntajePorCategoria, String comentario) {

		this.setPuntajePorCategoria(puntajePorCategoria);
		this.comentario = comentario;
	}

	private void setPuntajePorCategoria(List<PuntajePorCategoria> puntajePorCategoria) {
		this.puntajePorCategoria = puntajePorCategoria;
		
	}

	public List<PuntajePorCategoria> getPuntajePorCategoria(){
		return puntajePorCategoria;
	}
	
	public double getPuntajePromedio() {
		return puntajePorCategoria.stream()          
        .mapToDouble(PuntajePorCategoria::getPuntaje) 
        .average()                           
        .orElse(0.0);
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
