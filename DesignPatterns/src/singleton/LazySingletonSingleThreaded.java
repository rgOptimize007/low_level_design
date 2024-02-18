package singleton;

public class LazySingletonSingleThreaded {
	
	private static LazySingletonSingleThreaded INSTANCE;
	
	private LazySingletonSingleThreaded(){}
	
	public static LazySingletonSingleThreaded getInstance(){
		if(INSTANCE == null) {
			System.out.println("Inside instance creation..." + Thread.currentThread().getName());
			INSTANCE = new LazySingletonSingleThreaded();
			}
		return INSTANCE;
	}

}
