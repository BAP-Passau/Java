public abstract class Item {
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

        System.out.println("The item is at position x:" + this.position.getX() + " y:" + this.position.getY() + ".");
    }
}