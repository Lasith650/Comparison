import java.util.ArrayList;
import java.util.Arrays;

public class CWE_581 implements CWE {
    private String description = "Object Model Violation: Just One of Equals and Hashcode Defined";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering"));
    private String phase = "Implementation";
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }

    @Override
    public String getPhase() {
        return phase;
    }
}
