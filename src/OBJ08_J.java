import java.util.ArrayList;
import java.util.Arrays;

public class OBJ08_J implements CERTVulnerability {
    private String description = "Do not Expose Private Members of an Outer Class from Within a Nested Class";
    private ArrayList<String> associatedCWE = new ArrayList<String>(Arrays.asList("CWE_492"));
    private String href = "https://wiki.sei.cmu.edu/confluence/display/java/OBJ08-J.+Do+not+expose+private+members+of+an+outer+class+from+within+a+nested+class";

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
