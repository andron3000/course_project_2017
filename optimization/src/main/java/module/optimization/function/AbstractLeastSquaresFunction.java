package module.optimization.function;

import module.math.matrix.Matrix;
import module.math.matrix.Vector;

/**
 * @author Andriy Chekhovych
 */
public abstract class AbstractLeastSquaresFunction extends AbstractFunction {
    @Override
    public final double computeValue(Vector point) {
        Vector residuals = computeResiduals(point);
        return 0.5 * residuals.inner(residuals);
    }

    @Override
    public final Vector computeGradient(Vector point) {
        Vector residuals = computeResiduals(point);
        Matrix jacobian = computeJacobian(point);
        return jacobian.transpose().multiply(residuals);
    }

    @Override
    public final Matrix computeHessian(Vector point) {
        Matrix jacobian = computeJacobian(point);
        return jacobian.transpose().multiply(jacobian);
    }

    public abstract Vector computeResiduals(Vector point);
    public abstract Matrix computeJacobian(Vector point);

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        AbstractLeastSquaresFunction that = (AbstractLeastSquaresFunction) other;

        if (!super.equals(that))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
