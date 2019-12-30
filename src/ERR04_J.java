import java.util.ArrayList;
import java.util.Arrays;

public class ERR04_J implements CERTVulnerability {
    private String description = "Do not complete abruptly from a finally block";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_459"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
