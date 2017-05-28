package module.optimization.linesearch;

import module.math.matrix.Vector;
import module.optimization.function.NonSmoothFunctionException;
import module.optimization.function.QuadraticFunction;

/**
 * Class implementing an exact line search algorithm. It currently requires the objective function to be a quadratic
 * function.
 *
 * @author Andriy Chekhovych
 */
public final class ExactLineSearch implements LineSearch {
    /** The objective function instance. */
    private final QuadraticFunction objective;

    /**
     * Constructs an exact line search solver for the given objective function instance.
     *
     * @param   objective   The objective function instance.
     */
    public ExactLineSearch(QuadraticFunction objective) {
        this.objective = objective;
    }

    /** {@inheritDoc} */
    @Override
    public double computeStepSize(int iterationNumber,
                                  Vector point,
                                  Vector direction,
                                  Vector previousPoint,
                                  Vector previousDirection,
                                  double previousStepSize)
            throws NonSmoothFunctionException {
        return -objective.getGradient(point).inner(direction)
                / direction.transMult(objective.getA()).inner(direction);
    }
}
