package com.example.foodrecipeapp.newsletter.repository;

import com.example.foodrecipeapp.newsletter.model.Subscriber;
import com.example.foodrecipeapp.newsletter.model.TypeNewsletter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    boolean existsByName(String name);
    List<Subscriber> findAllByTypeNewsletter(TypeNewsletter typeNewsletter);

}
