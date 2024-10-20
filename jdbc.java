import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
public class Jdbc {
	//method to connect to the mysql database
	public Connection connect() {
		String url= "jdbc:mysql://localhost:3306/library_jdbc";
		String user="root";
		String password="Ayesha09@5";
		
		try {
			//establish the connection
			 Connection con	=DriverManager.getConnection(url,user,password);
			 System.out.println("connection successful!");
			 return con;
		}catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }
}
