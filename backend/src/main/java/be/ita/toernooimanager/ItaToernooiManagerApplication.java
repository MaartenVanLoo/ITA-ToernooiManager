package be.ita.toernooimanager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class ItaToernooiManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItaToernooiManagerApplication.class, args);
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
}
