package mk.ukim.finki.wp.laboratory1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Laboratory1Application {

    public static void main(String[] args) {
        SpringApplication.run(Laboratory1Application.class, args);
    }

}
