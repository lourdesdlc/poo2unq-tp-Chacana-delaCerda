package sitioWeb;

import java.util.Set;

public class Validador implements ValidadorGenerico {

	// "si el dia de mañana" cambia la forma de validacion, solo se cambia en este
	// metodo y esta clase
	// cumple S de solid... solo un motivo para cambiar...

	@Override
	public <T> boolean validarRegistro(T objeto, Set<T> conjunto) { // PREGUNTAR SI ESTO ESTA BIEN

		return conjunto.contains(objeto);
	}

	// otra opcion seria tener dos mensajes, uno por cada tipo de objeto a
	// registrarse
}
