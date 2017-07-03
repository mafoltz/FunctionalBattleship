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
		Game.fillBoardWithShips.apply(board, ships);

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
			Random random = new Random();

			if (n <= NUM_SHIPS) {
				ships[n - 1] = Ship.setSize.apply(Ship.makeShip.get(), SIZE_OF_SHIP);
			} else if (n <= NUM_SHIPS + NUM_SUBMARINES) {
				ships[n - 1] = Ship.setSize.apply(Ship.makeShip.get(), SIZE_OF_SUBMARINE);
			} else {
				ships[n - 1] = Ship.setSize.apply(Ship.makeShip.get(), SIZE_OF_MINE);
			}

			ships[n - 1] = Ship.setX.apply(
					Ship.setY.apply(Ship.setHorizontal.apply(ships[n - 1], random.nextBoolean()),
							random.nextInt(BOARD_SIZE - Ship.getSize.apply(ships[n - 1]) + 1)),
					random.nextInt(BOARD_SIZE - Ship.getSize.apply(ships[n - 1]) + 1));

			return Game.generateShips.apply(ships, n - 1);
		}
	};

	public static BiFunction<int[][], Ship[], int[][]> fillBoardWithShips = (board, ships) -> {
		if (ships.length == 0) {
			return board;
		} else {
			return Game.fillBoardWithShips.apply(Game.insertShip.apply(board, ships[0]),
					Arrays.copyOfRange(ships, 1, ships.length));
		}
	};

	public static BiFunction<int[][], Ship, int[][]> insertShip = (board, ship) -> {
		board[Ship.getX.apply(ship)][Ship.getY.apply(ship)] = 1;

		if (Ship.getSize.apply(ship) == 1) {
			return board;
		} else {
			// Decrease ship size to apply recursion
			Ship newShip = ship;
			newShip = Ship.setSize.apply(newShip, Ship.getSize.apply(newShip) - 1);

			// Set ship position to next position to be insert in the board
			if (Ship.isHorizontal.apply(newShip)) {
				newShip = Ship.setX.apply(newShip, Ship.getX.apply(newShip) + 1);
			} else {
				newShip = Ship.setY.apply(newShip, Ship.getY.apply(newShip) + 1);
			}

			return Game.insertShip.apply(board, newShip);
		}
	};
}
