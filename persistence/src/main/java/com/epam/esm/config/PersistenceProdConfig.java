package com.epam.esm.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Objects;


/**
 * App configuration for persistence module in prod profile
 */
@Configuration
@ComponentScan("com.epam.esm")
@PropertySource("classpath:application.properties")
@Profile("prod")
public class PersistenceProdConfig implements WebMvcConfigurer {

    private Environment environment;

    /**
     * Sets environment which reads application.properties
     *
     * @param environment the environment
     */
    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    /**
     * DataSource for my local database with Hicari coonnection pool
     *
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("jdbc.driver"));
        hikariConfig.setJdbcUrl(environment.getProperty("jdbc.url"));
        hikariConfig.setUsername(environment.getProperty("jdbc.user"));
        hikariConfig.setPassword(environment.getProperty("jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("jdbc.availableConnections"))));
        return new HikariDataSource(hikariConfig);
    }

    /**
     * Jdbc template for local database
     *
     * @return the jdbc template
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /**
     * Transaction manager platform to work with transactions.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
