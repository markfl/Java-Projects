import java.sql.Connection;
import java.sql.DriverManager;

public class database {

	Connection con;
	
	public void connect() throws Exception {
		
		if (con != null) return;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String URL = "jdbc:mysql://localhost:3306/webshop";
		String connectionUrl = "jdbc:sqlserver://FLORESINSPIRON:<port>;databaseName=HubGroup;user=markf;";

		con = DriverManager.getConnection(URL, "mark", "");
		System.out.println("Database connected: " + con);
		
	}
}
