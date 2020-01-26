import java.util.ArrayList;
import java.util.Arrays;

public class CWE_369 implements CWE {
    private String description = "Divide By Zero";
    private ArrayList<String> associatedSTRIDE = new ArrayList<>(Arrays.asList("Denial of Service"));
    private String phase = "Implementation";
    private String keyWords = "The product divides a value by zero";
    private String href = "https://cwe.mitre.org/data/definitions/369.html";
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<String> getAssociatedSTRIDE() {
        return associatedSTRIDE;
    }

    @Override
    public String getPhase() {
        return phase;
    }

    @Override
    public String getKeyWords() {
        return keyWords;
    }

    @Override
    public String getHref() {
        return href;
    }
}
