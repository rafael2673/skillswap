package br.com.skillswap.profileservice.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String validateToken(String token) {

        rabbitTemplate.setReplyTimeout(10000);
        String userId = (String) rabbitTemplate.convertSendAndReceive("authServiceExchange", "authServiceRoutingKey", token);
        if (userId == null) {
            throw new RuntimeException("Invalid token or user not found.");
        }
        return userId;
    }

}
