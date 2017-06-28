package ships;

import java.util.function.BiConsumer;
import java.util.function.Function;

import util.*;

public abstract class Ship {
	private Position cell;
	private boolean isHit;
	
	private Position getPosition() {
		return cell;
	}
	
	private void setPosition(Position position) {
		this.cell = position;
	}
	
	private void setPosition(int x, int y) {
		cell.setX(x);
		cell.setY(y);
	}
	
	public Function<Ship, Position> getPosition = Ship::getPosition;
	public BiConsumer<Ship, Position> setPosition = Ship::setPosition;
}
