import java.util.ArrayList;
import java.util.Arrays;

public class OBJ01_J implements CERTVulnerability {
    private String description = "Limit accessibility of fields";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_766"));

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
