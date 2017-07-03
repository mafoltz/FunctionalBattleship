import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Game {

	private static final int BOARD_SIZE = 15;
	private static final int NUM_MINES = 5;
	private static final int NUM_SUBMARINES = 4;
	private static final int NUM_SHIPS = 3;
	private static final int SIZE_OF_MINE = 1;
	private static final int SIZE_OF_SUBMARINE = 2;
	private static final int SIZE_OF_SHIP = 3;

	public static void main(String[] args) {
		// Function that create a board filled with zeros
		int[][] board = Game.initBoard.apply(makeBoard, BOARD_SIZE);

		// Create an empty array of ships
		Ship[] ships = Game.makeShips.apply(NUM_SUBMARINES + NUM_SHIPS + NUM_MINES);

		// Generate random ships
		ships = Game.generateShips.apply(ships, ships.length);

		// Fill board with ships
		board = Game.fillBoardWithShips.apply(board, ships);

		// TODO TESTS
		System.out.println("\n\nFINAL:\n");
		for (Ship ship : ships) {
			System.out.println(ship);
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static Function<Integer, int[][]> makeBoard = size -> new int[size][size];

	public static BiFunction<Function<Integer, int[][]>, Integer, int[][]> initBoard = (fn, size) -> {
		int[][] board = fn.apply(size);
		for (int i = 0; i < board.length; i++)
			Arrays.fill(board[i], 0);
		return board;
	};

	public static Function<Integer, Ship[]> makeShips = n -> new Ship[n];

	public static BiFunction<Ship[], Integer, Ship[]> generateShips = (ships, n) -> {
		if (n <= 0) {
			return ships;
		} else {
			// Generate the right number of mines, submarines and ships
			if (n <= NUM_MINES) {
				ships[n - 1] = Game.generateRandomShip.apply(SIZE_OF_MINE);
			} else if (n <= NUM_MINES + NUM_SUBMARINES) {
				ships[n - 1] = Game.generateRandomShip.apply(SIZE_OF_SUBMARINE);
			} else {
				ships[n - 1] = Game.generateRandomShip.apply(SIZE_OF_SHIP);
			}

			System.out.println("\nGENERATION:");
			System.out.println(ships[n - 1]);

			// Apply recursion to generate the others ships
			return Game.generateShips.apply(ships, n - 1);
		}
	};

	public static Function<Integer, Ship> generateRandomShip = size -> {
		Random random = new Random();

		return Ship.setX.apply(Ship.setY.apply(
				Ship.setSize.apply(Ship.setHorizontal.apply(Ship.makeShip.get(), random.nextBoolean()), size),
				random.nextInt(BOARD_SIZE - size + 1)), random.nextInt(BOARD_SIZE - size + 1));
	};

	public static BiFunction<int[][], Ship[], int[][]> fillBoardWithShips = (board, ships) -> {
		if (ships.length == 0) {
			return board;
		} else {

			if (Game.hasInvalidPosition.apply(board, ships[0])) {
				// Generate new ship to replace the invalids positions
				ships[0] = Game.generateRandomShip.apply(Ship.getSize.apply(ships[0]));
				return Game.fillBoardWithShips.apply(board, ships);

			} else {
				// Insert ship at board and apply recursion to insert the others ships
				return Game.fillBoardWithShips.apply(Game.insertShipAtBoard.apply(board, ships[0]),
						Arrays.copyOfRange(ships, 1, ships.length));
			}
		}
	};

	public static BiFunction<int[][], Ship, Boolean> hasInvalidPosition = (board, ship) -> {
		// Check if already there are some ship in the current position and neighborhood
		if (board[Ship.getY.apply(ship)][Ship.getX.apply(ship)] != 0) {
			return true;
		} else if (Ship.getX.apply(ship) != 0 && board[Ship.getY.apply(ship)][Ship.getX.apply(ship) - 1] != 0) {
			return true;
		} else if (Ship.getY.apply(ship) != 0 && board[Ship.getY.apply(ship) - 1][Ship.getX.apply(ship)] != 0) {
			return true;
		} else if (Ship.getX.apply(ship) != (board.length - 1)
				&& board[Ship.getY.apply(ship)][Ship.getX.apply(ship) + 1] != 0) {
			return true;
		} else if (Ship.getY.apply(ship) != (board.length - 1)
				&& board[Ship.getY.apply(ship) + 1][Ship.getX.apply(ship)] != 0) {
			return true;
		}

		if (Ship.getSize.apply(ship) == 1) {
			// Ship has a valid position
			return false;
		} else {
			// Decrease ship size to apply recursion
			Ship newShip = Ship.setX.apply(Ship.setY.apply(
					Ship.setSize.apply(Ship.setHorizontal.apply(Ship.makeShip.get(), Ship.isHorizontal.apply(ship)),
							Ship.getSize.apply(ship)),
					Ship.getY.apply(ship)), Ship.getX.apply(ship));
			newShip = Ship.setSize.apply(newShip, Ship.getSize.apply(newShip) - 1);

			// Set ship position to next position to be checked in the board
			if (Ship.isHorizontal.apply(newShip)) {
				return Game.hasInvalidPosition.apply(board, Ship.setX.apply(newShip, Ship.getX.apply(newShip) + 1));
			} else {
				return Game.hasInvalidPosition.apply(board, Ship.setY.apply(newShip, Ship.getY.apply(newShip) + 1));
			}
		}
	};

	public static BiFunction<int[][], Ship, int[][]> insertShipAtBoard = (board, ship) -> {
		board[Ship.getY.apply(ship)][Ship.getX.apply(ship)] = 1;

		if (Ship.getSize.apply(ship) == 1) {
			return board;
		} else {
			// Copy ship and decrease ship size to apply recursion
			Ship newShip = Ship.setX.apply(Ship.setY.apply(
					Ship.setSize.apply(Ship.setHorizontal.apply(Ship.makeShip.get(), Ship.isHorizontal.apply(ship)),
							Ship.getSize.apply(ship)),
					Ship.getY.apply(ship)), Ship.getX.apply(ship));
			newShip = Ship.setSize.apply(newShip, Ship.getSize.apply(newShip) - 1);

			// Set ship position to next position to be insert in the board
			if (Ship.isHorizontal.apply(newShip)) {
				newShip = Ship.setX.apply(newShip, Ship.getX.apply(newShip) + 1);
			} else {
				newShip = Ship.setY.apply(newShip, Ship.getY.apply(newShip) + 1);
			}

			return Game.insertShipAtBoard.apply(board, newShip);
		}
	};
}
