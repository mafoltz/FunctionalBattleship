import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Ship {

	// Properties
	private int x;
	private int y;
	private int size;
	private int aliveCells;
	private boolean horizontal;

	// Constructors
	public static Supplier<Ship> makeShip = Ship::new;

	// Getters and Setters
	public static Function<Ship, Integer> getX = ship -> ship.x;
	public static BiFunction<Ship, Integer, Ship> setX = (ship, x) -> {
		ship.x = x;
		return ship;
	};

	public static Function<Ship, Integer> getY = ship -> ship.y;
	public static BiFunction<Ship, Integer, Ship> setY = (ship, y) -> {
		ship.y = y;
		return ship;
	};

	public static Function<Ship, Integer> getSize = ship -> ship.size;
	public static BiFunction<Ship, Integer, Ship> setSize = (ship, size) -> {
		ship.size = size;
		return ship;
	};

	public static Function<Ship, Integer> getNumOfAliveCells = ship -> ship.aliveCells;
	public static BiFunction<Ship, Integer, Ship> setNumOfAliveCells = (ship, aliveCells) -> {
		ship.aliveCells = aliveCells;
		return ship;
	};

	public static Function<Ship, Boolean> isHorizontal = ship -> ship.horizontal;
	public static BiFunction<Ship, Boolean, Ship> setHorizontal = (ship, horizontal) -> {
		ship.horizontal = horizontal;
		return ship;
	};

	@Override
	public String toString() {
		String string = "Ship:\n";
		string = string.concat("x = " + this.x + "\n");
		string = string.concat("y = " + this.y + "\n");
		string = string.concat("size = " + this.size + "\n");
		string = string.concat("alive cells = " + this.aliveCells + "\n");
		if (this.horizontal)
			string = string.concat("isHorizontal = true\n");
		else
			string = string.concat("isHorizontal = false\n");
		return string;
	}
}