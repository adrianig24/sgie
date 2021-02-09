package es.sgie.back.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateLazyModule {
    @Bean
    protected Module disableForceLazyFetching() {
            return new Hibernate5Module();
        }
}
