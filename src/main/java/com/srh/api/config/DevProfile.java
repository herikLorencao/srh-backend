package com.srh.api.config;

import com.srh.api.database.DbSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevProfile {
    @Autowired
    private DbSeeder dbSeeder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean initializeDatabase() {
        if (strategy.equals("create")) {
            dbSeeder.seed();
            return true;
        }

        return false;
    }
}
