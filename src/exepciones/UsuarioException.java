package exepciones;

public class UsuarioException extends RuntimeException {
    public UsuarioException(String mensaje) {
        super(mensaje);
    }
}