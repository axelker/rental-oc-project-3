package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.dto.response.Response;
import com.openclassrooms.rental.service.command.MessageCommandService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/messages")
public class MessageRestController {
    private final MessageCommandService messageCommandService;

    public MessageRestController(MessageCommandService messageCommandService){
        this.messageCommandService = messageCommandService;
    }

    @PostMapping("")
    public ResponseEntity<Response> sendMessage(@RequestBody MessageRequest body) {
        this.messageCommandService.createMessage(body);
        return ResponseEntity.ok(new Response("Message send with success !"));
    }

}
