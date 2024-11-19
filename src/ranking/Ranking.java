package ranking;

import categoria.Categoria;

public class Ranking {

	private Categoria categoria; // Ej Limpieza
	private int puntaje; // del 1 al 5
	private String comentario;

	//ahora un Ranking es un puntaje por categoria con un comentario
	//pero podriamos evaluar que Ranking tenga una lista de "PuntajePorCategoria" que sea una class
	//y ademas el comentario
	
	public Ranking(Categoria categoria, int puntaje, String comentario) {
		super();

		this.setCategoria(categoria);
		this.setPuntaje(puntaje);
		this.comentario = comentario;
	}

	/*
	public double calcularPromedio() {
		hacer nueva logica }*/
	
	private void validarPuntaje(int puntaje) {
        if(puntaje < 1 || puntaje > 5){
            throw new RuntimeException("El puntaje debe estar en una escala del 1 al 5.");
        }
    }
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		//validar categoria
		this.categoria = categoria;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.validarPuntaje(puntaje);
		this.puntaje = puntaje;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
