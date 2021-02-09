package es.sgie.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main class
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages="es.sgie.back.repository")
public class SgieApplication {

    /**
     * Main
     */
    public static void main(String[] args) {
        SpringApplication.run(SgieApplication.class, args);
    }

}
