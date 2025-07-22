package br.com.skillswap.AuthService.messaging;
import br.com.skillswap.AuthService.model.User;
import br.com.skillswap.AuthService.service.TokenService;
import br.com.skillswap.AuthService.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RabbitListener(queues = "authServiceValidateQueue")
    public String handleTokenValidation(String token) {

        String email = tokenService.validateToken(token);
        if(userService.findByEmail(email).isPresent()) {
            User user = userService.findByEmail(email).get();
            return user.getId().toString();
        }

        return "";
    }
}

