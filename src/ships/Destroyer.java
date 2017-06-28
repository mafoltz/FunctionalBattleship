package ships;

import util.*;

public class Destroyer extends Mine {
	
	private Position cell2;
	private char orientation;
	
	public Destroyer()
	{
		super();
		setOrientation('h');
		setCell2(0,1);
	}
	
	public void setOrientation(char orientation) {
		if(orientation == 'v')
			this.orientation = orientation;
		else
			this.orientation = 'h';
	}
	
	public void setCell2(Position cell2) {
		// fazer verificação se é valida a posição da cell2
		this.cell2 = cell2;
	}
	
	public void setCell2(int x, int y) {
		// fazer verificação se é valida a posição da cell2
		this.cell2.setX.accept(cell2, x);;
		this.cell2.setY.accept(cell2, y);
	}

}
