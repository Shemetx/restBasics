package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("com.epam.esm")
@PropertySource("classpath:application.properties")
@ComponentScan
public class PersistenceConfig implements WebMvcConfigurer {


    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
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

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
