package design;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection
{
		final static private String url = "jdbc:mysql://localhost:3306/";
		final static private String driver = "com.mysql.jdbc.Driver";
		final static private String db = "banking"	+ "";  //wwwinmai_db 
		final static private String user = "root"; //wwwinmai_root
		final static private String pass = "root1"; 
		
		public static Connection javaConnection()
		{
			Connection conne=null;
			try {
				Class.forName(driver).newInstance();
				conne = (Connection) DriverManager.getConnection(url + db,user, pass);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conne;
			
		}
}
