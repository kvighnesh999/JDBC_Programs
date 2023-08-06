import java.sql.*;  
import java.io.*;  

public class InsertImage { 
	public static void main(String[] args) { 
		try{  
			Class.forName("org.postgresql.Driver");  
			Connection con=DriverManager.getConnection(  
					"jdbc:postgresql://localhost:5432/AdvanceJavaProject","postgres","postgres");              
			PreparedStatement ps=con.prepareStatement("insert into imagetable values(?,?)");  
			ps.setString(1,"sonoo");   
			FileInputStream file=new FileInputStream("img.jpg");  
			ps.setBinaryStream(2,file,file.available());  
			int i=ps.executeUpdate();  
			System.out.println(i+" records affected");  
          
			con.close();  
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}  
	}  
}  