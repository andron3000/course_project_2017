package module.optimization.function;

import module.math.matrix.Matrix;
import module.math.matrix.Vector;
import module.math.matrix.Vectors;
import module.optimization.constraint.AbstractInequalityConstraint;

import java.util.List;

/**
 * @author Andriy Chekhovych
 */
public final class LogBarrierFunction extends AbstractFunction {
    private final List<AbstractInequalityConstraint> inequalityConstraints;

    public LogBarrierFunction(List<AbstractInequalityConstraint> inequalityConstraints) {
        this.inequalityConstraints = inequalityConstraints;
    }

    @Override
    protected double computeValue(Vector point) {
        return -inequalityConstraints.stream()
                .map(constraint -> -Math.log(-constraint.getValue(point)))
                .reduce(0.0, Double::sum);
    }

    @Override
    protected Vector computeGradient(Vector point) throws NonSmoothFunctionException {
        Vector gradient = Vectors.build(point.size(), point.type());
        for (AbstractInequalityConstraint constraint : inequalityConstraints)
            gradient.addInPlace(constraint.computeGradient(point).multInPlace(-1 / constraint.computeValue(point)));
        return gradient;
    }

    @Override
    protected Matrix computeHessian(Vector point) throws NonSmoothFunctionException {
        Matrix hessian = Matrix.zeros(point.size(), point.size());
        for (AbstractInequalityConstraint constraint : inequalityConstraints) {
            double value = constraint.computeValue(point);
            Vector gradient = constraint.computeGradient(point);
            hessian.addEquals(gradient.outer(gradient).multiplyEquals(1 / (value * value))
                                      .subtractEquals(constraint.computeHessian(point).multiplyEquals(1 / value)));
        }
        return hessian;
    }
}
