package org.demo.parcialmagneto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class ParcialMagnetoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ParcialMagnetoApplication.class, args);
        System.out.println("Aplicacion de Analisis Experto Funcionando Correctamente");
    }
    
}
