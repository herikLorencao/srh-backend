package com.srh.api.config;

import com.srh.api.database.DbSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestProfile {
    @Autowired
    private DbSeeder dbSeeder;

    @Bean
    public boolean initializeDatabase() {
        dbSeeder.seed();
        return true;
    }
}
