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

        System.out.println("The key is at position " + this.position.getPositionString() + ".");
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;

        System.out.println("The key is at position " + this.position.getPositionString() + ".");
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
