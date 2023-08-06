import java.sql.DriverManager;
import java.sql.*;
import java.util.*;

public class UserInputCreateTable {
    public static void main(String[] args)throws Exception {
    	Class.forName("org.postgresql.Driver");  
    	Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AdvanceJavaProject","postgres","postgres");
    	System.out.println("Database Connected Succesfully...");
    	
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter table name: ");
        String tbname = sc.nextLine();
        
        System.out.print("Enter How many column you want? : ");
        int n=sc.nextInt();
        
        String[] column = new String[n];
        String[] datatype = new String[n];
        for(int i=0;i<n;i++) {
        	System.out.print("Enter Column "+(i+1)+" :");
			column[i]=sc.next();
			System.out.print("Enter Column "+(i+1)+"'s datatype : ");
			datatype[i]=sc.next();
        }
        		
        try {
            Statement sm = cn.createStatement();
            String query = "CREATE TABLE " + tbname + " (";
            query += column[0] + " " + datatype[0] + " PRIMARY KEY, ";
            for (int i = 1; i < n; i++) {
                query += column[i] + " " + datatype[i] + ",";
            }
            query = query.substring(0, query.length() - 1);
            query += ")";

            sm.executeUpdate(query);
            System.out.println();
            System.out.println(tbname + " Table created successfully!");
            
        } catch (SQLException e) {
            System.out.println("Table creation failed!");
        }
    }
}
