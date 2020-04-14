package Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages={"Application.Services"})
public class ProdavnicaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProdavnicaApplication.class, args);
    }
}
