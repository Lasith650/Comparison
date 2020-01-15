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
        ArrayList<String> violatedCERTSecurityGuidelines = comparison.getViolatedCERTTSecurityGuidelines();
        String text = "";
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();
        ComparisonReportGenerator comparisonReportGenerator = new ComparisonReportGenerator();

        //Bellow are associated with the interactions
        ArrayList<String> tmtInteractions = interactionHandler.distinctInteractions();
        for (int x = 0; x < violatedCERTSecurityGuidelines.size(); x++) {
            System.out.println(violatedCERTSecurityGuidelines.get(x));
            CERTVulnerability certVulnerability = certVulnerabilityFactory.getCERTVulnerability(violatedCERTSecurityGuidelines.get(x));
            ArrayList<String> associatedCWE = certVulnerability.getAssociatedCWE();
            System.out.println(associatedCWE.size());
            for (int y = 0; y < associatedCWE.size(); y++) {
                //To find whether there are more than one interaction which will match one CWE, count and matchingInteractions are used is used
                int count = 0;
                ArrayList<String> matchingInteractions = new ArrayList<String>();
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
                    if (answer == true) {
                        matchingInteractions.add(i);
                        count = count + 1;
                    }
                    text = text + textCreator.getOutput(answer, violatedCERTSecurityGuidelines.get(x), associatedCWE.get(y), i);
                    System.out.println(text);
                }
                //Add comparing the descriptions part here
                if (count > 0) {
                    String result = matchingInteractions.get(0);
                    System.out.println("Count is more than one");
                    for (int i = 0; i < matchingInteractions.size() - 1; i++) {
                        if (similarityCalculator.getCosineSimilarityValue() == similarityCalculator.getCosineSimilarityValue()) {
                            System.out.println("Both are equal as this is not correctly implemented");
                        }
                    }
                }
            }
            comparisonReportGenerator.getComparisonReport();
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html>");
//        sb.append("<head>");
//        sb.append("<title>Title Of the page");
//        sb.append("</title>");
//        sb.append("</head>");
//        sb.append("<body> <b>Hello World</b>");
//        for (int a = 0 ; a < 5 ; a++){
//            sb.append("<div>"+a+"</div>");
//        }
//        sb.append("</body>");
//        sb.append("</html>");
//        FileWriter fstream = new FileWriter("d:/Project/Comparison.html");
//        BufferedWriter out = new BufferedWriter(fstream);
//        out.write(sb.toString());
//        out.close();
//        try {
//            textCreator.usingBufferedWriter(text);
//        } catch (Exception e) {
//            System.out.println(e);
//        }

            //for saving the template html and feeding required data, for testing purpose
//        File htmlTemplateFile = new File("D:\\Project\\Comparison\\src\\template.html");
//        String htmlString = FileUtils.readFileToString(htmlTemplateFile);
//        String title = "New Page";
//        String body = "This is Body";
//        htmlString = htmlString.replace("$title", title);
//        htmlString = htmlString.replace("$body", body);
//        for (int i=0 ; i<1 ; i++){
//            String description = "done";
//            htmlString = htmlString.replace("$description", description);
//        }
//        File newHtmlFile = new File("d:/Project/new.html");
//        FileUtils.writeStringToFile(newHtmlFile, htmlString);


            //File Save dialog code fragment
//            JFrame parentFrame = new JFrame();
//
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setDialogTitle("Specify a file to save");
//
//            int userSelection = fileChooser.showSaveDialog(parentFrame);
//
//            if (userSelection == JFileChooser.APPROVE_OPTION) {
//                File fileToSave = fileChooser.getSelectedFile();
//                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
//            }
        }
    }
}
