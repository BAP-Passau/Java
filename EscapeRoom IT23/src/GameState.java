import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameState {
    private static GameState instance;
    private static final String gameStateFileName = "gamestate.bin";
    private Room room;

    private GameState() {

    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    public void saveGameState() {
            try {
                FileOutputStream gameStateFileWriter = new FileOutputStream(gameStateFileName);
                ObjectOutputStream gameStateOutputStream = new ObjectOutputStream(gameStateFileWriter);
                gameStateOutputStream.writeObject(this.room);

                System.out.println("Gamestate saved to file " + gameStateFileName + ".");

                gameStateOutputStream.close();
                gameStateFileWriter.close();
            } catch (Exception e) {
                System.out.println("Error while saving gamestate.");
            }
    }

    public boolean loadGameState() {
        try {
            FileInputStream gameStateFileReader = new FileInputStream(gameStateFileName);
            ObjectInputStream gameStateInputStream = new ObjectInputStream(gameStateFileReader);
            this.room = (Room) gameStateInputStream.readObject();

            System.out.println("Gamestate loaded from file " + gameStateFileName + ".");
        } catch (Exception e) {
            System.out.println("Error while loading gamestate.");
            return false;
        }

        return true;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
