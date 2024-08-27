package br.com.skillswap.AuthService.messaging;

import br.com.skillswap.common.dto.RabbitProfileDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Enviar dados de perfil
    public void sendProfileData(RabbitProfileDTO profile) {
        rabbitTemplate.convertAndSend("profileServiceExchange", "profileServiceRoutingKey", profile);
    }
}
