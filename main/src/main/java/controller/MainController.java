package controller;

import service.ServiceInterface;
import org.example.Component;
import org.example.Autowire;

@Component
public class MainController {
    private ServiceInterface service;

    @Autowire
    public void setService(ServiceInterface service) {
        this.service = service;
        System.out.println("Service injected in MainController: " + (service != null));
    }

    public void handleRequest() {
        if (service != null) {
            service.execute();
        } else {
            System.out.println("Service is not initialized.");
        }
    }
}
