
package com.jarcps;
 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
    	ConfigurableApplicationContext appContext = SpringApplication.run(Application.class, args);
        
    }
}
