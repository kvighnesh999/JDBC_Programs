import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

class TableNotFoundException extends Exception {
    public String toString() {
        return "Entered table does not exist";
    }
}

public class UpdateTableFromUser {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub	
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AdvanceJavaProject", "postgres", "postgres");
        System.out.println("Connection Done!");

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter the table name in which you want to update records: ");
            String tbname = sc.nextLine();

            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM " + tbname);

            if (!tbname.equals(tbname)) {
                throw new TableNotFoundException();
            } else {
                ResultSetMetaData rd = rs.getMetaData();
                int columnCount = rd.getColumnCount();

                System.out.println("\nThe table contains the following columns:");
                for (int i = 1; i <= columnCount; i++) {
                	String columnName = rd.getColumnName(i);
                    String columnType = rd.getColumnTypeName(i);
                    System.out.print(columnName + "(" + columnType + ")\t");
                }
                System.out.println();

                System.out.print("\nEnter the primary key value of the record to update: ");
                int primarykey = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter the column name to update: ");
                String colnameToUpdate = sc.nextLine();

                System.out.print("Enter the new value for the column: ");
                String newvalue = sc.nextLine();

                String updateQuery = "UPDATE " + tbname + " SET " + colnameToUpdate + " = '" + newvalue + "' WHERE id = " + primarykey;
                int rowsAffected = sm.executeUpdate(updateQuery);
                System.out.println(rowsAffected + " record(s) updated.");

                ResultSet updatedRs = sm.executeQuery("SELECT * FROM " + tbname + " WHERE id = " + primarykey);
                if (updatedRs.next()) {
                    System.out.println("\nUpdated Record:");
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(updatedRs.getString(i) + "\t");
                    }
                    System.out.println();
                } else {
                    System.out.println("Record not found.");
                }
            }
        } catch (TableNotFoundException e) {
            System.out.println("Exception caught: " + e);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }

        conn.close();
        System.out.println("Connection closed!");
    }
}
