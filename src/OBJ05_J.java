import java.util.ArrayList;
import java.util.Arrays;

public class OBJ05_J implements CERTVulnerability{
    private String description = "Do not Return References to Private Mutable Class Members";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_375"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
