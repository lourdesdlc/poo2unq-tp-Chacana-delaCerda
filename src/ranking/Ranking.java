package ranking;

import java.util.ArrayList;
import java.util.List;

import exepciones.CategoriaException;
import usuario.Usuario;


public class Ranking {

	private List<Categoria> categorias;
	private Usuario rankeador; //usuario que rankea
	private TipoRankeable tipoRankeable;
	
	private List<PuntajePorCategoria> puntajePorCategoria; 
	private String comentario;
	
	public Ranking(String comentario, Usuario rankeador, TipoRankeable tipoRankeable, List<Categoria> categorias) {
		this.setComentario(comentario);
		this.setRankeador(rankeador);
		this.tipoRankeable = tipoRankeable;
		this.setCategorias(categorias);
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
	    if (!categorias.contains(categoria)) {
	        throw new CategoriaException("La categoría ingresada no es válida.");
	    }
	}
	
	public double getPuntajePromedio() {
		return puntajePorCategoria.stream()          
	        .mapToDouble(PuntajePorCategoria::getPuntaje) 
	        .average()                           
	        .orElse(0.0);
	}
	
	private void setCategorias(List<Categoria> categoriasValidas) {
        this.categorias = categoriasValidas;
    }
	
	public List<Categoria> getCategorias() {
        return categorias;
    }
	
	private void setRankeador(Usuario rankeador) {
		this.rankeador = rankeador;
	}
	
	public Usuario getRankeador() {
		return rankeador;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public TipoRankeable getTipoRankeable() {
		return tipoRankeable;
	}

}
