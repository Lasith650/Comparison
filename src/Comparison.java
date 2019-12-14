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
        ComparisonHandler comparisonHandler = new ComparisonHandler();
        ArrayList<String> violatedCERTSecurityGuidelines = comparison.getViolatedCERTTSecurityGuidelines();

        //Bellow are associated with the interactions
        ArrayList<String> tmtInteractions = interactionHandler.distinctInteractions();
        for (int x = 0; x < violatedCERTSecurityGuidelines.size(); x++) {
            System.out.println(violatedCERTSecurityGuidelines.get(x));
            CERTVulnerability certVulnerability = certVulnerabilityFactory.getCERTVulnerability(violatedCERTSecurityGuidelines.get(x));
            ArrayList<String> associatedCWE = certVulnerability.getAssociatedCWE();
            System.out.println(associatedCWE.size());
            for (int y = 0; y < associatedCWE.size(); y++) {
                System.out.println(associatedCWE.get(y));
                CWE cwe = cweFactory.getCWE(associatedCWE.get(y));
                ArrayList<String> associatedSTRIDE = cwe.getAssociatedSTRIDE();
                for (String a : associatedSTRIDE) {
                    System.out.println(a);
                }

                for (String i : tmtInteractions) {
                    System.out.println(i);
                    ArrayList<String> associatedStrides = interactionHandler.associatedStrides(i);
                    for (String a : associatedStrides) {
                        System.out.println(a);
                    }
                    boolean answer = comparisonHandler.compare(associatedSTRIDE, associatedStrides);
                    System.out.println(answer);
                }
            }
        }
    }
}
