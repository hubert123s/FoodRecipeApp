package com.example.foodrecipeapp.Newsletter.Subscriber;

import com.example.foodrecipeapp.Newsletter.Subscriber.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber,Long> {

}
