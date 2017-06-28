package ships;

import util.Position;

public class Mine extends Ship {
	
	public Mine()
	{
		setPosition.accept(this, new Position(0, 0));
	}
	
	public Mine(int x, int y)
	{
		setPosition.accept(this, new Position(x, y));
	}
}
