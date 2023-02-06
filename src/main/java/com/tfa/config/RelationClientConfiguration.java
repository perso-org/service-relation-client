package com.tfa.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelationClientConfiguration {

	@Bean
	public Exchange exchange() {
		return new TopicExchange("service-client", true, false);
	}
	
	@Bean
	public Queue queueservivePaiement() {
		return new Queue("queue-rl-cl-001",true,false,false);
	}

	@Bean
	public Queue queueserviveLivraison() {
		return new Queue("queue-rl-cl-002",true,false,false);
	}

	@Bean
	public Binding bindingservivePaiement() {
		return BindingBuilder
				.bind(queueservivePaiement())
				.to(exchange())
				.with("routing-key-rl-cl-001")
				.noargs();
	}
	
	@Bean
	public Binding bindingserviveLivraison() {
		return BindingBuilder
				.bind(queueserviveLivraison())
				.to(exchange())
				.with("routing-key-rl-cl-002")
				.noargs();
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory factory ) {
		RabbitTemplate template = new RabbitTemplate(factory);
		template.setMessageConverter(converter());
		return template;
	}
}
