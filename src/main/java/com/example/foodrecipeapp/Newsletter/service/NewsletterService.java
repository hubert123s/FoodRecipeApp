package com.example.foodrecipeapp.Newsletter.service;

import com.example.foodrecipeapp.Ingredients.model.Ingredients;
import com.example.foodrecipeapp.Meal.model.Meal;
import com.example.foodrecipeapp.Meal.repository.MealRepository;
import com.example.foodrecipeapp.Meal.service.MealService;
import com.example.foodrecipeapp.Newsletter.ContentGenerator;
import com.example.foodrecipeapp.Newsletter.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.*;
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
    private final ContentGenerator contentGenerator;
    private final SubscriberRepository subscriberRepository;

    private final static String PATHFILE ="ebook.txt";
    private final static String SENDER ="szymanskidawid1205@gmail.com";
    public void sendMail(String text,String subject, List<String> email)
    {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.toArray(new String[0]));
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);

    }
    @Async
   // @Scheduled(fixedRate = 5000)
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
    public void sendEbook (List<String> email)
    {
        String subject = "Ebook";
        String text ="This is Ebook";
        sendWithFile(email,subject,text,subject, contentGenerator.ebookGenerator());

    }

    public void sendWithFile(List<String> email, String subject, String text, String filename, byte[] file)
    {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(email.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(text);

            if(filename != null && file != null){
                ByteArrayResource byteArrayResource = new ByteArrayResource(file);
                helper.addAttachment(filename,byteArrayResource);
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        javaMailSender.send(message);
    }


}
