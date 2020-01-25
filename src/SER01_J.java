import java.util.ArrayList;
import java.util.Arrays;

public class SER01_J implements CERTVulnerability {
    private String description = "Do not deviate from the proper signatures of serialization methods";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_502"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/SER01-J.+Do+not+deviate+from+the+proper+signatures+of+serialization+methods";

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
