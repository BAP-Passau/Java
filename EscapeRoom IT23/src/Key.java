import java.util.ArrayList;
import java.util.Random;

public class Key extends Item {
    private int price;
    private ArrayList<Coin> coins;

    public Key(Position position) {
        super(position);

        Random r = new Random();

        this.price = r.nextInt(1, 4);
        this.coins = new ArrayList<>();

        System.out.println("The key is at position x:" + this.position.getX() + " y:" + this.position.getY() + ".");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;

        System.out.println("The key is at position x:" + this.position.getX() + " y:" + this.position.getY() + ".");
    }

    public ArrayList<Position> keyArea() {
        ArrayList<Position> keyArea = new ArrayList<>();

        int keyXPos = this.position.getX();
        int keyYPos = this.position.getY();

        for (int x = keyXPos - 3; x < keyXPos + 4; x++) {
            for (int y = keyYPos - 3; y < keyYPos + 4; y++) {
                Position keyAreaPos = new Position(x, y);

                if (!keyAreaPos.equals(this.position)) {
                    keyArea.add(new Position(x, y));
                }
            }
        }

        return keyArea;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
