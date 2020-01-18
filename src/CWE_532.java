import java.util.ArrayList;
import java.util.Arrays;

public class CWE_532 implements CWE {
    private String description = "Inclusion of Sensitive Information in Log Files";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
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
