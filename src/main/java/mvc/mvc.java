package mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"mvc", "controlador", "dao", "modelo", "config"})
public class mvc {
public static void main(String[] args) {
        SpringApplication.run(mvc.class, args); 
    }
}   