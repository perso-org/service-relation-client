package com.tfa.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.tfa.dto.EvenementCommande;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RelationClientProducer {

	private final RabbitTemplate template;
	
	public void envoiecommande(EvenementCommande commande) {

		if(StringUtils.endsWithIgnoreCase(commande.getStatus(), "Remboursement")) {
			log.info("Envoie de commande pour remboursement {}",commande);
			template.convertAndSend("service-client", "routing-key-rl-cl-001", commande);
			return;
		}
		log.info("Envoie de commande pour relivraison {}",commande);
		template.convertAndSend("service-client", "routing-key-rl-cl-002", commande);
	}
}
