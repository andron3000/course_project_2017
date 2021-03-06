package module.optimization.constraint;

import module.math.matrix.Matrix;
import module.math.matrix.SingularMatrixException;
import module.math.matrix.Vector;
import module.utilities.MathUtilities;

/**
 * @author Andriy Chekhovych
 */
public abstract class AbstractConstraint {
    protected final double epsilon = MathUtilities.computeMachineEpsilonDouble();

    private int numberOfConstraintEvaluations = 0;
    private int numberOfJacobianEvaluations = 0;

    public final Vector getValue(Vector point) {
        numberOfConstraintEvaluations++;
        return computeValue(point);
    }

    abstract protected Vector computeValue(Vector point);

    public final Matrix getJacobian(Vector point) {
        numberOfJacobianEvaluations++;
        return computeJacobian(point);
    }

    abstract protected Matrix computeJacobian(Vector point);

    public abstract Vector project(Vector point)
            throws SingularMatrixException;

    public final int getNumberOfConstraintEvaluations() {
        return numberOfConstraintEvaluations;
    }

    public final int getNumberOfJacobianEvaluations() {
        return numberOfJacobianEvaluations;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        AbstractConstraint that = (AbstractConstraint) other;

        if (numberOfConstraintEvaluations != that.numberOfConstraintEvaluations)
            return false;
        if (numberOfJacobianEvaluations != that.numberOfJacobianEvaluations)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numberOfConstraintEvaluations;
        result = 31 * result + numberOfJacobianEvaluations;
        return result;
    }
}
