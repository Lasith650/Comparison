import java.util.ArrayList;
import java.util.Arrays;

public class CWE_266 implements CWE {
    private String description = "Incorrect Privilege Assignment";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Elevation of Privilege"));
    private String phase = "Design";
    private String keyWords = "A product incorrectly assigns a privilege to a particular actor, creating an " +
            "unintended sphere of control for that actor";
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
