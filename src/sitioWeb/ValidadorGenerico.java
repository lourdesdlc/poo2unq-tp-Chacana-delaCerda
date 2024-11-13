package sitioWeb;

import java.util.Set;

public interface ValidadorGenerico {
	<T> boolean validarRegistro(T objeto, Set<T> conjunto);
}
