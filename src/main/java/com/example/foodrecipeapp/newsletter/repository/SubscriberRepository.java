package com.example.foodrecipeapp.newsletter.repository;

import com.example.foodrecipeapp.newsletter.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    boolean existsByName(String name);

}
