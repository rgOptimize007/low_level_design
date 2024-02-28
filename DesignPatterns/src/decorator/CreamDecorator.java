package decorator;

public class CreamDecorator implements IBaseCoffee {
	
	IBaseCoffee bc;
	
	public CreamDecorator(IBaseCoffee bc){
		this.bc = bc;
	}

	@Override
	public double getCoffeePrice() {
		return bc.getCoffeePrice() + 5;
	}

}
