package com.openclassrooms.rental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.rental.dto.request.MessageRequest;
import com.openclassrooms.rental.dto.response.ErrorResponse;
import com.openclassrooms.rental.dto.response.Response;
import com.openclassrooms.rental.service.command.MessageCommandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Messages", description = "Manage user messages on rental")
@ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Unauthorized - Token missing or invalid", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class)))
})
@RestController
@RequestMapping("/messages")
public class MessageRestController {
    private final MessageCommandService messageCommandService;

    public MessageRestController(MessageCommandService messageCommandService) {
        this.messageCommandService = messageCommandService;
    }

    @Operation(summary = "Create a message", description = "Add a new message to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Message created successfully", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input", content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<Response> sendMessage(@Valid @RequestBody MessageRequest body) {
        this.messageCommandService.createMessage(body);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.builder().message("Message sent successfully!").build());

    }

}
