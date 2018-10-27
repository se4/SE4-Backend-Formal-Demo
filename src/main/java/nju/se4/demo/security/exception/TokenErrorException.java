package nju.se4.demo.security.exception;

/**
 * Description:
 * token 错 401
 *
 * @author xxz
 * Created on 05/03/2018
 */
public class TokenErrorException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public TokenErrorException(String message) {
        super(message);
    }
}