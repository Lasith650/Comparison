import java.util.ArrayList;
import java.util.Arrays;

public class SER05_J implements CERTVulnerability {
    private String description = "Do not Serialize instances of Inner Classes";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_499"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/SER05-J.+Do+not+serialize+instances+of+inner+classes";
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
