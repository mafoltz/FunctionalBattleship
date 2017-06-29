import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Game {
	private static final int BOARD_SIZE = 15;
	
	public static void main(String[] args) {
		int[][] board = Game.initBoard.apply(createBoard, BOARD_SIZE);
	}
	
	public static Function<Integer,int[][]> createBoard =
			size -> new int[size][size];

	public static BiFunction<Function<Integer,int[][]>, Integer, int[][]> initBoard =
			(fn, size) -> {
				int[][] board = fn.apply(size);
				Arrays.fill(board, 0);
				return board;
			};
}
