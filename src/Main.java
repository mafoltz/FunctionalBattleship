import ships.Mine;

public class Main {
	public static void main(String[] args) {
		System.out.println("BATTLESHIP!");
		
		// Constructor test: must create position with 0
		Mine mine = Mine.makeEmptyMine.get();
		System.out.println("x = " + mine.getPosition.apply(mine).getX.apply(mine.getPosition.apply(mine)));
		
		// Setter test: must write position with 42
		mine.setPositionX.accept(mine, 42);
		System.out.println("x = " + mine.getPosition.apply(mine).getX.apply(mine.getPosition.apply(mine)));
	}
}
