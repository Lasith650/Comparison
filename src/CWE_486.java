import java.util.ArrayList;
import java.util.Arrays;

public class CWE_486 implements CWE {
    private String description = "Comparison of Classes by Name";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure",
            "Denial of Service"));
    private String phase = "Implementation";
    private String keyWords = "The program compares classes by name, which can cause it to use the wrong class when " +
            "multiple classes can have the same name";
    private String href = "https://cwe.mitre.org/data/definitions/486.html";
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
