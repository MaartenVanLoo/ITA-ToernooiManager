package be.ita.toernooimanager.repositories.shiai;

import be.ita.toernooimanager.model.shiai.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "shiaiEntityManagerFactory",
        transactionManagerRef = "shiaiTransactionManager",
        basePackages = {"be.ita.toernooimanager.model.shiai","be.ita.toernooimanager.repositories.shiai"}
)
public class ShiaiJpaConfiguration {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean shiaiEntityManagerFactory(
            @Qualifier("shiaiDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages("be.ita.toernooimanager.model.shiai")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager shiaiTransactionManager(
            @Qualifier("shiaiEntityManagerFactory") LocalContainerEntityManagerFactoryBean shiaiEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(shiaiEntityManagerFactory.getObject()));
    }
}
