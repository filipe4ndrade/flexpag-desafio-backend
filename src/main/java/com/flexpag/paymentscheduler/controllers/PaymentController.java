package com.flexpag.paymentscheduler.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

	@Autowired
	PaymentService service;

	// Lista Todos os Pagamentos
	@GetMapping
	public ResponseEntity<List<Payment>> findAll() {
		List<Payment> payments = service.findAll();
		return ResponseEntity.ok().body(payments);
	}

	// Encontra Pagamento por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Payment> findById(@PathVariable Long id) {
		Payment payment = service.findById(id);
		return ResponseEntity.ok().body(payment);
	}

	// Cria Novo Pagamento
	@PostMapping(value = "/create")
	public ResponseEntity<Long> create(@RequestBody Payment payment) {
		payment = service.create(payment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId())
				.toUri();
		return ResponseEntity.created(uri).body(payment.getId());

	}

	// Caso o pagamento ainda não foi realizado o usuário pode:

	// 1) Exclui Pagamento
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// 2) Editar Status e/ou Data e Hora (e valor)
	@PutMapping(value = "/{id}")
	public ResponseEntity<Payment> updateDateOrHour(@PathVariable Long id, @RequestBody Payment payment){
		payment = service.update(id, payment);
		return ResponseEntity.ok().body(payment);
	}
}
