package com.example.foodrecipeapp.newsletter.controller;

import com.example.foodrecipeapp.exception.DuplicatedEmailException;
import com.example.foodrecipeapp.newsletter.model.Subscriber;
import com.example.foodrecipeapp.newsletter.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriber")
public class SubscriberController {
    private final SubscriberService subscriberService;

    @PostMapping("/add")
    Subscriber addSubscriber(@RequestBody Subscriber subscriber) throws DuplicatedEmailException {
        return subscriberService.addSubscriber(subscriber);
    }

    @PostMapping("/your-dishes")
    void sendYourDishes(@RequestParam String author, @RequestPart MultipartFile file) throws IOException {
        subscriberService.sendYourDishes(author, file);
    }

}
