package org.example;

import org.example.controller.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.example.database.ConnectionHolder;

public class Application {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ConnectionHolder connectionHolder = context.getBean(ConnectionHolder.class);

        Object[] controllers = {
                context.getBean(CarController.class),
        };

        for (Object ctrl : controllers) {
            ctrl.getClass().getMethod("testCRUDOperations").invoke(ctrl);
        }

        connectionHolder.closeAllConnections(true);

        context.close();
    }
}




