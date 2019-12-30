import java.util.ArrayList;
import java.util.Arrays;

public class CWE_502 implements CWE {
    private String description = "Deserialization of Untrusted Data";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Denial of Service"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }
}