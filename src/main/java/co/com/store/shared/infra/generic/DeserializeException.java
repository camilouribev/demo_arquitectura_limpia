package co.com.store.shared.infra.generic;

public class DeserializeException extends RuntimeException {
    public DeserializeException(Throwable cause) {
        super(cause);
    }
}