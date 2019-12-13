import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ModuleThree {
    static String output = "";

    //With this method out put will be saved as a text file
    public static void usingBufferedWriter(String data) throws IOException {
        String fileContent = data;
        BufferedWriter writer = new BufferedWriter(new FileWriter("d:/Project/Comparison.txt"));
        writer.write(fileContent);
        writer.close();
    }

    public static void main(String[] args) {
        Connection con = DatabaseConnection.getDatabaseConnection_instance().getConnection();
        ArrayList<String> violatedCERTSecurityGuidelines = new ArrayList<String>();

        try {

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from identified_cert_security_guideline_violations_tbl");
            while (rs.next()) {
                violatedCERTSecurityGuidelines.add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        for (String i : violatedCERTSecurityGuidelines) {

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from cwe_tbl as a inner join cwe_stride_tbl as b " +
                        "on a.cwe_id = b.cwe_id inner join stride_tbl as c " +
                        "on b.security_control_id = c.security_control_id where a.cert_id = '" + i + "'");
                while (rs.next()) {
                    output = output + i + " " + rs.getString(1) + " " + rs.getString(2) + " " +
                            rs.getString(3) + " " + rs.getString(4) + " " +
                            rs.getString(5) + " " + rs.getString(6) + " " +
                            rs.getString(7) + " " + rs.getString(8) + " " +
                            rs.getString(9) + "\n";
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            con.close();
            ;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(output);
        try {
            usingBufferedWriter(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
