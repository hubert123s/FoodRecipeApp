package com.example.foodrecipeapp.newsletter.service;

import com.example.foodrecipeapp.meal.repository.MealRepository;
import com.example.foodrecipeapp.meal.service.MealService;
import com.example.foodrecipeapp.newsletter.ContentGenerator;
import com.example.foodrecipeapp.newsletter.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@EnableScheduling
@RequiredArgsConstructor
@Service
public class NewsletterService {

    private final JavaMailSender javaMailSender;

    private final MealRepository mealRepository;
    private final MealService mealService;
    private final ContentGenerator contentGenerator;
    private final SubscriberRepository subscriberRepository;

    private static final String PATHFILE = "ebook.txt";
    private static final String SENDER = "szymanskidawid1205@gmail.com";

    public void sendMail(String text, String subject, List<String> email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.toArray(new String[0]));
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);

    }

    @Async
    // @Scheduled(fixedRate = 5000)
    @Transactional
    @Scheduled(cron = "0 00 10 * * ?")
    public void dailyNewsletter() {
        Long max = mealRepository.getMaxId();
        Long randomnumber = ThreadLocalRandom.current().nextLong(1, max + 1);
        List<String> allEmail = subscriberRepository.findAll()
                .stream()
                .map(email -> email.getEmail())
                .toList();
        String subject = "daily newsletter";
        sendMail(contentGenerator.recipeToEmailTemplate(randomnumber), subject, allEmail);
    }

    public void sendWithFile(List<String> email, String subject, String text, String filename, byte[] file) {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(SENDER);
            helper.setTo(email.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(text);

            if (filename != null && file != null) {
                ByteArrayResource byteArrayResource = new ByteArrayResource(file);
                helper.addAttachment(filename, byteArrayResource);
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }


}
