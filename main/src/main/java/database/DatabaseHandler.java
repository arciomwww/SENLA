package database;

import util.ParametersHolder;
import org.example.Component;
import org.example.Autowire;
import org.example.Value;

@Component
public class DatabaseHandler implements DatabaseInterface {

    @Autowire
    private ParametersHolder parametersHolder;


    @Override
    public String execute() {
        if (parametersHolder != null) {
            return parametersHolder.getSomeText();
        } else {
            return "ParametersHolder not initialized!";
        }
    }
}
