import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Game {
	private static final int BOARD_SIZE = 15;
	private static final int NUM_SUBMARINES = 4;
	private static final int NUM_SHIPS = 3;
	private static final int NUM_MINES = 5;

	public static void main(String[] args) {
		// Function that create a board filled with zeros
		int[][] board = Game.initBoard.apply(makeBoard, BOARD_SIZE);

		// Create an empty array of ships
		Ship[] ships = Game.makeShips.apply(NUM_SUBMARINES + NUM_SHIPS + NUM_MINES);

		// TODO Generate random ships
		
		// Fill board with ships
		Game.fillBoardWithShips.apply(board, ships);
	}

	public static Function<Integer, int[][]> makeBoard = size -> new int[size][size];
	public static Function<Integer, Ship[]> makeShips = n -> new Ship[n];

	public static BiFunction<Function<Integer, int[][]>, Integer, int[][]> initBoard = (fn, size) -> {
		int[][] board = fn.apply(size);
		for (int i = 0; i < board.length; i++)
			Arrays.fill(board[i], 0);
		return board;
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
		return board;
	};
}
