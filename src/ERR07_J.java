import java.util.ArrayList;
import java.util.Arrays;

public class ERR07_J implements CERTVulnerability {
    private String description = "Do not throw RuntimeException, Exception, or Throwable";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_397"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
