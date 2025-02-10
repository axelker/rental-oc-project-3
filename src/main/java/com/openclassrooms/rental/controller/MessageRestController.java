package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.dto.response.Response;
import com.openclassrooms.rental.mapper.MessageMapper;
import com.openclassrooms.rental.service.command.MessageCommandService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/messages")
public class MessageRestController {
    private final MessageCommandService messageCommandService;
    private final MessageMapper messageMapper;

    public MessageRestController(MessageCommandService messageCommandService, MessageMapper messageMapper) {
        this.messageCommandService = messageCommandService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("")
    public ResponseEntity<Response> sendMessage(@RequestBody MessageRequest body) {
        this.messageCommandService.createMessage(messageMapper.toEntity(body));
        return ResponseEntity.ok(Response.builder().message("Message send with success !").build());
    }

}
