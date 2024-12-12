package ranking;

import exepciones.PuntajeException;

public class PuntajePorCategoria {
	private Categoria categoria;
	private int puntaje;
	
	public PuntajePorCategoria(Categoria categoria, int puntaje) {
		this.setCategoria(categoria);
		this.setPuntaje(puntaje);
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.validarPuntaje(puntaje);
		this.puntaje = puntaje;
	}
	
	void validarPuntaje(int puntaje) {
		if(puntaje < 1 || puntaje > 5){
			throw new PuntajeException("El puntaje debe estar en una escala del 1 al 5.");
		}
	}
}
