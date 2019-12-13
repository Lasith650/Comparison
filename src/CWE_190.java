import java.util.ArrayList;
import java.util.Arrays;

public class CWE_190 implements CWE {
    private String description = "Integer Overflow or Wraparound";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service", "Tampering",
            "Information Disclosure", "Elevation of Privilege"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }
}
