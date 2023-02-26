package com.onlinestore.modabit.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.dto.SaleDTO;
import com.onlinestore.modabit.entities.payments.Boleto;
import com.onlinestore.modabit.entities.payments.CreditCard;
import com.onlinestore.modabit.entities.payments.DebitCard;
import com.onlinestore.modabit.entities.payments.Pix;
import com.onlinestore.modabit.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleResource {

	@Autowired
	private SaleService service;
	
	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<SaleDTO>> findAll() {
		List<SaleDTO> sales = service.findAll();
		if (!sales.isEmpty()) {
			return ResponseEntity.ok(sales);
		}
		return ResponseEntity.noContent().build();
	}
	
	@Transactional(readOnly = true)
	@GetMapping(value = "/search-id/{id}")
	public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@Transactional
	@PostMapping(value = "/debitcard")
	public ResponseEntity<SaleDTO> debitCard(@RequestBody DebitCard debitCard){
		return ResponseEntity.ok(service.validateSale(debitCard));
	}
	
	@Transactional
	@PostMapping(value = "/debitcard/{cpf}")
	public ResponseEntity<SaleDTO> debitCard(@PathVariable String cpf, @RequestBody DebitCard debitCard){
		return ResponseEntity.ok(service.validateSale(cpf, debitCard));
	}
	
	@Transactional
	@PostMapping(value = "/creditcard")
	public ResponseEntity<SaleDTO> creditCard(@RequestBody CreditCard creditcard){
		return ResponseEntity.ok(service.validateSale(creditcard));
	}
	
	@Transactional
	@PostMapping(value = "/creditcard/{cpf}")
	public ResponseEntity<SaleDTO> creditCard(@PathVariable String cpf, @RequestBody CreditCard creditcard){
		return ResponseEntity.ok(service.validateSale(cpf, creditcard));
	}
	
	@Transactional
	@PostMapping(value = "/pix")
	public ResponseEntity<SaleDTO> creditCard(@RequestBody Pix pix){
		return ResponseEntity.ok(service.validateSale(pix));
	}
	
	@Transactional
	@PostMapping(value = "/pix/{cpf}")
	public ResponseEntity<SaleDTO> creditCard(@PathVariable String cpf, @RequestBody Pix pix){
		return ResponseEntity.ok(service.validateSale(cpf, pix));
	}
	
	@Transactional
	@PostMapping(value = "/boleto")
	public ResponseEntity<SaleDTO> creditCard(@RequestBody Boleto boleto){
		return ResponseEntity.ok(service.validateSale(boleto));
	}
	
	@Transactional
	@PostMapping(value = "/boleto/{cpf}")
	public ResponseEntity<Sale> creditCard(@PathVariable String cpf, @RequestBody Boleto boleto){
		return ResponseEntity.ok(service.validateSale(cpf, boleto));
	}
}
