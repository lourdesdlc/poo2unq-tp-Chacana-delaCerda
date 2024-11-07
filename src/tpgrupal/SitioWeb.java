package tpgrupal;

import java.util.ArrayList;
import java.util.List;

import filtroDeBusqueda.CriterioBusqueda;
import usuario.Usuario;

public class SitioWeb {
	private List<Inmueble> inmuebles;
	private List<Usuario> usuarios;

	public SitioWeb() {
		inmuebles = new ArrayList<>();
		usuarios = new ArrayList<>();
	}

	public void registrarUsuario(Usuario usuario) {
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

	/*
	 * public void darDeAltaInmueble(Inmueble inmueble) { // TODO Auto-generated
	 * method stub
	 * 
	 * };
	 */
}
