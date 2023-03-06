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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// Acessar a documentação swagger: http://localhost:8080/swagger-ui/index.html

/*
Como fazer update ou create pelo JSON:
Deve-se usar apenas dois atributos: valuePayment e date.
Exemplo:	
  {
     "valuePayment": 1500.00,
     "date": "2023-03-06 08:30:20"
   }
*/

@RestController
@RequestMapping(value = "/api/payments")
@Tag(name = "Payments", description = "Endpoints for Managing Payments" )
public class PaymentController {

	@Autowired
	PaymentService service;

	// Lista Todos os Pagamentos
	@GetMapping
	@Operation(summary = "Finds All Payments", description="Finds All Payments")
	public ResponseEntity<List<Payment>> findAll() {
		List<Payment> payments = service.findAll();
		return ResponseEntity.ok().body(payments);
	}

	// Encontra Pagamento por id
	@GetMapping(value = "/{id}")
	@Operation(summary = "Finds a Payment by Id", description ="Finds a Payment by Id" )
	public ResponseEntity<Payment> findById(@PathVariable Long id) {
		Payment payment = service.findById(id);
		return ResponseEntity.ok().body(payment);
	}

	// Cria Novo Pagamento
	@PostMapping(value = "/create")
	@Operation(summary = "Add a new Payments", description="Add a new Payments")
	public ResponseEntity<Long> create(@RequestBody Payment payment) {
		payment = service.create(payment);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(payment.getId())
				.toUri();
		return ResponseEntity.created(uri).body(payment.getId());

	}

	// Caso o pagamento ainda não foi realizado o usuário pode:

	// 1) Exclui Pagamento
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Delete Payment By Id", description= "Delete Payment By Id")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// 2) Editar Data e Hora (e valor)
	@PutMapping(value = "/{id}")
	@Operation(summary = "Update the Payment date and value by Id", description= "Update the Payment date and value by Id")
	public ResponseEntity<Payment> updateDateOrHour(@PathVariable Long id, @RequestBody Payment payment){
		payment = service.update(id, payment);
		return ResponseEntity.ok().body(payment);
	}
	
	// 3) Editar Status
	@PutMapping(value = "/status/{id}")
	@Operation(summary = "Change status 'PENDING to PAID' or 'PAID to PENDING'", description="Change status 'PENDING to PAID' or 'PAID to PENDING'")
	public void makeThePayment(@PathVariable Long id){
		service.updateStatus(id);
	}
}
