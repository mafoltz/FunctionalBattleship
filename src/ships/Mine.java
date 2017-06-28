package ships;

import util.Position;

public class Mine extends Ship {
	
	public Mine()
	{
		setPosition.accept(this, new Position(0, 0));
	}
	
	public Mine(int x, int y)
	{
		setPositionX.accept(this, x);
		setPositionY.accept(this, y);
	}
}
