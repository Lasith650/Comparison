import java.util.ArrayList;
import java.util.Arrays;

public class CWE_493 implements CWE {
    private String description = "Critical Public Variable without Final Modifier";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
    private String phase = "Implementation";
    private String keyWords = "The product has a critical public variable that is not final, which allows the " +
            "variable to be modified to contain unexpected values";
    private String href = "https://cwe.mitre.org/data/definitions/493.html";
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
