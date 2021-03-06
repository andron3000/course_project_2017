package module.optimization.constraint;

/**
 * @author Andriy Chekhovych
 */
public abstract class AbstractEqualityConstraint extends AbstractConstraint {
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        AbstractEqualityConstraint that = (AbstractEqualityConstraint) other;

        if (!super.equals(that))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
