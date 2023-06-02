package com.example.demo.controller;

import com.example.core.drivingPort.IMessageService;
import com.example.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MessageController {

    private final IMessageService _messageService;

    public MessageController(IMessageService messageService) {
        _messageService = messageService;
    }

    @RequestMapping(method = { GET, POST }, path = "/hello")
    public Message StartMessage(@RequestParam(name = "name", defaultValue = "anonymous") String name) {
        return _messageService.getMessage(String.format("Hello %s!", name));
    }

    @GetMapping("/bye")
    public Message endMessage(@RequestParam(name = "name", defaultValue = "anonymous") String name) {
        return _messageService.getMessage(String.format("See u later %s!", name));
    }
}
