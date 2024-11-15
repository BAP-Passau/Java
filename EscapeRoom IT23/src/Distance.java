import java.io.Serializable;

public enum Distance implements Serializable {
    KEY_AREA_DISTANCE(3.0),
    COIN_AREA_DISTANCE(2.0),
    MATCH(0.0);

    public final double value;

    private Distance(double value) {
        this.value = value;
    }
}
