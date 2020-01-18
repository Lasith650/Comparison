import java.util.ArrayList;
import java.util.Arrays;

public class CWE_766 implements CWE{
    private String description = "Critical Data Element Declared Public";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
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
