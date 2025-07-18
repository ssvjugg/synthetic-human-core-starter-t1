package ru.usernamedrew.synthetichumancorestarter.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.usernamedrew.synthetichumancorestarter.api.Command;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class SyntheticHumanCommand implements Command {
    @NotBlank
    @Size(max = 1000)
    private final String description;

    @NotNull
    private final CommandPriority priority;

    @NotBlank
    @Size(max = 100)
    private final String author;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private final LocalDateTime time;
}
