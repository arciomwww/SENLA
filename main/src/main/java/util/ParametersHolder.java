package util;

import org.example.Component;
import org.example.Value;

@Component
public class ParametersHolder {

    @Value("my.param.db")
    private String someText;

    public String getSomeText() {
        return someText;
    }
}
