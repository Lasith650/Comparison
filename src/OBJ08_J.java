import java.util.ArrayList;
import java.util.Arrays;

public class OBJ08_J implements CERTVulnerability {
    private String description = "Do not Expose Private Members of an Outer Class from Within a Nested Class";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_492"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
