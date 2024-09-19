package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.database.ConnectionHolder;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackages = "org.example" )

@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public TransactionAspect transactionAspect(ConnectionHolder connectionHolder) {
        return new TransactionAspect(connectionHolder);
    }

    @Bean
    public ConnectionHolder connectionHolder(DataSource dataSource) {
        return new ConnectionHolder(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/postgres")
                .username("postgres")
                .password("wyszka")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

}