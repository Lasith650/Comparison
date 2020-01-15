import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ComparisonReportGenerator {
    public void getComparisonReport() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Comparison Report");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body style=background-color:#f2f2f2>");
        sb.append("<h1>Comparison Report</h1>");
        for (int a = 1 ; a < 5 ; a++){
            sb.append("<div style=height:200px;background-color:#e6e6e6;padding-left:5px><h2>Interaction "+a+"</h2></div>");
            //sb.append("<div></div>");
        }
        sb.append("</body>");
        sb.append("</html>");
        FileWriter stream = new FileWriter("d:/Project/Comparison.html");
        BufferedWriter out = new BufferedWriter(stream);
        out.write(sb.toString());
        out.close();
    }
}
