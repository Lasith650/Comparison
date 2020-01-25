import java.util.ArrayList;
import java.util.Arrays;

public class NUM00_J implements CERTVulnerability {
    private String description = "Integer Overflow";
    private ArrayList<String> associatedCWE = new ArrayList<>(Arrays.asList("CWE_190","CWE_191","CWE_682"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/NUM00-J.+Detect+or+prevent+integer+overflow";

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
