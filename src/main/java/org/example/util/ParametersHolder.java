package org.example.util;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ParametersHolder {

    @Value("my.param.db")
    private String someText;

    public String getSomeText() {
        return someText;
    }
}
