package az.code.trammanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TramManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TramManagementSystemApplication.class, args);
    }

}
