import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.wso2.choreo.analytics.rest.api.conf.APISecurityConfig;
import org.wso2.choreo.analytics.rest.api.exceptions.BadTokenException;
import org.wso2.choreo.analytics.rest.api.exceptions.TokenGenerationException;
import org.wso2.choreo.analytics.rest.api.logging.LoggingData;
import org.wso2.choreo.analytics.rest.api.logging.LoggingDataContextHolder;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

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
