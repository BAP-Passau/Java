/**
 * Implement methods with default values thru overlaoding
 */
public class MethodsWithDefaultParameters {
    public void doSomething() {
        this.doSomething(0, "");
    }

    public void doSomething(int i) {
        this.doSomething(i, "");
    }

    public void doSomething(String s) {
        this.doSomething(0, s);
    }

    public void doSomething(int i, String s) {
        System.out.println(s.concat(" ").concat(Integer.toString(i)));
    }
}
