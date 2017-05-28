package module.optimization.function;

/**
 * @author Andriy Chekhovych
 */
public class NonSmoothFunctionException extends Exception {
    private static final long serialVersionUID = 1862641387391723829L;

    public NonSmoothFunctionException() {
        super("The function is not smooth.");
    }

    public NonSmoothFunctionException(String message) {
        super(message);
    }
}
