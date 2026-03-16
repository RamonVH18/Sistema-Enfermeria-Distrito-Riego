package exception;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class CitasException extends Exception {

    /**
     * Creates a new instance of <code>CitasException</code> without detail
     * message.
     */
    public CitasException() {}

    /**
     * Constructs an instance of <code>CitasException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CitasException(String msg) {
        super(msg);
    }
}