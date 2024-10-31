package usuario;
import java.util.List;

import tpgrupal.Ranking;

public class Usuario {
    private String nombreCompleto;
    private String email;
    private String telefono;
    private Ranking ranking;

    public Usuario(String nombreCompleto, String email, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.ranking = new Ranking();
    }

    public void agregarPuntajeUsuario(int puntaje, String comentario) {
        ranking.agregarPuntaje(puntaje, comentario);
    }

    public double obtenerPromedioPuntajeUsuario() {
        return ranking.obtenerPromedio();
    }

    public List<String> obtenerComentariosUsuario() {
        return ranking.getComentarios();
    }

    // Getters y setters
    public String getEmail() {
        return email;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }

	public String getTelefono() {
		return telefono;
	}
}

