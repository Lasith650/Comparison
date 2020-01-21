import java.util.ArrayList;
import java.util.Arrays;

public class CWE_502 implements CWE {
    private String description = "Deserialization of Untrusted Data";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Denial of Service"));
    private String phase = "Design";
    private String keyWords = "The application de-serializes untrusted data without sufficiently verifying that the " +
            "resulting data will be valid";
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
