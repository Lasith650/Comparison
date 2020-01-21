import java.util.ArrayList;
import java.util.Arrays;

public class CWE_682 implements CWE {
    private String description = "Incorrect Calculation";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service", "Tampering",
            "Information Disclosure", "Elevation of Privilege"));
    private String phase = "Implementation";
    private String keyWords = "The software calls a function, procedure, or routine, but the caller specifies the " +
            "wrong variable or reference as one of the arguments, which may lead to undefined behavior and " +
            "resultant weaknesses";

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
