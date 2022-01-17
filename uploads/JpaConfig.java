package com.example.jdbcTemplate;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.activation.DataSource;

public class JpaConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:C:/Temporal/misPersonas;");
        dataSourceBuilder.username("username");
        dataSourceBuilder.password("password");
        return (DataSource) dataSourceBuilder.build();
    }


}
