package tpgrupal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Inmueble> buscarInmuebles(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida, 
                                          Integer capacidad, Double precioMin, Double precioMax) {
        // Implementar búsqueda con filtros
        return new ArrayList<>();  // Retorna lista filtrada
    }

    public void mostrarDetallesInmueble(Inmueble inmueble){}

	public void darDeAltaInmueble(Inmueble inmueble) {
		// TODO Auto-generated method stub
		
	};
}
