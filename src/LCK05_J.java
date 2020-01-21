import java.util.ArrayList;
import java.util.Arrays;

public class LCK05_J implements CERTVulnerability {
    private String description = "Synchronize access to static fields that can be modified by untrusted code";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_820"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
