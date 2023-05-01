package com.example.foodrecipeapp.newsletter.service;

import com.example.foodrecipeapp.exception.DuplicatedEmailException;
import com.example.foodrecipeapp.newsletter.repository.SubscriberRepository;
import com.example.foodrecipeapp.newsletter.model.Subscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final NewsletterService newsletterService;
    private static final String SUBJECT ="Subscription confirmation";
    private static final String TEXT = "You have been successfully added to the subscriber base";

    public Subscriber addSubscriber(Subscriber subscriber) throws DuplicatedEmailException {
        if (subscriberRepository.existsByName(subscriber.getName()))
        {
            throw new DuplicatedEmailException(subscriber.getName());
        }
         subscriber = subscriberRepository.save(subscriber);
        newsletterService.sendMail(TEXT, SUBJECT, List.of(subscriber.getEmail()));
        return subscriber;
    }

    public void sendYourDishes(String author, MultipartFile file) throws IOException {
        String subject = "Dishes from " + author;
        String text = "greetings " + author;
        newsletterService.sendWithFile(List.of("hubert.antoniszyn98@gmail.com", author), subject, text, file.getOriginalFilename(), file.getBytes());
    }
}
