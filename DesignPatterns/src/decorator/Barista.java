package decorator;

public class Barista {

	public static void main(String[] args) {
		
		
	    //IBaseCoffee bc = new Capuccino(); // could be capuccino , latte etc
		IBaseCoffee bc = new CookiesDecorator(new CreamDecorator(new Espresso()));
		
		CoffeePricer cp = new CoffeePricer(bc);
		
		System.out.println("Value for Final coffee : " + cp.getPrice());

	}

}
