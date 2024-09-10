package org.example.database;

import org.example.util.ParametersHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHandler implements DatabaseInterface {

    @Autowired
    private ParametersHolder parametersHolder;

    //chat
    @Value("${some.property}")
    private String someProperty;

    @Override
    public String execute() {
        if (parametersHolder != null) {
            return parametersHolder.getSomeText();
        } else {
            return "ParametersHolder not initialized!";
        }
    }
}
