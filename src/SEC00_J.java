import java.util.ArrayList;
import java.util.Arrays;

public class SEC00_J implements CERTVulnerability {
    private String description = "Do not allow privileged blocks to leak sensitive information across a trust boundary";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_266", "CWE_272"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/SEC00-J.+Do+not+allow+privileged+blocks+to+leak+sensitive+information+across+a+trust+boundary";

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
