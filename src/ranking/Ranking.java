package ranking;

import java.util.Map;

import usuario.Usuario;

public class Ranking {

	private String categoria; // Ej Limpieza
	private int puntajesPorCategoria; // del 1 al 5
	private String comentario;

	public Ranking(String categoria, int puntajesPorCategoria, String comentario) {
		super();

		this.categoria = categoria;
		this.puntajesPorCategoria = puntajesPorCategoria;
		this.comentario = comentario;
	}

	/*
	public double calcularPromedio() {
		hacer nueva logica }*/
	

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getPuntajesPorCategoria() {
		return puntajesPorCategoria;
	}

	public void setPuntajesPorCategoria(int puntajesPorCategoria) {
		this.puntajesPorCategoria = puntajesPorCategoria;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
