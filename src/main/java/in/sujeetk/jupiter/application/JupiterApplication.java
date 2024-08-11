package in.sujeetk.jupiter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("in.sujeetk.jupiter")
@EnableMongoRepositories(basePackages = "in.sujeetk.jupiter.repository")
public class JupiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(JupiterApplication.class, args);
    }
}
