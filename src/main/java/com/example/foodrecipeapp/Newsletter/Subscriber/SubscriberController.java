package com.example.foodrecipeapp.Newsletter.Subscriber;

import com.example.foodrecipeapp.Newsletter.Subscriber.model.Subscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriber")
public class SubscriberController {
    private final SubscriberService subscriberService;

    @PostMapping("/add")
    public void addSubscriber(@RequestBody Subscriber subscriber)
    {
         subscriberService.addSubscriber(subscriber);
    }

}
