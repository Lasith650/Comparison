import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Comparison {
    public ArrayList getViolatedCERTTSecurityGuidelines() {
        ArrayList<String> violatedCERTSecurityGuidelines = new ArrayList<String>();
        try {
            Connection con = DatabaseConnection.getDatabaseConnection_instance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from identified_cert_security_guideline_violations_tbl");
            while (rs.next()) {
                violatedCERTSecurityGuidelines.add(rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return violatedCERTSecurityGuidelines;
    }

    public static void main(String[] args) {
        Comparison comparison = new Comparison();
        CERTVulnerabilityFactory certVulnerabilityFactory = new CERTVulnerabilityFactory();
        CWEFactory cweFactory = new CWEFactory();
        InteractionHandler interactionHandler = new InteractionHandler();
        ArrayList<String> violatedCERTSecurityGuidelines = comparison.getViolatedCERTTSecurityGuidelines();
        for (int i = 0; i < violatedCERTSecurityGuidelines.size(); i++) {
            System.out.println(violatedCERTSecurityGuidelines.get(i));
            CERTVulnerability certVulnerability = certVulnerabilityFactory.getCERTVulnerability(violatedCERTSecurityGuidelines.get(i));
            ArrayList<String> associatedCWE = certVulnerability.getAssociatedCWE();
            System.out.println(associatedCWE.size());
            for (int x = 0; x < associatedCWE.size(); x++) {
                System.out.println(associatedCWE.get(x));
                CWE cwe = cweFactory.getCWE(associatedCWE.get(x));
                ArrayList<String> associatedSTRIDE = cwe.getAssociatedSTRIDE();
                for (String a : associatedSTRIDE) {
                    System.out.println(a);
                }
            }
        }

        //Bellow are associated with the interactions
        ArrayList<String> tmtInteractions = interactionHandler.distinctInteractions();
        for (String i : tmtInteractions){
            System.out.println(i);
            ArrayList<String> associatedStrides = interactionHandler.associatedStrides(i);
            for (String a : associatedStrides){
                System.out.println(a);
            }
        }
    }
}
