package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.dto.response.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/messages")
public class MessageRestController {

    @PostMapping("")
    public ResponseEntity<Response> sendMessage(@RequestBody MessageRequest body) {
        return ResponseEntity.ok(new Response("Message send with success !"));
    }

}
