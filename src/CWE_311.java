import java.util.ArrayList;
import java.util.Arrays;

public class CWE_311 implements CWE {
    private String description = "Missing Encryption of Sensitive Data";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "The software does not encrypt sensitive or critical information before storage or " +
            "transmission";
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
