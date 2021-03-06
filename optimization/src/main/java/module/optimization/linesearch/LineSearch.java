package module.optimization.linesearch;

import module.math.matrix.Vector;
import module.optimization.function.NonSmoothFunctionException;

/**
 * Interface specifying the methods for which all classes implementing line search algorithms should have
 * implementations.
 *
 * @author Andriy Chekhovych
 */
public interface LineSearch {
    /**
     * Computes the step size value using the implemented algorithm.
     *
     *
     * @param   iterationNumber     The current iteration of the solver.
     * @param   point               The point at which we perform the line search.
     * @param   direction           The direction for which we perform the line search.
     * @param   previousPoint       The previous point selected by the optimization algorithm (used by some step size
     *                              initialization methods for iterative line search algorithms).
     * @param   previousDirection   The previous direction selected by the optimization algorithm (used by some step
     *                              size initialization methods for iterative line search algorithms).
     * @param   previousStepSize    The previous step size used by the optimization algorithm (used by some step
     *                              size initialization methods for iterative line search algorithms).
     * @return                      A step size value that satisfies certain criteria that depend on the algorithm
     *                              choice.
     */
     public double computeStepSize(int iterationNumber,
                                   Vector point,
                                   Vector direction,
                                   Vector previousPoint,
                                   Vector previousDirection,
                                   double previousStepSize) throws NonSmoothFunctionException;
}
