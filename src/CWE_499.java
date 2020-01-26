import java.util.ArrayList;
import java.util.Arrays;

public class CWE_499 implements CWE {
    private String description = "Serializable Class Containing Sensitive Data";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
    private String phase = "Implementation";
    private String keyWords = "The code contains a class with sensitive data but the class does not explicitly " +
            "deny serialization The data can be accessed by serializing the class through another class";
    private String href = "https://cwe.mitre.org/data/definitions/499.html";
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
