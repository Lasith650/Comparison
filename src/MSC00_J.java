import java.util.ArrayList;
import java.util.Arrays;

public class MSC00_J implements CERTVulnerability {
    private String description = "Use SSLSocket rather than Socket for secure data exchange";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_311"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
