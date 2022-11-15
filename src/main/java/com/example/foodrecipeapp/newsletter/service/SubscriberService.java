package com.example.foodrecipeapp.newsletter.service;

import com.example.foodrecipeapp.exceptions.DuplicatedEmailException;
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

    public Subscriber addSubscriber(Subscriber subscriber) throws DuplicatedEmailException {
        String subject = "Subscription confirmation";
        String text = "You have been successfully added to the subscriber base";
        if (subscriberRepository.existsByName(subscriber.getName()))
        {
            throw new DuplicatedEmailException(subscriber.getName());
        }
         subscriber = subscriberRepository.save(subscriber);
        newsletterService.sendMail(text, subject, List.of(subscriber.getEmail()));
        return subscriber;
    }

    public void sendYourDishes(String author, MultipartFile file) throws IOException {
        String subject = "Dishes from " + author;
        String text = "greetings " + author;
        newsletterService.sendWithFile(List.of("hubert.antoniszyn98@gmail.com", author), subject, text, file.getOriginalFilename(), file.getBytes());
    }
}
