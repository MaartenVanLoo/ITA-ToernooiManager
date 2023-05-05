package be.ita.toernooimanager.repositories.shiai;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class ShiaiDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.shiai")
    public DataSourceProperties shiaiDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.shiai.hikari")
    public DataSource shiaiDataSource() {
        return shiaiDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
