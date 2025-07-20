package ru.usernamedrew.synthetichumancorestarter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.*;

@Configuration
@EnableAspectJAutoProxy
public class CommandProcessorImplConfig {
    @Value("${command.queue.capacity:100}")
    private int capacity;

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(capacity));
    }
}
