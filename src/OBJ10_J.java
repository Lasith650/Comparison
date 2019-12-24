import java.util.ArrayList;
import java.util.Arrays;

public class OBJ10_J implements CERTVulnerability{
    private String description = "Do not use public static non-final fields";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_493", "CWE_500"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
