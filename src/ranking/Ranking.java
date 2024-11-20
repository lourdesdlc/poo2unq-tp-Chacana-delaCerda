package ranking;

import java.util.ArrayList;
import java.util.List;

import categoria.Categoria;
import sitioWeb.SitioWeb;


public class Ranking {
	/*
	 * Para inicializar Ranking solo se necesita un comentario, si quieren 
	 * puntuar categorias, se hace una por una, esto permite que se puedan 
	 * validar las categorias que se intentan puntuar
	 */
	private SitioWeb sitio;
	private List<PuntajePorCategoria> puntajePorCategoria; 
	private String comentario;
	
	public Ranking(String comentario) {
		this.setComentario(comentario);
		this.puntajePorCategoria = new ArrayList<>();
	}

	public List<PuntajePorCategoria> getPuntajePorCategoria(){
		return puntajePorCategoria;
	}
	
	public void agregarPuntajePorCategoria(PuntajePorCategoria puntajePorCategoria) {
		validarCategoria(puntajePorCategoria.getCategoria());
		this.puntajePorCategoria.add(puntajePorCategoria);
	}
	
	private void validarCategoria(Categoria categoria) {
        if(!sitio.esCategoriaValida(categoria)){
        	throw new RuntimeException("La categoría ingresada no es válida");
        }
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
