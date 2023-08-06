import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

class TableFoundNot extends Exception {
    public String toString() {
        return "Entered table does not exist";
    }
}

public class DeleteRecordFromUser {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AdvanceJavaProject", "postgres", "postgres");
        System.out.println("Connection Done!");

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter the table name in which you want to delete records: ");
            String tbname = sc.nextLine();

            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM " + tbname);

            if (!tbname.equals(tbname)) {
                throw new TableFoundNot();
            } else {
                ResultSetMetaData rd = rs.getMetaData();
                int columnCount = rd.getColumnCount();
                System.out.println("\nThe Data of the Table you entered is here--:");
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
                System.out.println();

                System.out.print("\nEnter the primary key value of the record to delete: ");
                int primarykey = sc.nextInt();
                sc.nextLine();
                
                String deleteQuery = "DELETE FROM " + tbname + " WHERE id = " + primarykey;
                int rowsAffected = sm.executeUpdate(deleteQuery);
                System.out.println(rowsAffected + " record(s) deleted.");
                
                System.out.println("\nThe Data of the Table after delete operation--:");
                ResultSet rss = sm.executeQuery("SELECT * FROM " + tbname);
                while (rss.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rss.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (TableFoundNot e) {
            System.out.println("Exception caught: " + e);
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }

        conn.close();
        System.out.println("Connection closed!");
    }
}
