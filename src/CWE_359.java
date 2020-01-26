import java.util.ArrayList;
import java.util.Arrays;

public class CWE_359 implements CWE {
    private String description = "Exposure of Private Information";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
    private String phase = "Design";
    private String keyWords = "The software does not properly prevent private data from being accessed by actors who " +
            "either are not explicitly authorized to access the data or do not have the implicit consent of the " +
            "people to which the data is related";
    private String href = "https://cwe.mitre.org/data/definitions/359.html";
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
