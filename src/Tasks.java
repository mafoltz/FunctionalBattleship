import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class Tasks {
	public void tasks() {
		/* 1 - IMUTABLE FUNCTIONS (CREATE NEW ARRAYS) */
		
		/* 2 - LAMBDA FUNCTIONS */
		Ship ship = Ship.makeShip.get();
		System.out.println(ship);
		Ship.setX.accept(ship, 42);
		Ship.setY.accept(ship, 10);
		Ship.setSize.accept(ship, 3);
		Ship.setHorizontal.accept(ship, false);
		System.out.println(ship);
		
		/* 3 - CURRYING */
		IntFunction<IntUnaryOperator> curriedAdd = a -> b -> a + b;
		// Curried version lets you perform partial application:
	    IntUnaryOperator adder5 = curriedAdd.apply(5);
	    System.out.println(adder5.applyAsInt(4));
	    System.out.println(adder5.applyAsInt(6));
	    
	    /* 4 - PATTERN MATCH */
	    // It needs to be implemented in Java
	    // https://kerflyn.wordpress.com/2012/05/09/towards-pattern-matching-in-java/
	    // https://stackoverflow.com/questions/11219858/java-8-pattern-matching
	    
	    /* 5 - HIGHER ORDER FUNCTIONS */
	    // Functions that receive functions as argument or return others functions
	    
	    /* 6 - MAP, REDUCE, FOLDR, FOLDL */
	    // foldr (+) 0 [1,2,3] -> 0+3+2+1 -> 6
	    //  map foo1 [2, 3, 6] -> [foo1(2), foo1(3), foo1(6)] -> [4, 6, 36]
	    //Collection<E> in = ...
	    	//Object[] mapped = in.stream().map(e -> doMap(e)).toArray();
	    	// or
	    	//List<E> mapped = in.stream().map(e -> doMap(e)).collect(Collectors.toList());
	    
	    /* 7 - FIRST CLASS FUNCTIONS */
	    // Functions can be used as parameters or returned as a subroutine result
	    
	    /* 8 - RECURSION FOR LIST ITERATION */
	}
}
