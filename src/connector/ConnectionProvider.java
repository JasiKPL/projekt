package connector;
import java.sql.*;
import static connector.Provider.*;

public class ConnectionProvider {
	static Connection con=null;
	static{
		try{
			Class.forName(DRIVER);
			con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
			}catch(Exception e){
				System.out.println("Error while connecting to database: " + e.toString());
			}
	}
	public static Connection getCon(){
		return con;
	}
}
