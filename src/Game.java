public class Game {
	public static void main(String[] args) {
		System.out.println("BATTLESHIP!");
		
		// Constructor test: must create position x with 3
		Ship ship = Ship.makeShip.get();
		System.out.println(ship);
		
		// Setter test: must write position x with 42
		Ship.setX.accept(ship, 42);
		Ship.setY.accept(ship, 10);
		Ship.setSize.accept(ship, 3);
		Ship.setHorizontal.accept(ship, false);
		System.out.println(ship);
	}
}
