import java.sql.DriverManager;
import java.sql.Connection;
public class databaseConn {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Class.forName("org.postgresql.Driver");
		Connection c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/AdvanceJavaProject","postgres","postgres");
		System.out.print("Connection Successfully Done");
		c.close();
	}

}
