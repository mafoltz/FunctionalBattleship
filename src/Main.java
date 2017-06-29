public class Main {
	public static void main(String[] args) {
		System.out.println("BATTLESHIP!");
		
		// Constructor test: must create position x with 3
		Ship ship = Ship.makeShip.apply(3, 4);
		System.out.println("x = " + Ship.getX.apply(ship));
		
		// Setter test: must write position x with 42
		Ship.setX.accept(ship, 42);
		System.out.println("x = " + Ship.getX.apply(ship));
	}
}
