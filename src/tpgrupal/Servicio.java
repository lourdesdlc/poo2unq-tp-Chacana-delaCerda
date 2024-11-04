package tpgrupal;

public class Servicio {
	//Elegi que sea una clase por si en un futuro se quisieran agregar mas servicios
	//que los mencionados en el enunciado
    private String nombre;

    public Servicio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
