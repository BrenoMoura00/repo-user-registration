package com.example.userregistrationservice.config;

import org.springframework.stereotype.Component;

@Component
public class Logger {

    private static Logger instancia;

    private Logger(){}

    public static synchronized Logger getInstancia(){

        if(instancia == null) {
            instancia = new Logger();
        }

        return instancia;
    }

    public void info(String message) {
        System.out.println("Info: " + message);
    }

    public void error(String message) {
        System.out.println("Erro: " + message);
    }

}
