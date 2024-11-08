package tpgrupal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import filtroDeBusqueda.CriterioBusqueda;
import ranking.Rankeable;
import ranking.Ranking;
import usuario.Usuario;

public class SitioWeb {
	private AdminWeb admin;
	private Set<Inmueble> inmuebles;
	private Set<Usuario> usuarios;
	private Set<Ranking> rankeados;

	public void registrarUsuario(Usuario usuario) {
		usuario.registrarEnWeb(this); // vinculacion a mismo sitioWeb
		usuarios.add(usuario);
	}

	// pensar en clase Validador.
	public void registrarInmueble(Inmueble inmueble) {

		if (usuarios.contains(inmueble.getPropietario())) {

			inmuebles.add(inmueble);
		}

		else {
			throw new IllegalArgumentException("El propietario no está registrado en el sistema.");
		}
	}

	public List<Inmueble> buscarInmuebles(CriterioBusqueda criterio) {
		// Filtra la lista de inmuebles usando el criterio recibido como parámetro
		return criterio.buscar(inmuebles);
	}

	public void mostrarDetallesInmueble(Inmueble inmueble) {
	}

	public void validarRankingPara(Usuario u, String comentario, int puntaje, String categoria) {
		admin.validarRanking(u, comentario, puntaje, categoria);

	}

	// acoplamiento de mensaje?
	public void validarRankingPara(Inmueble i, String comentario, int puntaje, String categoria) {
		admin.validarRankingDeInmueble(i, comentario, puntaje, categoria);

	}
	
	public void agregarRanking(Ranking ranking) {
		this.rankeados.add(ranking);

	}

	public AdminWeb getAdmin() {
		return admin;
	}

	public void setAdmin(AdminWeb admin) {
		this.admin = admin;
	}

	public Set<Inmueble> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(Set<Inmueble> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Ranking> getRankingsRecibidos() {
		return rankeados;
	}

	public void setRankingsRecibidos(Set<Ranking> rankingsRecibidos) {
		this.rankeados = rankingsRecibidos;

	}

}