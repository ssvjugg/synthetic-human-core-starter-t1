package ru.usernamedrew.synthetichumancorestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class EnvPostProcessor implements EnvironmentPostProcessor {
    private final PropertiesPropertySourceLoader propertySourceLoader;

    public EnvPostProcessor() {
        propertySourceLoader = new PropertiesPropertySourceLoader();
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var resource = new ClassPathResource("audit.properties");
        PropertySource<?> propertySource = null;
        try {
            propertySource = propertySourceLoader.load("weyland.audit", resource).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // прочитанные настройки проставляются в настройки окружения Spring'а
        environment.getPropertySources().addLast(propertySource);
    }
}
