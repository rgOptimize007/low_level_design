package decorator;

public class CoffeePricer {

	IBaseCoffee bc;
	
	public CoffeePricer(IBaseCoffee bc2) {
		this.bc = bc2;
	}
	
	public double getPrice() {
		return bc.getCoffeePrice();
	}

}
