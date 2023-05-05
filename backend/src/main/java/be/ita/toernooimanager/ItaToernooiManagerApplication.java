package be.ita.toernooimanager;

import be.ita.toernooimanager.utils.Exclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class ItaToernooiManagerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ItaToernooiManagerApplication.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        app.run(args);
        //SpringApplication.run(ItaToernooiManagerApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Primary
    public JdbcTemplate shiaiJdbcTemplate(@Qualifier("shiaiDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate localJdbcTemplate(@Qualifier("localDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ExclusionStrategy exclusionStrategy(){
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getAnnotation(Exclude.class) != null;
            }
        };
    }
}
