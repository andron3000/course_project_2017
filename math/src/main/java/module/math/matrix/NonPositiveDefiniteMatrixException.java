package module.math.matrix;

/**
 * @author Andriy Chekhovych
 */
public class NonPositiveDefiniteMatrixException extends Exception {
    private static final long serialVersionUID = -580851088236168017L;

    public NonPositiveDefiniteMatrixException(String message) {
        super(message);
    }
}
