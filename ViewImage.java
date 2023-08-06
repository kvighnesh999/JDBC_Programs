import java.sql.*;
import java.io.*;

public class ViewImage {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/AdvanceJavaProject", "postgres", "postgres");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT image FROM imagetable WHERE names = 'sonoo'");

            if (rs.next()) {
                InputStream in = rs.getBinaryStream("image");
                FileOutputStream out = new FileOutputStream("retrieved2_image.jpg");

                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                in.close();
                out.close();

                System.out.println("Image retrieved and saved successfully.");
            } else {
                System.out.println("No image found with the given name.");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
