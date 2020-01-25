import java.util.ArrayList;
import java.util.Arrays;

public class OBJ09_J implements CERTVulnerability {
    private String description = "Compare Classes and not Class Names";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_486"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/OBJ09-J.+Compare+classes+and+not+class+names";
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
