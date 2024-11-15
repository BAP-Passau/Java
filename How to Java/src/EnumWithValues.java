/**
 * implement an enum with a value for each element
 */
public enum EnumWithValues {
    /**
     * create the instances
     */
    ZERO(0.0),
    ONE(1.0),
    TWO(2.0);

    /**
     * Enum can have attributes
     */
    public final double value;

    /**
     * private constructor to create the instances
     * @param value
     */
    private EnumWithValues(double value) {
        this.value = value;
    }
}
