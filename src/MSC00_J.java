import java.util.ArrayList;
import java.util.Arrays;

public class MSC00_J implements CERTVulnerability {
    private String description = "Use SSLSocket rather than Socket for secure data exchange";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_311"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/MSC00-J.+Use+SSLSocket+rather+than+Socket+for+secure+data+exchange";
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
