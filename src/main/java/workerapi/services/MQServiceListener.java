package workerapi.services;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import workerapi.models.Vote;
import workerapi.repository.VoteRepository;

@Component
public class MQServiceListener {
	@Autowired
	VoteRepository voteRepository;

	static final String queueName = "spring-boot";

	@Bean
	public MessageListenerAdapter listenerAdapter() {
		return new MessageListenerAdapter(this, "receiveMessage");
	}
	
	
	@Bean
	SimpleMessageListenerContainer listener(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
		voteRepository.saveAndFlush(new Vote(message));
	}
	

}
