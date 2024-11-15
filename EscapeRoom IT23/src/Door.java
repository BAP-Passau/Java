import java.io.Serializable;

public class Door implements Serializable {
    private Position position;
    private Key key;

    public Door(Key key, Position position) {
        this.position = position;

        System.out.println("The door is at position " + this.position.getPositionString() + ".");

        this.key = key;
    }

    public boolean unlockDoor(Key key) {
        if (key.equals(this.key)) {
            return true;
        } else {
            return false;
        }
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Key getKey() {
        return this.key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
