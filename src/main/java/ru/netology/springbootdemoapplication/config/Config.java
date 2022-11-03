package ru.netology.springbootdemoapplication.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.springbootdemoapplication.profile.DevProfile;
import ru.netology.springbootdemoapplication.profile.ProductionProfile;
import ru.netology.springbootdemoapplication.profile.SystemProfile;

@Configuration
public class Config {
    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }

}
