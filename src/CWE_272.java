import java.util.ArrayList;
import java.util.Arrays;

public class CWE_272 implements CWE {
    private String description = "Least Privilege Violation";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Elevation of Privilege", "Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "The elevated privilege level required to perform operations such as chroot() " +
            "should be dropped immediately after the operation is performed";
    private String href = "https://cwe.mitre.org/data/definitions/272.html";
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
