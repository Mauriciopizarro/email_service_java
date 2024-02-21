package com.example.emailservice;

import com.example.emailservice.Services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class Consumer {

    Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.sign.up.email}")
    public  void receive(@Payload String message){
        emailService.sendNewMail(message, "Register successfully", "Thanks for register");
        logger.info("Message received and email sent");
    }

}
