import java.util.ArrayList;
import java.util.Arrays;

public class CWE_397 implements CWE {
    private String description = "Declaration of Throws for Generic Exception";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Repudiation"));
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
