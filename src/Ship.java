import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Ship {

	private int x;
	private int y;
	private int size;
	private boolean horizontal;

	// Constructor
	public static Supplier<Ship> makeShip = Ship::new;
		
	// Getters and Setters
	public static Function<Ship, Integer> getX = ship -> ship.x;
	public static BiConsumer<Ship, Integer> setX = (ship, x) -> ship.x = x;
	public static Function<Ship, Integer> getY = ship -> ship.y;
	public static BiConsumer<Ship, Integer> setY = (ship, y) -> ship.y = y;
	public static Function<Ship, Integer> getSize = ship -> ship.size;
	public static BiConsumer<Ship, Integer> setSize = (ship, size) -> ship.size = size;
	public static Function<Ship, Boolean> isHorizontal = ship -> ship.horizontal;
	public static BiConsumer<Ship, Boolean> setHorizontal = (ship, horizontal) -> ship.horizontal = horizontal;

	@Override
	public String toString() {
		String string = "Ship:\n";
		string = string.concat("x = " + this.x + "\n");
		string = string.concat("y = " + this.y + "\n");
		string = string.concat("size = " + this.size + "\n");
		if(this.horizontal)
			string = string.concat("isHorizontal = true\n");
		else
			string = string.concat("isHorizontal = true\n");
		return string;
	}
}