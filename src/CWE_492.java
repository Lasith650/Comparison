import java.util.ArrayList;
import java.util.Arrays;

public class CWE_492 implements CWE {
    private String description = "Use of Inner Class Containing Sensitive Data";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
    private String phase = "Implementation";
    private String keyWords = "Inner classes are translated into classes that are accessible at package scope and " +
            "may expose code that the programmer intended to keep private to attackers";
    private String href = "https://cwe.mitre.org/data/definitions/492.html";
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
