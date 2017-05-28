package module.math.matrix;

/**
 * @author Andriy Chekhovych
 */
public class NonSymmetricMatrixException extends Exception {
    private static final long serialVersionUID = 9153953427416283275L;

    public NonSymmetricMatrixException(String message) {
        super(message);
    }
}
