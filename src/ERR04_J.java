import java.util.ArrayList;
import java.util.Arrays;

public class ERR04_J implements CERTVulnerability {
    private String description = "Do not complete abruptly from a finally block";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_459"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/ERR04-J.+Do+not+complete+abruptly+from+a+finally+block";
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
