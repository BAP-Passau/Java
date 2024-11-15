import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {
    private Position position;
    private Room room;
    private Directions currentDirection;
    private ArrayList<Item> items;

    public Player() {
        this.position = new Position(0, 0);
        this.currentDirection = Directions.UP;
        this.room = null;
        this.items = new ArrayList<>();
    }

    public void putInPlace(Room room, int roomWidth, int roomLength) {
        Random r = new Random();

        this.position.setX(r.nextInt(1, roomWidth));
        this.position.setY(r.nextInt(1, roomLength));
        this.room = room;
    }

    public void turnRight() {
        switch (this.currentDirection) {
            case Directions.UP -> this.currentDirection = Directions.RIGHT;
            case Directions.RIGHT -> this.currentDirection = Directions.DOWN;
            case Directions.DOWN -> this.currentDirection = Directions.LEFT;
            case Directions.LEFT -> this.currentDirection = Directions.UP;
        }

        System.out.println("I'm going " + this.currentDirection + " now.");
    }

    public void turnLeft() {
        switch (this.currentDirection) {
            case Directions.UP -> this.currentDirection = Directions.LEFT;
            case Directions.LEFT -> this.currentDirection = Directions.DOWN;
            case Directions.DOWN -> this.currentDirection = Directions.RIGHT;
            case Directions.RIGHT -> this.currentDirection = Directions.UP;
        }

        System.out.println("I'm going " + this.currentDirection + " now.");
    }

    public void addItem(Item item) {
        System.out.println("Added " + item.getClass().getName().toLowerCase() + " to my items.");
        this.items.add(item);
        this.getRoom().removeItem(item);
    }

    public boolean move(Integer steps) {
        boolean proceedGame = true;

        switch (this.currentDirection) {
            case Directions.UP: {
                this.position.setY(this.position.getY() - steps);

                if (this.room.compareDoorPositions(this.position) && this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has left the room through the door.");
                    proceedGame = false;
                } else if (this.room.compareDoorPositions(this.position) && !this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has reached the door, but doesn't have the key.");
                    this.position.setY(1);
                } else if (this.position.getY() <= 0) {
                    System.out.println("Ouch, player hit upper wall.");
                    this.position.setY(1);
                }

                keyRadar();
                checkAndGetKeys();
                coinRadar();;
                checkAndGetCoins();

                break;
            }
            case Directions.RIGHT: {
                this.position.setX(this.position.getX() + steps);

                if (this.room.compareDoorPositions(this.position) && this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has left the room through the door.");
                    proceedGame = false;
                } else if (this.room.compareDoorPositions(this.position) && !this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has reached the door, but doesn't have the key.");
                    this.position.setX(this.room.getRoomWidth() - 1);
                } else if (this.position.getX() >= this.room.getRoomWidth()) {
                    System.out.println("Ouch, player hit right wall.");
                    this.position.setX(this.room.getRoomWidth() - 1);
                }

                keyRadar();
                checkAndGetKeys();
                coinRadar();;
                checkAndGetCoins();

                break;
            }
            case Directions.DOWN: {
                this.position.setY(this.position.getY() + steps);

                if (this.room.compareDoorPositions(this.position) && this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has left the room through the door.");
                    proceedGame = false;
                } else if (this.room.compareDoorPositions(this.position) && !this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has reached the door, but doesn't have the key.");
                    this.position.setY(this.room.getRoomLength() - 1);
                } else if (this.position.getY() >= this.room.getRoomLength()) {
                    System.out.println("Ouch, player hit lower wall.");
                    this.position.setY(this.room.getRoomLength() - 1);
                }

                keyRadar();
                checkAndGetKeys();
                coinRadar();;
                checkAndGetCoins();

                break;
            }
            case Directions.LEFT: {
                this.position.setX(this.position.getX() - steps);

                if (this.room.compareDoorPositions(this.position) && this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has left the room through the door.");
                    proceedGame = false;
                } else if (this.room.compareDoorPositions(this.position) && !this.items.contains(this.room.getDoorAtPosition(this.position).getKey())) {
                    System.out.println("Player has reached the door, but doesn't have the key.");
                    this.position.setX(1);
                } else if (this.position.getX() <= 0) {
                    System.out.println("Ouch, player hit left wall.");
                    this.position.setX(1);
                }

                keyRadar();
                checkAndGetKeys();
                coinRadar();;
                checkAndGetCoins();

                break;
            }
        }

        if (proceedGame) {
            System.out.println("Player is going " + this.currentDirection + ".");
            System.out.println("Player's current position is " + this.position.getPositionString() + ".");
        }

        return proceedGame;
    }

    private void checkAndGetKeys() {
        for (Key key : this.getRoom().getKeys()) {
            if (this.position.equals(key.getPosition())) {
                ArrayList<Coin> coins = this.getCoins();

                if (coins.size() < key.getPrice()) {
                    System.out.println("Player does not have enough coins to pay the key. Player has " + this.getCoins().size() + " coin(s), but the key has a price of " + key.getPrice() + " coin(s).");
                } else {
                    ArrayList<Coin> coinsToSpend = new ArrayList<>();

                    for (int i = 0; i < key.getPrice(); i++) {
                        coinsToSpend.add(this.getCoins().get(i));
                    }

                    for (Coin coin : coinsToSpend) {
                        key.getCoins().add(coin);
                        this.getItems().remove(coin);
                    }

                    this.addItem(key);
                }
            }
        }
    }

    private void keyRadar() {
        for (Position pos : this.getRoom().getKeys().stream().map(k -> k.getPosition()).toList()) {
            if (pos.distanceTo(this.position) <= Distance.KEY_AREA_DISTANCE.value) {
                System.out.println("Key nearby.");

                break;
            }
        }
    }

    private void checkAndGetCoins() {
        for (Coin coin : this.getRoom().getCoins()) {
            if (this.position.equals(coin.getPosition())) {
                this.addItem(coin);
            }
        }
    }

    private void coinRadar() {
        for (Position pos : this.getRoom().getCoins().stream().map(c -> c.getPosition()).toList()) {
            if (pos.distanceTo(this.position) <= Distance.COIN_AREA_DISTANCE.value) {
                System.out.println("Key nearby.");

                break;
            }
        }
    }

    public ArrayList<Key> getKeys() {
        ArrayList<Key> keys = new ArrayList<>();

        for (Item item : this.items) {
            if (item instanceof Key) {
                keys.add((Key) item);
            }
        }

        return keys;
    }

    public ArrayList<Coin> getCoins() {
        ArrayList<Coin> coins = new ArrayList<>();

        for (Item item : this.items) {
            if (item instanceof Coin) {
                coins.add((Coin) item);
            }
        }

        return coins;
    }

    public void printInventory() {
        System.out.println();
        System.out.println("=== Collected items ===");

        System.out.println(this.getCoins().size() + " coin(s)");
        System.out.println(this.getKeys().size() + " key(s)");

        System.out.println();
    }

    public void dropKey() {
        System.out.println();
        System.out.println("=== Your keys ===");

        for (Key key : this.getKeys()) {
            System.out.println(Integer.toString(this.getKeys().indexOf(key) + 1) + ": price " + Integer.toString(key.getPrice()) + ", " + key.getPosition().getPositionString());
        }

        System.out.println();
        System.out.println("Which key would you like to drop? Enter the number: ");

        try {
            Scanner scanner = new Scanner(System.in);
            int keyNumber = Integer.parseInt(System.console().readLine());
            Key key = this.getKeys().get(keyNumber - 1);

            this.items.addAll(key.getCoins());
            key.getCoins().clear();

            this.room.getItems().add(key);
            this.getItems().remove(key);
        }
        catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Directions getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Directions currentDirection) {
        this.currentDirection = currentDirection;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
