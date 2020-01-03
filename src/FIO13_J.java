import java.util.ArrayList;
import java.util.Arrays;

public class FIO13_J implements CERTVulnerability {
    private String description = "Do not log sensitive information outside a trust boundary";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_359" , "CWE_532"));
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }
}
