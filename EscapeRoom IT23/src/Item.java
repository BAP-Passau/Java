import java.io.Serializable;

public abstract class Item implements Serializable {
    protected Position position;

    public Item() {
        this.position = new Position();
    }

    public Item(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

        System.out.println("The item is at position " + this.position.getPositionString() + ".");
    }
}
