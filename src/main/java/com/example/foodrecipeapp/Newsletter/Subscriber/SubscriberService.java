package com.example.foodrecipeapp.Newsletter.Subscriber;

import com.example.foodrecipeapp.Newsletter.NewsletterService;
import com.example.foodrecipeapp.Newsletter.Subscriber.model.Subscriber;
import com.example.foodrecipeapp.Newsletter.TypeNewsletter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;
    private final NewsletterService newsletterService;

    void addSubscriber(Subscriber subscriber)
    {
        String subject = "Subscription confirmation";
        String text = "You have been successfully added to the subscriber base";
        subscriberRepository.save(subscriber);
        newsletterService.sendMail(text,subject, List.of(subscriber.getEmail()));
        if(subscriber.getTypeNewsletter().equals(TypeNewsletter.ebook))
        {
            //send ebook
        }
    }
}
