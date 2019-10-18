package com.k003.nam;

import com.k003.nam.UserController.UserController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {

        UserController mainController = new UserController();
        mainController.controller();
    }
}
