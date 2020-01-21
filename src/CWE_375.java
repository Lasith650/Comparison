import java.util.ArrayList;
import java.util.Arrays;

public class CWE_375 implements CWE {
    private String description = "Returning a Mutable Object to an Untrusted Caller";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Elevation of Privilege"));
    private String phase = "Implementation";
    private String keyWords = "Sending non-cloned mutable data as a return value may result in that data being " +
            "altered or deleted by the calling function";
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
}
