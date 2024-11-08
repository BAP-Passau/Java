import java.util.ArrayList;

public class Coin extends Item {
    public Coin(Position position) {
        super(position);

        System.out.println("The coin is at position x:" + this.position.getX() + " y:" + this.position.getY() + ".");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;

        System.out.println("The coin is at position x:" + this.position.getX() + " y:" + this.position.getY() + ".");
    }

    public ArrayList<Position> coinArea() {
        ArrayList<Position> coinArea = new ArrayList<>();

        int coinXPos = this.position.getX();
        int coinYPos = this.position.getY();

        for (int x = coinXPos - 2; x < coinXPos + 3; x++) {
            for (int y = coinYPos - 2; y < coinYPos + 3; y++) {
                Position coinAreaPos = new Position(x, y);

                if (!coinAreaPos.equals(this.position)) {
                    coinArea.add(new Position(x, y));
                }
            }
        }

        return coinArea;
    }
}
