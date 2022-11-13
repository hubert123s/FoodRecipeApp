package com.example.foodrecipeapp.Newsletter;

import com.example.foodrecipeapp.Newsletter.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {

}
