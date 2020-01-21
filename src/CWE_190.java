import java.util.ArrayList;
import java.util.Arrays;

public class CWE_190 implements CWE {
    private String description = "Integer Overflow or Wraparound";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service", "Tampering",
            "Information Disclosure", "Elevation of Privilege"));
    private String phase = "Implementation";
    private String keyWords = "The software performs a calculation that can produce an integer overflow or wraparound, " +
            "when the logic assumes that the resulting value will always be larger than the original value. This can " +
            "introduce other weaknesses when the calculation is used for resource management or execution control.";
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
