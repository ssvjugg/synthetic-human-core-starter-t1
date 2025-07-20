package ru.usernamedrew.bishopprototype.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.usernamedrew.bishopprototype.dto.CommandDTO;
import ru.usernamedrew.synthetichumancorestarter.api.Command;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.commands.SyntheticHumanCommand;

@RestController
@AllArgsConstructor
@RequestMapping("/synthetichuman/api/commands")
public class BishopPrototypeController {
    private final CommandProcessor commandProcessor;

    @PostMapping("send")
    public ResponseEntity<String> sendMessage(@RequestBody CommandDTO commandDTO) {
        Command command = new SyntheticHumanCommand(commandDTO.getDescription(),
                                                    commandDTO.getPriority(),
                                                    commandDTO.getAuthor(),
                                                    commandDTO.getTime());
        commandProcessor.processCommand(command);
        return ResponseEntity.ok().build();
    }
}
