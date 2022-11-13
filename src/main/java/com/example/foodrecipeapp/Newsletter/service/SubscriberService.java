package com.example.foodrecipeapp.Newsletter.service;

import com.example.foodrecipeapp.Newsletter.SubscriberRepository;
import com.example.foodrecipeapp.Newsletter.model.Subscriber;
import com.example.foodrecipeapp.Newsletter.TypeNewsletter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final NewsletterService newsletterService;

    public void addSubscriber(Subscriber subscriber)
    {
        String subject = "Subscription confirmation";
        String text = "You have been successfully added to the subscriber base";
        subscriberRepository.save(subscriber);
        newsletterService.sendMail(text,subject, List.of(subscriber.getEmail()));

        if(subscriber.getTypeNewsletter().equals(TypeNewsletter.ebook))
        {
            newsletterService.sendEbook(List.of(subscriber.getEmail()));
        }
    }

    public void sendYourDishes(String author, MultipartFile file) throws IOException {
        String subject ="Dishes from "+author;
        String text ="greetings " +author;
        newsletterService.sendWithFile(List.of("hubert.antoniszyn98@gmail.com",author),subject,text,file.getOriginalFilename(), file.getBytes());
    }
}
