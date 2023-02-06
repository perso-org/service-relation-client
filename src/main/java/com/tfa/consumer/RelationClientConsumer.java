package com.tfa.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.tfa.dto.EvenementCommande;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RelationClientConsumer {

	@RabbitListener(queues = "queue-retour")
	public void informe_client(EvenementCommande commande) {
		log.warn(commande.getStatus());
		log.warn(commande.getMessage());
		//Envoie de mail au client
	}
}