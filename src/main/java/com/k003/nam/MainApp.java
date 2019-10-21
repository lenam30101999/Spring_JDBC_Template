package com.k003.nam;

import com.k003.nam.UserController.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class MainApp {
    public static void main(String[] args) {

        UserController mainController = new UserController();
        mainController.controller();

    }
}
