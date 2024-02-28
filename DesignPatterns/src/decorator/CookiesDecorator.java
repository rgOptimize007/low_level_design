package decorator;

public class CookiesDecorator implements IBaseCoffee {
	
	IBaseCoffee bc;
	
	public CookiesDecorator(IBaseCoffee bc){
		this.bc = bc;
	}

	@Override
	public double getCoffeePrice() {
		return bc.getCoffeePrice() + 10;
	}

}
