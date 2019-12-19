import java.util.ArrayList;
import java.util.Arrays;

public class CWE_375 implements CWE {
    private String description = "Returning a Mutable Object to an Untrusted Caller";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Elevation of Privilege"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }
}
