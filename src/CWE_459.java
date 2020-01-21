import java.util.ArrayList;
import java.util.Arrays;

public class CWE_459 implements CWE {
    private String description = "Incomplete Cleanup";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "The software does not properly clean up and remove temporary or supporting resources " +
            "after they have been used";
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
        return keyWords;
    }
}
