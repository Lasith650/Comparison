import java.util.ArrayList;
import java.util.Arrays;

public class CWE_369 implements CWE {
    private String description = "Divide By Zero";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service"));
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

    @Override
    public String getKeyWords() {
        return null;
    }
}
