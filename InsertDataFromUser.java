import java.sql.*;
import java.util.Scanner;

class TableNotFound extends Exception{
	public String toString(){
        return "Entered table Does not Exists";
    }
}

public class InsertDataFromUser {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AdvanceJavaProject", "postgres", "postgres");
        System.out.println("Connection done!");

        Scanner sc = new Scanner(System.in);
        
        try {
        	System.out.print("Enter the table name in which you want to insert data: ");
            String tbname = sc.nextLine();
            if(!tbname.equals(tbname)) {
            	throw new TableNotFound();
            }else {
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM " + tbname);
            ResultSetMetaData rmd = rs.getMetaData();
            int columnCount = rmd.getColumnCount();
            System.out.println();

            System.out.print("The table contains the following columns:\n ");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rmd.getColumnName(i);
                String columnType = rmd.getColumnTypeName(i);
                System.out.print(columnName + "(" + columnType + ")");
                System.out.print("\t");
            }
            System.out.println();

            System.out.print("Enter how many records you want to insert: ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= n; i++) {
                System.out.println("Enter data for record " + i + ":");
                String[] values = new String[columnCount]; // Array to store values for each column

                for (int j = 1; j <= columnCount; j++) {
                    String columnName = rmd.getColumnName(j);
                    String columnType = rmd.getColumnTypeName(j);
                    System.out.print("Enter " + columnName + ": ");
                    values[j - 1] = sc.nextLine();
                }

                String query = "INSERT INTO " + tbname + " VALUES (";
                for (int k = 0; k < columnCount; k++) {
                    query += "'" + values[k] + "'";
                    if (k < columnCount - 1) {
                        query += ",";
                    }
                }
                query += ")";

                sm.executeUpdate(query);
                System.out.println("Record " + i + " inserted successfully!");
                System.out.println();
            }
          }
        } catch (TableNotFound e) {
        	System.out.println("Exception caught: "+ e);     	
        }catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }

        conn.close();
        System.out.println("Connection closed!");
    }
}

