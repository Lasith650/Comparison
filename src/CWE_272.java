import java.util.ArrayList;
import java.util.Arrays;

public class CWE_272 implements CWE {
    private String description = "Least Privilege Violation";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Elevation of Privilege", "Information Disclosure"));
    private String phase = "Design";
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
