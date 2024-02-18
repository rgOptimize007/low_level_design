package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ObjectCreator {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		/*Eager singleton example broken with Reflection */
		
		EagerSingleton es = EagerSingleton.getInstance();
		System.out.println(es);
		
		Constructor c = es.getClass().getDeclaredConstructor(new Class[0]);
		c.setAccessible(true);
		EagerSingleton es2 = (EagerSingleton) c.newInstance();
		System.out.println(es2);
		
		/*Lazy single threaded example broken in multi threaded*/
		
		Executor ex = new ThreadPoolExecutor(2, 2, 10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		ex.execute(() -> {
			LazySingletonSingleThreaded ls = LazySingletonSingleThreaded.getInstance();
		    System.out.println(ls);
		});
		ex.execute(() -> {
			LazySingletonSingleThreaded ls = LazySingletonSingleThreaded.getInstance();
		    System.out.println(ls);
		});
		ex.execute(() -> {
			LazySingletonSingleThreaded ls = LazySingletonSingleThreaded.getInstance();
		    System.out.println(ls);
		});
		ex.execute(() -> {
			LazySingletonSingleThreaded ls = LazySingletonSingleThreaded.getInstance();
		    System.out.println(ls);
		});
		
		/*Lazy singleton Thread safe multi threaded*/
		
/*Lazy single threaded example broken in multi threaded*/
		
		Executor ex2 = new ThreadPoolExecutor(2, 2, 10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
		ex.execute(() -> {
			LazySingletonMultiThreaded ls = LazySingletonMultiThreaded.getInstance();
		    System.out.println(ls);
		});
		ex2.execute(() -> {
			LazySingletonMultiThreaded ls = LazySingletonMultiThreaded.getInstance();
		    System.out.println(ls);
		});
		ex2.execute(() -> {
			LazySingletonMultiThreaded ls = LazySingletonMultiThreaded.getInstance();
		    System.out.println(ls);
		});
		ex2.execute(() -> {
			LazySingletonMultiThreaded ls = LazySingletonMultiThreaded.getInstance();
		    System.out.println(ls);
		});
		
		//Thread safe + (de)serialzation proof + reflection proof
		SingletonEnum enumSingleton = SingletonEnum.INSTANCE;
		Connection connection = enumSingleton.getConnection();
	}

}
