import java.sql.*;
import java.util.ArrayList;

public class Comparison {

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
                        "on a.cwe_id = b.cwe_id where a.cert_id = '"+i+"'");
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2)+ " " +
                            rs.getString(3)+ " " + rs.getString(4)+ " " +
                            rs.getString(5)+ " " + rs.getString(6));

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

    }
}
