package com.tfa.controller;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfa.dto.Commande;
import com.tfa.dto.EvenementCommande;
import com.tfa.producer.RelationClientProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rl-cl")
@RequiredArgsConstructor
public class RelationClientController {

	private final RelationClientProducer producer;
	
	@PostMapping("/cmd-cl")
	public ResponseEntity<EvenementCommande> envoiecommande(@RequestBody Commande commande) {
		commande.setIdCmd(UUID.randomUUID().toString().toUpperCase());
		int idx = new SecureRandom().nextInt(10);
		EvenementCommande evenementCommande = EvenementCommande.builder()
				.status(idx < 2 ? "Relivraison" : "Remboursement")
				.message("Commande non recu de la part du client")
				.commande(commande)
				.build();
		producer.envoiecommande(evenementCommande);
		
		return new ResponseEntity<EvenementCommande>(evenementCommande, HttpStatus.CREATED);
		
	}
}
