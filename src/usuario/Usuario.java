package usuario;

import ranking.Rankeable;

public class Usuario extends Rankeable{
    private String nombreCompleto;
    private String email;
    private String telefono;

    public Usuario(String nombreCompleto, String email, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
    }

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

