import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Ship {

	private int x;
	private int y;
	private int size;
	private boolean isHorizontal;
	private boolean isHit;

	// Constructor
	public static BiFunction<Integer, Integer, Ship> makeShip = Ship::new;
	private Ship(int x, int y) {
		setX.accept(this, x);
		setY.accept(this, y);
	}

	// Getters and Setters
	public static Function<Ship, Integer> getX = ship -> ship.x;
	public static Function<Ship, Integer> getY = ship -> ship.y;
	public static BiConsumer<Ship, Integer> setX = (ship, x) -> ship.x = x;
	public static BiConsumer<Ship, Integer> setY = (ship, y) -> ship.y = y;
}
