import java.io.BufferedWriter;
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

    public String getInteractionConcatString(String interaction, ArrayList<String> associatedSTRIDE){
        String concatString = "";
        for (int i = 0 ; i < associatedSTRIDE.size() ; i++) {
            ArrayList<String> descriptions = new ArrayList<String>();
            System.out.println(associatedSTRIDE.get(i));

            try {
                Connection con = DatabaseConnection.getDatabaseConnection_instance().getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select associated_stride_description from tmt_output_tbl where interaction='" + interaction + "' && associated_stride='" + associatedSTRIDE.get(i) + "'");
                while (rs.next()) {
                    descriptions.add(rs.getString(1));
                }
                for (String x : descriptions) {
                    concatString = concatString + x + " ";
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("bellow is the concatString");
        System.out.println(concatString);
        return concatString;
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
                System.out.println(associatedCWE.get(y));
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
                        //to find the one with the most similarity matching index is been used
                        int highestSimilarityIndex = 0;
                        System.out.println("Count is more than 1");
                        for (int i = 0; i < matchingInteractions.size() - 1; i++) {
                            //This string contains the total details for highest similarity index for the interactions
                            String description1 = comparison.getInteractionConcatString(matchingInteractions.get(highestSimilarityIndex),associatedSTRIDE);
                            //This string contains the total details for highest similarity index for the interactions
                            String description2 = comparison.getInteractionConcatString(matchingInteractions.get(i+1),associatedSTRIDE);
                            String description3 = cwe.getKeyWords();
                            System.out.println("*************"+description1);
                            System.out.println("*************"+description2);
                            System.out.println("*************"+description3);

                            if (similarityCalculator.getCosineSimilarityValue(description1 , description3) < similarityCalculator.getCosineSimilarityValue(description2 , description3)) {
                                highestSimilarityIndex = highestSimilarityIndex + 1;
                            }
                        }
                        //this will output the best matching interaction
                        System.out.println("most matching interaction is : " + matchingInteractions.get(highestSimilarityIndex));
                    }else if (count == 1){
                        System.out.println("Count is one");
                    }else {
                        System.out.println("Not identified as a design fault as there are no matching interactions");
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
