import java.util.ArrayList;
import java.util.Arrays;

public class FIO13_J implements CERTVulnerability {
    private String description = "Do not log sensitive information outside a trust boundary";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_359" , "CWE_532"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/FIO13-J.+Do+not+log+sensitive+information+outside+a+trust+boundary";
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedCWE() {
        return associatedCWE;
    }

    @Override
    public String getHref() {
        return href;
    }
}
