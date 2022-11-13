package com.example.foodrecipeapp.Newsletter.controller;

import com.example.foodrecipeapp.Newsletter.model.Subscriber;
import com.example.foodrecipeapp.Newsletter.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriber")
public class SubscriberController {
    private final SubscriberService subscriberService;

    @PostMapping("/add")
    public void addSubscriber(@RequestBody Subscriber subscriber) {
        subscriberService.addSubscriber(subscriber);
    }

    @PostMapping("/your-dishes")
    public void sendYourDishes(@RequestParam String author, @RequestPart MultipartFile file) throws IOException {
        subscriberService.sendYourDishes(author, file);
    }

}
