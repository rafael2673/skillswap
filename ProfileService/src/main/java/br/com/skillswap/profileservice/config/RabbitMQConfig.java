package br.com.skillswap.profileservice.config;

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

    public static final String EXCHANGE_TOKEN = "authServiceExchange";
    public static final String ROUTING_KEY_TOKEN = "authServiceRoutingKey";
    public static final String QUEUE_PROFILE = "profileServiceQueue";
    public static final String EXCHANGE_PROFILE = "profileServiceExchange";
    public static final String ROUTING_KEY_PROFILE = "profileServiceRoutingKey";

    @Bean
    public DirectExchange exchangeToken() {
        return new DirectExchange(EXCHANGE_TOKEN);
    }

    @Bean
    public Queue profileQueue() {
        return new Queue(QUEUE_PROFILE, false);
    }

    @Bean
    public DirectExchange exchangeProfile() {
        return new DirectExchange(EXCHANGE_PROFILE);
    }

    @Bean
    public Binding bindingProfileQueueToExchange() {
        return BindingBuilder.bind(profileQueue()).to(exchangeProfile()).with(ROUTING_KEY_PROFILE);
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
