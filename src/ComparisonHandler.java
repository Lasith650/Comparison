import java.util.ArrayList;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
