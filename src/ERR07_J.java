import java.util.ArrayList;
import java.util.Arrays;

public class ERR07_J implements CERTVulnerability {
    private String description = "Do not throw RuntimeException, Exception, or Throwable";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_397"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/ERR07-J.+Do+not+throw+RuntimeException%2C+Exception%2C+or+Throwable";
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
