import java.util.ArrayList;
import java.util.Arrays;

public class CWE_532 implements CWE {
    private String description = "Inclusion of Sensitive Information in Log Files";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "Information written to log files can be of a sensitive nature and give valuable " +
            "guidance to an attacker or expose sensitive user information";
    private String href = "https://cwe.mitre.org/data/definitions/532.html";
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
