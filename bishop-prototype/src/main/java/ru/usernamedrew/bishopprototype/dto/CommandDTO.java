package ru.usernamedrew.bishopprototype.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.usernamedrew.synthetichumancorestarter.commands.CommandPriority;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CommandDTO {
    private String description;
    private CommandPriority priority;
    private String author;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;
}