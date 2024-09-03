package service;

import database.DatabaseInterface;
import org.example.Autowire;
import org.example.Component;

@Component
public class ServiceHandler implements ServiceInterface {

    private DatabaseInterface database;


    @Autowire
    public void setDatabase(DatabaseInterface database) {
        this.database = database;
        System.out.println("Database injected in ServiceHandler: " + (database != null));
    }

    @Override
    public void execute() {
        if (database != null) {
            System.out.println(database.execute());
        } else {
            System.out.println("Database is not initialized.");
        }
    }
}