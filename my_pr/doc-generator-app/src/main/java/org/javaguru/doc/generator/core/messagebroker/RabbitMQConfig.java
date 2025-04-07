package org.javaguru.doc.generator.core.messagebroker;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_PROPOSAL_GENERATION = "q.proposal-generation";

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue createProposalPdfGenerationQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(QUEUE_PROPOSAL_GENERATION);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
}
