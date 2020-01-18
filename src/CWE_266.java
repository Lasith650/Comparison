import java.util.ArrayList;
import java.util.Arrays;

public class CWE_266 implements CWE {
    private String description = "Incorrect Privilege Assignment";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Elevation of Privilege"));
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
