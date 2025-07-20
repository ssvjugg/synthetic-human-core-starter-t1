package ru.usernamedrew.synthetichumancorestarter.monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@AllArgsConstructor
public class CommandMetrics {
    private final MeterRegistry meterRegistry;
    private final ConcurrentMap<String, Counter> authorCounters = new ConcurrentHashMap<>();
    private final BlockingQueue<Runnable> commandQueue;

    @PostConstruct
    public void init() {
        bindToQueue(commandQueue);
    }

    public void bindToQueue(BlockingQueue<Runnable> commandQueue) {
        // Метрика размера очереди
        Gauge.builder("android.command.queue.size", commandQueue,
                        BlockingQueue::size)
                .description("Current command queue size")
                .register(meterRegistry);
    }

    public void incrementAuthorCommandCount(String author) {
        authorCounters.computeIfAbsent(author,
                        a -> Counter.builder("android.command.completed")
                                .tag("author", a)
                                .description("Number of completed commands by author")
                                .register(meterRegistry))
                .increment();
    }
}
