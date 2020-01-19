import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void main(String[] args) throws IOException {
        Comparison comparison = new Comparison();
        CERTVulnerabilityFactory certVulnerabilityFactory = new CERTVulnerabilityFactory();
        CWEFactory cweFactory = new CWEFactory();
        InteractionHandler interactionHandler = new InteractionHandler();
        ComparisonHandler comparisonHandler = new ComparisonHandler();
        TextCreator textCreator = new TextCreator();
        String text = "";
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        ComparisonReportGenerator comparisonReportGenerator = new ComparisonReportGenerator();

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Comparison Report");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body style=background-color:#f2f2f2>");
        sb.append("<h1>Comparison Report</h1>");

        //Bellow are associated with the interactions
        ArrayList<String> tmtInteractions = interactionHandler.distinctInteractions();
        //To get the security guideline violations list
        ArrayList<String> violatedCERTSecurityGuidelines = comparison.getViolatedCERTTSecurityGuidelines();
        for (int x = 0; x < violatedCERTSecurityGuidelines.size(); x++) {
            //CERT Vulnerability div
            sb.append("<div style=height:200px;background-color:#e6e6e6;padding-left:5px><h3>Violated CERT: "+violatedCERTSecurityGuidelines.get(x)+"</h3>");
            //To get the associated CWE for specific interaction
            CERTVulnerability certVulnerability = certVulnerabilityFactory.getCERTVulnerability(violatedCERTSecurityGuidelines.get(x));
            ArrayList<String> associatedCWE = certVulnerability.getAssociatedCWE();
            for (int y = 0; y < associatedCWE.size(); y++) {
                //To find whether there are more than one interaction which will match one CWE, count and matchingInteractions are used is used
                int count = 0;
                ArrayList<String> matchingInteractions = new ArrayList<String>();
                CWE cwe = cweFactory.getCWE(associatedCWE.get(y));
                String phase = cwe.getPhase();
                if (phase == "Implementation"){
                    sb.append("<div style=height:auto;background-color:#e6e6e6;padding-left:5px><h4>" +
                            "This may happen as a result of violating  "+associatedCWE.get(y)+", " +
                            "if so it is an implementation fault.</h4></div>");
                    System.out.println("is an implementation fault");
                }else{
                    System.out.println("Design fault");
                    //This list contains the associated STRIDE's for a specific CWE
                    ArrayList<String> associatedSTRIDE = cwe.getAssociatedSTRIDE();
                    for (String i : tmtInteractions) {
                        ArrayList<String> associatedStrides = interactionHandler.associatedStrides(i);
//                    boolean answer = comparisonHandler.compare(associatedSTRIDE, associatedStrides);
                        boolean answer = associatedStrides.containsAll(associatedSTRIDE);
                        if (answer == true) {
                            matchingInteractions.add(i);
                            count = count + 1;
                        }
                        text = text + textCreator.getOutput(answer, violatedCERTSecurityGuidelines.get(x), associatedCWE.get(y), i);
                    }
                    //Add comparing the descriptions part here
                    if (count > 1) {
                        System.out.println("Count is more than 1");
                        for (int i = 0; i < matchingInteractions.size() - 1; i++) {
                            if (similarityCalculator.getCosineSimilarityValue() == similarityCalculator.getCosineSimilarityValue()) {
                                System.out.println("Both are equal as this is not correctly implemented");
                            }
                        }
                    }
                }
            }
            //Ending of CERT Vulnerability Div
            sb.append("</div>");
        }
        System.out.println(text);
        //comparisonReportGenerator.getComparisonReport();

        sb.append("</body>");
        sb.append("</html>");
        FileWriter stream = new FileWriter("d:/Project/Comparison3.html");
        BufferedWriter out = new BufferedWriter(stream);
        out.write(sb.toString());
        out.close();
    }
}
