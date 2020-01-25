import java.util.ArrayList;
import java.util.Arrays;

public class OBJ01_J implements CERTVulnerability {
    private String description = "Limit accessibility of fields";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_766"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/OBJ01-J.+Limit+accessibility+of+fields";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }

    @Override
    public String getHref() {
        return href;
    }
}
