package com.example.foodrecipeapp.Newsletter;

import com.example.foodrecipeapp.Ingredients.Ingredients;
import com.example.foodrecipeapp.Meal.Meal;
import com.example.foodrecipeapp.Meal.MealRepository;
import com.example.foodrecipeapp.Meal.MealService;
import com.example.foodrecipeapp.Meal.dto.MealIngredientsDto;
import com.example.foodrecipeapp.Newsletter.Subscriber.Subscriber;
import com.example.foodrecipeapp.Newsletter.Subscriber.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@EnableScheduling
@RequiredArgsConstructor
@Service
public class NewsletterService {
    @Autowired
    private JavaMailSender javaMailSender;

    private final MealRepository mealRepository;
    private final MealService mealService;
    private final SubscriberRepository subscriberRepository;

    public void sendMail(String text,String subject, List<String> email)
    {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.toArray(new String[0]));
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);

    }
    @Async
    //@Scheduled(fixedRate = 5000)
    @Transactional
    @Scheduled(cron = "0 49 12 * * ?")
    public void dailyNewsletter()
    {
        Long max =mealRepository.getMaxId();
        Long randomnumber =ThreadLocalRandom.current().nextLong(1, max + 1);
        List<String> allEmail = subscriberRepository.findAll()
                .stream()
                .map(email->email.getEmail())
                .collect(Collectors.toList());
        String subject = "daily newsletter";
        sendMail(recipeToEmailTemplate(randomnumber),subject,allEmail);
    }
    public String recipeToEmailTemplate(Long id)
    {
         var ingredients= mealRepository
                 .findById(id)
                 .stream()
                 .map(Meal::getIngredientsList)
                 .flatMap(Collection::stream)
                 .map(Ingredients::emailFormat)
                 .toList();
        String meal = mealRepository
                .findById(id)
                .orElseThrow()
                .emailFormat();
        return meal+ingredients;
    }
    public void sendEbook()
    {

    }

}
