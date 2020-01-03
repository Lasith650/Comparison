import java.util.ArrayList;
import java.util.Arrays;

public class NUM02_J implements CERTVulnerability {
    private String description = "Ensure that division and remainder operations do not result in divide-by-zero errors";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_369"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
