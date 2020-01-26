import java.util.ArrayList;
import java.util.Arrays;

public class CWE_820 implements CWE {
    private String description = "Missing Synchronization";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "The software utilizes a shared resource in a concurrent manner but does not attempt " +
            "to synchronize access to the resource";
    private String href = "https://cwe.mitre.org/data/definitions/820.html";
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

    @Override
    public String getHref() {
        return href;
    }
}
