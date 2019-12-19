import java.util.ArrayList;
import java.util.Arrays;

public class OBJ09_J implements CERTVulnerability {
    private String description = "Compare Classes and not Class Names";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_486"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
