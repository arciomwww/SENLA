package org.example.service;

import org.example.database.DatabaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceHandler implements ServiceInterface {

    private DatabaseInterface database;


    @Autowired
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