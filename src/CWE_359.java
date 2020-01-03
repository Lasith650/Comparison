import java.util.ArrayList;
import java.util.Arrays;

public class CWE_359 implements CWE {
    private String description = "Exposure of Private Information";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Information Disclosure"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }
}
