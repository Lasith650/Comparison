import java.util.ArrayList;
import java.util.Arrays;

public class SEC00_J implements CERTVulnerability {
    private String description = "Do not allow privileged blocks to leak sensitive information across a trust boundary";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_266", "CWE_272"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
