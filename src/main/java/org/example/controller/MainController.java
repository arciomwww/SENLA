package org.example.controller;

import org.example.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {
    private ServiceInterface service;

    @Autowired
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
