package com.example.foodrecipeapp.Newsletter.Subscriber;

import com.example.foodrecipeapp.Newsletter.TypeNewsletter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {

}
