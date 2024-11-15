public class Program {
    public static void main(String[] args) {
        System.out.println(System.lineSeparator().concat("=== Methods with default parameters thru overloading ===".concat(System.lineSeparator())));

        MethodsWithDefaultParameters methodsWithDefaultParametersInstance = new MethodsWithDefaultParameters();

        methodsWithDefaultParametersInstance.doSomething();
        methodsWithDefaultParametersInstance.doSomething(6);
        methodsWithDefaultParametersInstance.doSomething(3, "three");

        System.out.println(System.lineSeparator().concat("=== Enum with values ===".concat(System.lineSeparator())));

        System.out.println(EnumWithValues.ZERO.toString().concat(": ".concat(Double.toString(EnumWithValues.ZERO.value))));
        System.out.println(EnumWithValues.ONE.toString().concat(": ".concat(Double.toString(EnumWithValues.ONE.value))));
        System.out.println(EnumWithValues.TWO.toString().concat(": ".concat(Double.toString(EnumWithValues.TWO.value))));
    }
}
