package util;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class Position {
	private int x;
	private int y;
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private int getX() {
		return x;
	}
	
	private int getY() {
		return y;
	}
	
	private void setX(int x) {
		this.x = x;
	}
	
	private void setY(int y) {
		this.y = y;
	}
	
	public Function<Position, Integer> getX = Position::getX;
	public Function<Position, Integer> getY = Position::getY;
	public BiConsumer<Position, Integer> setX = Position::setX;
	public BiConsumer<Position, Integer> setY = Position::setY;
}
