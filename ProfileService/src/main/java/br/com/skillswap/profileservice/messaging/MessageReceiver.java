package br.com.skillswap.profileservice.messaging;

import br.com.skillswap.common.dto.RabbitProfileDTO;
import br.com.skillswap.profileservice.service.ProfileService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {


    @Autowired
    private ProfileService profileService;

    // Fila dedicada para receber os dados de perfil
    @RabbitListener(queues = "profileServiceQueue")
    public void receiveMessage(RabbitProfileDTO profile) {
        profileService.createProfile(profile);
    }
}
