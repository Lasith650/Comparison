import java.util.ArrayList;
import java.util.Arrays;

public class CWE_486 implements CWE {
    private String description = "Comparison of Classes by Name";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Tampering", "Information Disclosure",
            "Denial of Service"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }
}
