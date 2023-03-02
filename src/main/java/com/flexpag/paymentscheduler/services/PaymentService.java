package com.flexpag.paymentscheduler.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.flexpag.paymentscheduler.entities.Payment;
import com.flexpag.paymentscheduler.enums.PaymentStatus;
import com.flexpag.paymentscheduler.repositories.PaymentRepository;
import com.flexpag.paymentscheduler.services.exceptions.ResourceForbiddenException;
import com.flexpag.paymentscheduler.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository repository;

	// Lista Todos os Pagamentos
	public List<Payment> findAll() {
		return repository.findAll();
	}

	// Encontra Pagamento por Id (Isso inclui a consulta do status)
	public Payment findById(Long id) {
		Optional<Payment> payment = repository.findById(id);
		return payment.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// Cria Novo Pagamento
	public Payment create(Payment obj) {
		return repository.save(obj);
	}

	// Caso o pagamento ainda não foi realizado o usuário pode:

	// 1) Exclui Pagamento
	public void delete(Long id) {
		try {
			Payment p = repository.getReferenceById(id);
			if (p.getStatus() == PaymentStatus.PENDING) {

				repository.deleteById(p.getId());
			}
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceForbiddenException(id);
		}
	}

	// 2) Editar Status e/ou Data e Hora (e valor)
	public Payment update(Long id, Payment obj) {
		try {
			Payment p = repository.getReferenceById(id);
			if (p.getStatus() == PaymentStatus.PENDING) {
				p.setValuePayment(obj.getValuePayment());
				p.setStatus(obj.getStatus());
				p.setDate(obj.getDate());
			}
			return repository.save(p);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceForbiddenException(id);
		}
	}

}
