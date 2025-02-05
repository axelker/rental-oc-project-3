package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.MessageDtoRequest;
import com.openclassrooms.rental.dto.response.ResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/messages")
public class MessageRestController {

    @PostMapping("")
    public ResponseEntity<ResponseDto> sendMessage(@RequestBody MessageDtoRequest body) {
        return ResponseEntity.ok(new ResponseDto("Message send with success !"));
    }

}
