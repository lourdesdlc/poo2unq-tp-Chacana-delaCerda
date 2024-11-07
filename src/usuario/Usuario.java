package usuario;

import ranking.Rankeable;
import ranking.Ranking;

public class Usuario extends Rankeable{
    private String nombreCompleto;
    private String email;
    private String telefono;

    public Usuario(String nombreCompleto, String email, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
    }
	//MISMO METODO PARA CADA RANKEADOR pero NO para RANKEABLE
    public void rankear(Rankeable rankeable, Ranking ranking) {
    	rankeable.agregarRanking(ranking);
    }
    
	public void mostrarHistorial() {
		//información propia del dueño, el puntaje que otros usuarios le han dado a él mismo
		//y el puntaje promedio que ha obtenido
		//LO MISMO PARA INQUILINO
	};
    
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

