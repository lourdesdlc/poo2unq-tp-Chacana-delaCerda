package Inmueble;

public class TipoInmueble {
	//Elegi que sea una clase porque el Admin tiene que darlos de alta
    private String nombre;
    public TipoInmueble(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
}
