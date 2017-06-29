import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Ship {

	private int x;
	private int y;
	private int spaces;
	private boolean isHorizontal;
	private boolean isHit;

	// Constructor
	public static BiFunction<Integer, Integer, Ship> makeShip = Ship::new;
	private Ship(int x, int y) {
		setX.accept(this, x);
		setY.accept(this, y);
	}

	// Getters and Setters
	public static Function<Ship, Integer> getX = Ship::getX;
	private int getX() {
		return x;
	}
	
	public static Function<Ship, Integer> getY = Ship::getY;
	private int getY() {
		return y;
	}
	
	public static BiConsumer<Ship, Integer> setX = Ship::setX;
	private void setX(int x) {
		this.x = x;
	}
	
	public static BiConsumer<Ship, Integer> setY = Ship::setY;
	private void setY(int y) {
		this.y = y;
	}
}
