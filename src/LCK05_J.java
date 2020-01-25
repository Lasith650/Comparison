import java.util.ArrayList;
import java.util.Arrays;

public class LCK05_J implements CERTVulnerability {
    private String description = "Synchronize access to static fields that can be modified by untrusted code";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_820"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/LCK05-J.+Synchronize+access+to+static+fields+that+can+be+modified+by+untrusted+code";
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
