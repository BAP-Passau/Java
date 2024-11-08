import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private Player player;
    private Integer roomLength;
    private Integer roomWidth;
    private ArrayList<Item> items;
    private ArrayList<Door> doors;
    private ArrayList<Position> validDoorPositions = new ArrayList<>();
    private ArrayList<Position> validRoomPositions = new ArrayList<>();

    public Room() {
        Random r = new Random();

        this.roomLength = r.nextInt(10, 100);
        this.roomWidth = r.nextInt(10, 100);
        this.player = new Player();
        this.items = new ArrayList<>();
        this.doors = new ArrayList<>();

        for (int x = 1; x < this.roomWidth; x++) {
            for (int y = 1; y < this.roomLength; y++) {
                this.validRoomPositions.add(new Position(x, y));
            }
        }

        for (int x = 0; x < this.roomWidth; x++) {
            validDoorPositions.add(new Position(x, 0));
            validDoorPositions.add(new Position(x, this.roomLength));
        }

        for (int y = 0; y < this.roomLength; y++) {
            validDoorPositions.add(new Position(0, y));
            validDoorPositions.add(new Position(this.roomWidth, y));
        }

        ArrayList<Key> keys = new ArrayList<>();
        ArrayList<Coin> coins = new ArrayList<>();
        Position doorPosition;

        for (int i = 0; i < 2; i++) {
            Position keyPosition = this.validRoomPositions.get(r.nextInt(this.validRoomPositions.size()));
            keys.add(new Key(keyPosition));
            validRoomPositions.remove(keyPosition);
        }

        for (int i = 0; i < 5; i++) {
            Position coinPosition = this.validRoomPositions.get(r.nextInt(this.validRoomPositions.size()));
            coins.add(new Coin(coinPosition));
            validRoomPositions.remove(coinPosition);
        }

        for (Key key : keys) {
            doorPosition = this.validDoorPositions.get(r.nextInt(this.validDoorPositions.size()));
            this.doors.add(new Door(key, doorPosition));
            this.items.add(key);
            this.validDoorPositions.remove(doorPosition);
        }

        for (Coin coin : coins) {
            this.items.add(coin);
        }
    }

    public void placePlayer(Player player) {
        player.putInPlace(this, this.roomWidth, this.roomLength);

        this.player = player;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void print() {
        for (int y = 0; y <= this.roomLength; y++) {
            for (int x = 0; x <= this.roomWidth; x++) {
                Position paintPos = new Position(x, y);

                if (y == 0) {
                    if (!this.getDoors().isEmpty() && this.compareDoorPositions(paintPos)) {
                        System.out.print("D");
                    } else {
                        System.out.print("-");
                    }

                    if (x == this.roomWidth) {
                        System.out.println();
                    }
                } else if (y == this.roomLength) {
                    if (!this.getDoors().isEmpty() && this.compareDoorPositions(paintPos)) {
                        System.out.print("D");
                    } else {
                        System.out.print("-");
                    }

                    if (x == this.roomWidth) {
                        System.out.println();
                    }
                } else if (x == 0) {
                    if (!this.getDoors().isEmpty() && this.compareDoorPositions(paintPos)) {
                        System.out.print("D");
                    } else {
                        System.out.print("|");
                    }
                } else if (x == this.roomWidth) {
                    if (!this.getDoors().isEmpty() && this.compareDoorPositions(paintPos)) {
                        System.out.println("D");
                    } else {
                        System.out.println("|");
                    }
                } else if (!this.getKeys().isEmpty() && this.compareKeyPositions(paintPos)) {
                    for (Key key : this.getKeys()) {
                        if (paintPos.equals(key.getPosition())) {
                            System.out.print("K");
                        }
                    }
                } else if (!this.getCoins().isEmpty() && this.compareCoinPositions(paintPos)) {
                    for (Coin coin : this.getCoins()) {
                        if (paintPos.equals(coin.getPosition())) {
                            System.out.print("€");
                        }
                    }
                } else if (x == this.player.getPosition().getX() && y == this.player.getPosition().getY()) {
                    System.out.print("P");
                } else {
                    boolean keyNearby = false;
                    boolean coinNearby = false;

                    if (!this.getKeys().isEmpty()) {
                        for (Key key : this.getKeys()) {
                            ArrayList<Position> keyArea = key.keyArea();

                            if (!this.player.getItems().contains(key)) {
                                for (Position pos : keyArea) {
                                    if (x == pos.getX() && y == pos.getY()) {
                                        keyNearby = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (!this.getCoins().isEmpty()) {
                        for (Coin coin : this.getCoins()) {
                            ArrayList<Position> coinArea = coin.coinArea();

                            if (!this.player.getItems().contains(coin)) {
                                for (Position pos : coinArea) {
                                    if (x == pos.getX() && y == pos.getY()) {
                                        coinNearby = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (keyNearby || coinNearby) {
                        System.out.print(".");
                    } else {
                        System.out.print(" ");
                    }
                }
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

    protected List<Position> getKeyPositions() {
        return this.getKeys().stream().map(Item::getPosition).toList();
    }

    protected boolean compareKeyPositions(Position position) {
        boolean result = false;

        for (Position p : this.getKeyPositions()) {
            if (p.equals(position)) {
                result = true;
                break;
            }
        }

        return result;
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

    protected List<Position> getCoinPositions() {
        return this.getCoins().stream().map(Item::getPosition).toList();
    }

    protected boolean compareCoinPositions(Position position) {
        boolean result = false;

        for (Position p : this.getCoinPositions()) {
            if (p.equals(position)) {
                result = true;
                break;
            }
        }

        return result;
    }

    protected List<Position> getDoorPositions() {
        return this.getDoors().stream().map(Door::getPosition).toList();
    }

    protected boolean compareDoorPositions(Position position) {
        boolean result = false;

        for (Position p : this.getDoorPositions()) {
            if (p.equals(position)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public Door getDoorAtPosition(Position position) {
        Door door = null;

        for (Door d : this.getDoors()) {
            if (d.getPosition().equals(position)) {
                door = d;
            }
        }

        return door;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayers(Player player) {
        this.player = player;
    }

    public Integer getRoomLength() {
        return roomLength;
    }

    public void setRoomLength(Integer roomLength) {
        this.roomLength = roomLength;
    }

    public Integer getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(Integer roomWidth) {
        this.roomWidth = roomWidth;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public void setDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }

    public ArrayList<Position> getValidDoorPositions() {
        return validDoorPositions;
    }

    public void setValidDoorPositions(ArrayList<Position> validDoorPositions) {
        this.validDoorPositions = validDoorPositions;
    }

    public ArrayList<Position> getValidRoomPositions() {
        return validRoomPositions;
    }

    public void setValidRoomPositions(ArrayList<Position> validRoomPositions) {
        this.validRoomPositions = validRoomPositions;
    }
}
