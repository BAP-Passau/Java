import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        GameState gameState = GameState.getInstance();
        Room room;
        Player player;

        if (gameState.loadGameState()) {
            room = gameState.getRoom();
            player = gameState.getRoom().getPlayer();
        } else {
            room = new Room();
            player = new Player();
            room.placePlayer(player);
        }

        System.out.println("The room has a length of " + room.getRoomLength() + " and a width of " + room.getRoomWidth() + ".");

        gameState.setRoom(room);

        String command = "";
        int steps = 0;

        boolean play = true;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Type \"r\" to turn player right, \"l\" to turn player left, \"i\" to print list of collected items, \"d\" to drop a key, just hit ENTER to keep current direction, \"q\" to quit the game: ");

            try {
                command = scanner.nextLine();
            }
            catch (Exception e) {
                System.out.println("Something went wrong.");
            }

            switch (command.toLowerCase()) {
                case "r" -> player.turnRight();
                case "l" -> player.turnLeft();
                case "q" -> play = false;
                case "p" -> room.print();
                case "i" -> player.printInventory();
                case "d" -> player.dropKey();
                case "" -> System.out.println();
                default -> System.out.println("The player does only understand \"r\", \"l\", empty command and \"q\".");
            }

            if (!play) {
                gameState.saveGameState();
                break;
            }

            System.out.println("How many steps should the player go? ");

            try {
                steps = Integer.parseInt(scanner.nextLine());
            }
            catch (Exception e) {
                System.out.println("Invalid input.");
                steps = 0;
            }

            if (!player.move(steps)) {
                break;
            }
        }
    }
}
