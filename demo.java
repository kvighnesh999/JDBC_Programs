import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class demo {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/AdvanceJavaProject","postgres","postgres");
			Scanner sc = new Scanner(System.in);
			for (int i = 0; i < 5; i++) {
				System.out.println("Enter details for student " + (i + 1));
				System.out.print("Roll Number (rno): ");
				int rno = sc.nextInt();
				sc.nextLine();
				System.out.print("Name (snam): ");
				String sname = sc.nextLine();
				System.out.print("Percentage (per): ");
				int per = sc.nextInt();
				sc.nextLine();
				System.out.print("Gender (gender): ");
				String gender = sc.nextLine();
				System.out.print("Class (class): ");
				String Class = sc.nextLine();
				PreparedStatement st = conn.prepareStatement("insert into student values (?,?,?,?,?)");
				st.setInt(1, rno);
				st.setString(2, sname);
				st.setInt(3, per);
				st.setString(4, gender);
				st.setString(5, Class);
				st.executeUpdate();
			}
			//displaying highest percentage
			PreparedStatement stmt = conn.prepareStatement("select * from student order by per desc limit 1");
			ResultSet rs = stmt.executeQuery();
			System.out.println("\nDetails of the student with the highest percentage:");
			while (rs.next()) {
				int rno = rs.getInt("rno");
				String sname = rs.getString("sname");
				int per = rs.getInt("per");
				String gender=rs.getString("gender");
				String Class=rs.getString("class");
				System.out.println("Roll Number: " + rno);
				System.out.println("Name: " + sname);
				System.out.println("Percentage: " + per+"%");
				System.out.println("Gender: " + gender);
				System.out.println("Class: " + Class);
			}
			conn.close();
			sc.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
