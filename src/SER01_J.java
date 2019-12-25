import java.util.ArrayList;
import java.util.Arrays;

public class SER01_J implements CERTVulnerability {
    private String description = "Do not deviate from the proper signatures of serialization methods";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_502"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
