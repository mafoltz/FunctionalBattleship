package ships;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import util.Position;

public class Mine extends Ship {
	
	// Constructors
	public static Supplier<Mine> makeEmptyMine = Mine::new;
	public static BiFunction<Integer, Integer, Mine> makeMine = Mine::new;
	
	public Mine() {
		setPosition.accept(this, Position.makePosition.apply(0, 0));
	}
	
	private Mine(int x, int y) {
		setPositionX.accept(this, x);
		setPositionY.accept(this, y);
	}
}
