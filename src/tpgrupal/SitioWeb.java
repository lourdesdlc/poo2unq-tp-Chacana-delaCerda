package tpgrupal;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void registrarInmueble(Inmueble inmueble) {
        inmuebles.add(inmueble);
    }

    public List<Inmueble> buscarInmuebles(CriterioBusqueda criterio) {
    	// Filtra la lista de inmuebles usando el criterio recibido como parámetro
        return inmuebles.stream()
                .filter(criterio::cumple)
                .collect(Collectors.toList());
    }

    public void mostrarDetallesInmueble(Inmueble inmueble){}

	/*public void darDeAltaInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		
	}; */
}
