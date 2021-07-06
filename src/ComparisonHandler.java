import java.util.ArrayList;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wso2.choreo.analytics.alert.api.security.JWTUserDetails;
import java.util.Optional;

public class ComparisonHandler {
    public boolean compare(ArrayList<String> associated_strides_for_cwe,
                           ArrayList<String> associated_strides_for_interactions){

        if (associated_strides_for_cwe.size() == associated_strides_for_interactions.size()){
            int count = 0;
            for (int i = 0 ; i < associated_strides_for_cwe.size(); i++){
                for (int a = 0 ; a < associated_strides_for_interactions.size(); a++){
                    if (associated_strides_for_cwe.get(i).equals(associated_strides_for_interactions.get(a))){
                        count = count + 1;
                    }
                }
            }
            System.out.println(count);
            if (count == associated_strides_for_cwe.size()){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }


}
