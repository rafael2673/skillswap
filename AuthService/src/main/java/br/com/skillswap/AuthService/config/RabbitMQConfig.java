package br.com.skillswap.AuthService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_PROFILE = "profileServiceExchange";
    public static final String ROUTING_KEY_PROFILE = "profileServiceRoutingKey";
    public static final String QUEUE_PROFILE = "profileServiceQueue";
    public static final String EXCHANGE_VALIDATE = "authServiceExchange";
    public static final String ROUTING_KEY_VALIDATE = "authServiceRoutingKey";
    public static final String QUEUE_VALIDATE = "authServiceValidateQueue";

    @Bean
    public DirectExchange exchangeProfile() {
        return new DirectExchange(EXCHANGE_PROFILE);
    }

    @Bean
    public Queue profileQueue() {
        return new Queue(QUEUE_PROFILE, false);
    }

    @Bean
    public DirectExchange exchangeValidate() {
        return new DirectExchange(EXCHANGE_VALIDATE);
    }

    @Bean
    public Queue validateQueue() {
        return new Queue(QUEUE_VALIDATE, false);
    }

    @Bean
    public Binding bindingValidateQueueToExchange() {
        return BindingBuilder.bind(validateQueue()).to(exchangeValidate()).with(ROUTING_KEY_VALIDATE);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
