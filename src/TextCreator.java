import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextCreator {

    public String getOutput(boolean result, String cert, String cwe, String interaction) {
        if (result == true) {
            return "CERT security vulnerability : " + cert + " may occur as a result of : " + cwe + ". This may happen" +
                    " as a result of the interaction : " + interaction + " indicated in the tmt output" + "\n";
        } else {
            return "CERT security vulnerability :" + cert + "may not due to a design fault" + "\n";
        }
    }

    //This method is used to create the Text file
    public void usingBufferedWriter(String data) throws IOException {
        String fileContent = data;
        BufferedWriter writer = new BufferedWriter(new FileWriter("d:/Project/Comparison.txt"));
        writer.write(fileContent);
        writer.close();
    }
}
