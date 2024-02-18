package singleton;

import java.sql.Connection;

public enum SingletonEnum {
	
	INSTANCE;
	
	Connection c;
	
	public Connection getConnection(){
		return c;
	}

}
