import java.util.ArrayList;
import java.util.Arrays;

public class CWE_191 implements CWE {
    private String description = "Integer Underflow";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service", "Tampering",
            "Information Disclosure", "Elevation of Privilege"));
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return null;
    }
}
