package pl.umcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CreatorMainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CreatorMainApp.class, args);
        ctx.close();
    }
}

