package itemservice.validation;

import itemservice.validation.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//public class ValidationApplication implements WebMvcConfigurer {
public class ValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationApplication.class, args);
    }

//    @Override
//    public Validator getValidator() {
//        return new ItemValidator();
//    }

}
