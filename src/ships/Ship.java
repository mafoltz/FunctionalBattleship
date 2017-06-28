package ships;

import java.util.function.BiConsumer;
import java.util.function.Function;

import util.*;

public abstract class Ship {
	private Position cell;
	private boolean isHit;
	
	// Getters and Setters
	public Function<Ship, Position> getPosition = Ship::getPosition;
	public BiConsumer<Ship, Position> setPosition = Ship::setPosition;
	public BiConsumer<Ship, Integer> setPositionX = Ship::setPositionX;
	public BiConsumer<Ship, Integer> setPositionY = Ship::setPositionY;
	
	private Position getPosition() {
		return cell;
	}
	
	private void setPosition(Position position) {
		this.cell = position;
	}
	
	private void setPositionX(int x) {
		cell.setX.accept(cell, x);
	}
	
	private void setPositionY(int y) {
		cell.setX.accept(cell, y);
	}
}
