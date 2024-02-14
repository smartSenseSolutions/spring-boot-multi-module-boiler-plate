package ss.mod.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class TestHelper {

    private static Environment env;

    @Autowired
    public TestHelper(Environment environment) {
        TestHelper.env = environment;
    }

    public static MultiValueMap<String, String> prepareHeader(boolean validUser) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        if (Boolean.parseBoolean(TestHelper.env.getProperty("app.security.enabled"))) {
            headers.add(HttpHeaders.AUTHORIZATION, ContainerContextInitializer.createToken(validUser));
        }
        return headers;
    }
}
