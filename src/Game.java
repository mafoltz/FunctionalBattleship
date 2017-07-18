import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Game {

	public static final int BOARD_SIZE = 15;
	public static final int NUM_MINES = 5;
	public static final int NUM_SUBMARINES = 4;
	public static final int NUM_SHIPS = 3;
	public static final int SIZE_OF_MINE = 1;
	public static final int SIZE_OF_SUBMARINE = 2;
	public static final int SIZE_OF_SHIP = 3;

	/*public static void main(String[] args) {
		// Create a board filled with zeros
		int[][] board = Game.initBoard.apply(makeBoard, BOARD_SIZE);

		// Create an empty array of ships
		Ship[] ships = Game.makeShips.apply(NUM_SUBMARINES + NUM_SHIPS + NUM_MINES);

		// Generate random ships
		ships = Game.generateShips.apply(ships, ships.length);

		// Fill board with ships
		board = Game.fillBoardWithShips.apply(board, ships);

		// TODO delete these tests
		print(ships);
		print(board);

		// Run the game
		Game.runGame.apply(board, ships);
	}*/

	public static Function<Integer, int[][]> makeBoard = size -> new int[size][size];

	public static BiFunction<Function<Integer, int[][]>, Integer, int[][]> initBoard = (fn,
			size) -> Game.fillBoardWithZeros.apply(fn.apply(size), size);

	public static BiFunction<int[][], Integer, int[][]> fillBoardWithZeros = (board, n) -> {
		if (n == 0) {
			return board;
		} else {
			Arrays.fill(board[n - 1], 0);
			return Game.fillBoardWithZeros.apply(board, n - 1);
		}
	};

	public static Function<Integer, Ship[]> makeShips = n -> new Ship[n];

	public static BiFunction<Ship[], Integer, Ship[]> generateShips = (ships, n) -> {
		if (n <= 0) {
			return ships;
		} else {
			// Generate the right number of mines, submarines and ships
			if (n <= NUM_SHIPS) {
				ships[n - 1] = Game.generateRandomShip.apply(SIZE_OF_SHIP);
			} else if (n <= NUM_SHIPS + NUM_SUBMARINES) {
				ships[n - 1] = Game.generateRandomShip.apply(SIZE_OF_SUBMARINE);
			} else {
				ships[n - 1] = Game.generateRandomShip.apply(SIZE_OF_MINE);
			}

			// Apply recursion to generate the others ships
			return Game.generateShips.apply(ships, n - 1);
		}
	};

	public static Function<Integer, Ship> generateRandomShip = size -> {
		Random random = new Random();

		return Ship.setX.apply(Ship.setY.apply(
				Ship.setSize.apply(Ship.setNumOfAliveCells
						.apply(Ship.setHorizontal.apply(Ship.makeShip.get(), random.nextBoolean()), size), size),
				random.nextInt(BOARD_SIZE - size + 1)), random.nextInt(BOARD_SIZE - size + 1));
	};

	public static Function<Ship, Ship> regenerateRandomShip = ship -> {
		Random random = new Random();

		return Ship.setX.apply(
				Ship.setY.apply(Ship.setHorizontal.apply(ship, random.nextBoolean()),
						random.nextInt(BOARD_SIZE - Ship.getSize.apply(ship) + 1)),
				random.nextInt(BOARD_SIZE - Ship.getSize.apply(ship) + 1));
	};

	public static BiFunction<int[][], Ship[], int[][]> fillBoardWithShips = (board, ships) -> {
		if (ships.length == 0) {
			return board;
		} else {
			if (Game.hasInvalidPosition.apply(board, ships[0])) {
				// Generate new ship to replace the invalids positions
				ships[0] = Game.regenerateRandomShip.apply(ships[0]);
				return Game.fillBoardWithShips.apply(board, ships);

			} else {
				// Insert ship at board and apply recursion to insert the others
				// ships
				return Game.fillBoardWithShips.apply(Game.insertShipAtBoard.apply(board, ships[0]),
						Arrays.copyOfRange(ships, 1, ships.length));
			}
		}
	};

	public static BiFunction<int[][], Ship, Boolean> hasInvalidPosition = (board, ship) -> {
		// Check if already there are some ship in the current position and
		// neighborhood
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
			Ship newShip = Ship.setX
					.apply(Ship.setY.apply(
							Ship.setSize.apply(Ship.setNumOfAliveCells.apply(
									Ship.setHorizontal.apply(Ship.makeShip.get(), Ship.isHorizontal.apply(ship)),
									Ship.getNumOfAliveCells.apply(ship)), Ship.getSize.apply(ship)),
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
			Ship newShip = Ship.setX
					.apply(Ship.setY.apply(
							Ship.setSize.apply(Ship.setNumOfAliveCells.apply(
									Ship.setHorizontal.apply(Ship.makeShip.get(), Ship.isHorizontal.apply(ship)),
									Ship.getNumOfAliveCells.apply(ship)), Ship.getSize.apply(ship)),
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

	public static BiFunction<int[][], Ship[], int[][]> runGame = (board, ships) -> {
		if (Game.hasGameFinished.apply(board)) {
			System.out.println("CONGRATULATIONS! YOU WON THE GAME :)");
			return board;
		} else {
			// TODO insert interface here
			int y = read("row");
			int x = read("column");

			if (Game.hasShipAtPosition.apply(Game.getValueForPosition.apply(board, x).applyAsInt(y))) {
				// A ship was hit, then the board and ship must be updated
				Game.updateShipsAtPosition.apply(x, y).apply(ships.length).apply(ships);
				Game.updateBoardAtPosition.apply(x, y).apply(board);

				// print(ships);
				System.out.println("Position had ship!\n");
				print(board);
			} else {
				System.out.println("Water\n");
			}

			return Game.runGame.apply(board, ships);
		}
	};

	public static Function<int[][], Boolean> hasGameFinished = board -> {
		// TODO delete this print
		int x = Game.convertMatrixToList.apply(board, board.length).stream().mapToInt(n -> n).sum();
		//System.out.println("Ships = " + x + "\n");

		// Reduce a list to the sum of its elements
		if (x == 0) {
			return true;
		} else {
			return false;
		}
	};

	public static BiFunction<int[][], Integer, List<Integer>> convertMatrixToList = (matrix, n) -> {
		if (n == 1) {
			// End the recursion at the last row of the matrix
			return Arrays.stream(matrix[n - 1]).boxed().collect(Collectors.toList());
		} else {
			// Map the int's to Integer's and add the numbers form other lists recursively
			List<Integer> list = Arrays.stream(matrix[n - 1]).boxed().collect(Collectors.toList());
			list.addAll(Game.convertMatrixToList.apply(matrix, n - 1));
			return list;
		}
	};

	public static Function<Integer, Boolean> hasShipAtPosition = n -> n == 1;

	public static BiFunction<int[][], Integer, IntUnaryOperator> getValueForPosition = (board, x) -> y -> board[y][x];

	public static BiFunction<Integer, Integer, UnaryOperator<int[][]>> updateBoardAtPosition = (x, y) -> board -> {
		board[y][x] = 0;
		return board;
	};

	public static BiFunction<Integer, Integer, Function<Integer, UnaryOperator<Ship[]>>> updateShipsAtPosition = (x,
			y) -> n -> ships -> {
				if (n <= 0) {
					return ships;
				} else {
					ships[n - 1] = Game.tryUpdateShipAtPosition.apply(x, y).apply(ships[n - 1]);
					return Game.updateShipsAtPosition.apply(x, y).apply(n - 1).apply(ships);
				}
			};

	public static BiFunction<Integer, Integer, UnaryOperator<Ship>> tryUpdateShipAtPosition = (x, y) -> ship -> {
		// Ship begin in position
		if (x == Ship.getX.apply(ship) && y == Ship.getY.apply(ship)) {
			Ship.setNumOfAliveCells.apply(ship, Ship.getNumOfAliveCells.apply(ship) - 1);
		}

		// Ship begin in one position above
		else if (Ship.getSize.apply(ship) >= 2 && !Ship.isHorizontal.apply(ship) && x == Ship.getX.apply(ship)
				&& y == Ship.getY.apply(ship) + 1) {
			Ship.setNumOfAliveCells.apply(ship, Ship.getNumOfAliveCells.apply(ship) - 1);
		}

		// Ship begin in one position to the left
		else if (Ship.getSize.apply(ship) >= 2 && Ship.isHorizontal.apply(ship) && x == Ship.getX.apply(ship) + 1
				&& y == Ship.getY.apply(ship)) {
			Ship.setNumOfAliveCells.apply(ship, Ship.getNumOfAliveCells.apply(ship) - 1);
		}

		// Ship begin in two positions above
		else if (Ship.getSize.apply(ship) == 3 && !Ship.isHorizontal.apply(ship) && x == Ship.getX.apply(ship)
				&& y == Ship.getY.apply(ship) + 2) {
			Ship.setNumOfAliveCells.apply(ship, Ship.getNumOfAliveCells.apply(ship) - 1);
		}

		// Ship begin in two positions to the left
		else if (Ship.getSize.apply(ship) == 3 && Ship.isHorizontal.apply(ship) && x == Ship.getX.apply(ship) + 2
				&& y == Ship.getY.apply(ship)) {
			Ship.setNumOfAliveCells.apply(ship, Ship.getNumOfAliveCells.apply(ship) - 1);
		}

		return ship;
	};

	// TODO delete these methods
	public static void print(Ship[] ships) {
		for (Ship ship : ships) {
			System.out.println(ship);
		}
	}

	public static void print(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static Scanner scanner;

	public static int read(String text) {
		System.out.print("Enter " + text + ": ");
		scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
}