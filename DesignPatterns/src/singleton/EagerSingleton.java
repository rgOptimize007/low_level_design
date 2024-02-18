package singleton;

public class EagerSingleton {
	
	private static EagerSingleton INSTANCE = new EagerSingleton();
	
	
	private EagerSingleton(){
	}
	
	public static EagerSingleton getInstance(){
		return INSTANCE;
	}

}
