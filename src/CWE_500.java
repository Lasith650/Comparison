import java.util.ArrayList;
import java.util.Arrays;

public class CWE_500 implements CWE {
    private String description = "Public Static Field Not Marked Final";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
    private String phase = "Implementation";
    private String keyWords = "An object contains a public static field that is not marked final, which might allow " +
            "it to be modified in unexpected ways";
    private String href = "https://cwe.mitre.org/data/definitions/500.html";
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
