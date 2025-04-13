package dev.danvega.mcp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.danvega.mcp.service.MessageService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public String getMessage() {
        return "Hello, World!";
    }

    @PostMapping("/message")
    public String postMessage(@RequestBody String message) {
        return messageService.processMessage(message);
    }
}
