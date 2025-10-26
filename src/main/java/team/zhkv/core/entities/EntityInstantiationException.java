package team.zhkv.core.entities;

/**
 * Exception thrown when an entity cannot be instantiated.
 *
 * @author bonnyped
 */
public class EntityInstantiationException extends RuntimeException {
    /**
     * Constructs a new EntityInstantiationException with the specified message and
     * cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public EntityInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
