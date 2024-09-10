package org.example;

import org.example.controller.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MainController controller = context.getBean(MainController.class);
        controller.handleRequest();

        Object[] controllers = {
                context.getBean(CarController.class),
                context.getBean(CarModelController.class),
                context.getBean(FeedbackController.class),
                context.getBean(InsuranceController.class),
                context.getBean(LocationController.class),
                context.getBean(RentalController.class),
                context.getBean(RoleController.class),
                context.getBean(UserController.class),
                context.getBean(UserRolesController.class)
        };

        for (Object ctrl : controllers) {
            ctrl.getClass().getMethod("testCRUDOperations").invoke(ctrl);
        }

        context.close();
    }
}





