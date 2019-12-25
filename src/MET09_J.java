import java.util.ArrayList;
import java.util.Arrays;

public class MET09_J implements CERTVulnerability {
    private String description = "Classes that define an equals() method must also define a hashCode() method";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_581"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
