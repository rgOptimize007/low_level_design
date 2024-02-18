package singleton;

import java.io.Serializable;

public class LazySingletonMultiThreaded implements Serializable {

private static LazySingletonMultiThreaded INSTANCE;
	
	private LazySingletonMultiThreaded(){}
	
	public static LazySingletonMultiThreaded getInstance(){
		if(INSTANCE == null) {
			synchronized(LazySingletonMultiThreaded.class){
		      if(INSTANCE == null){
		    	  System.out.println("Inside instance creation..." + Thread.currentThread().getName());
				  INSTANCE = new LazySingletonMultiThreaded();
		      }
		   }
		}
		return INSTANCE;
	}
	
	//Following method is implemented to protect this class from breaking singleton pattern during (De)serialization process. 
	protected Object readResolve(){
		return INSTANCE;
	}
}
