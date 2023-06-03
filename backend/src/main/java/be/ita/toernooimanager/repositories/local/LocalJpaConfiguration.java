package be.ita.toernooimanager.repositories.local;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "localEntityManagerFactory",
        transactionManagerRef = "localTransactionManager",
        basePackages = {"be.ita.toernooimanager.model.local","be.ita.toernooimanager.repositories.local"}
)
public class LocalJpaConfiguration {
    @Value("${local.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;
    @Value("${local.jpa.properties.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    @Value("${local.jpa.properties.hibernate.show_sql}")
    private String show_sql;
    @Value("${local.jpa.properties.hibernate.globally_quoted_identifiers}")
    private String globally_quoted_identifiers;
    @Bean
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactory(
            @Qualifier("localDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", hibernateDialect);
        //props.put("hibernate.globally_quoted_identifiers", globally_quoted_identifiers);
        return builder
                .dataSource(dataSource)
                .properties(props)
                .packages("be.ita.toernooimanager.model.local")
                .build();
    }

    @Bean
    public PlatformTransactionManager localTransactionManager(
            @Qualifier("localEntityManagerFactory") LocalContainerEntityManagerFactoryBean localEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(localEntityManagerFactory.getObject()));
    }
}