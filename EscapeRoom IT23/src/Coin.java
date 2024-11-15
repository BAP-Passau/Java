public class Coin extends Item {
    public Coin(Position position) {
        super(position);

        System.out.println("The coin is at position " + this.position.getPositionString() + ".");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;

        System.out.println("The coin is at position " + this.position.getPositionString() + ".");
    }
}
