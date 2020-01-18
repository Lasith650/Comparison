import java.util.ArrayList;
import java.util.Arrays;

public class CWE_492 implements CWE {
    private String description = "Use of Inner Class Containing Sensitive Data";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
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
