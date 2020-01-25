import java.util.ArrayList;
import java.util.Arrays;

public class OBJ05_J implements CERTVulnerability{
    private String description = "Do not Return References to Private Mutable Class Members";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_375"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/OBJ05-J.+Do+not+return+references+to+private+mutable+class+members";

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
