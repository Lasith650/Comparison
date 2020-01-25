import java.util.ArrayList;
import java.util.Arrays;

public class MET09_J implements CERTVulnerability {
    private String description = "Classes that define an equals() method must also define a hashCode() method";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_581"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/MET09-J.+Classes+that+define+an+equals%28%29+method+must+also+define+a+hashCode%28%29+method";
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
