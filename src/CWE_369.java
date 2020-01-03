import java.util.ArrayList;
import java.util.Arrays;

public class CWE_369 implements CWE {
    private String description = "Comparison of Classes by Name";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }
}
