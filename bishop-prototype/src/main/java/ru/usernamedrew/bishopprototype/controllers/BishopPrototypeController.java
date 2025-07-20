package ru.usernamedrew.bishopprototype.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.usernamedrew.synthetichumancorestarter.api.Command;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;

@RestController
@AllArgsConstructor
@RequestMapping("/synthetichuman/api/commands")
public class BishopPrototypeController {
    private final CommandProcessor commandProcessor;

    @PostMapping("send")
    public ResponseEntity<String> sendMessage(@RequestBody Command command) {
        commandProcessor.processCommand(command);
        return ResponseEntity.ok().build();
    }
}
