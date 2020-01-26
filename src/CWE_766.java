import java.util.ArrayList;
import java.util.Arrays;

public class CWE_766 implements CWE{
    private String description = "Critical Data Element Declared Public";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "The software declares a critical variable field or member to be public when intended " +
            "security policy requires it to be private";
    private String href = "https://cwe.mitre.org/data/definitions/766.html";
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
