package ru.usernamedrew.synthetichumancorestarter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class CommandProcessorImplConfig {
    @Value("${command.queue.capacity:100}")
    private int capacity;

    @Bean
    public BlockingQueue<Runnable> commandQueue() {
        return new LinkedBlockingQueue<>(capacity);
    }

    @Bean
    public ExecutorService executorService(BlockingQueue<Runnable> commandQueue) {
        return new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, commandQueue);
    }
}
