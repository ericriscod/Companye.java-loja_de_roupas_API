package com.onlinestore.modabit.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinestore.modabit.entities.Sale;
import com.onlinestore.modabit.entities.payments.DebitCard;
import com.onlinestore.modabit.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleResource {

	@Autowired
	private SaleService service;
	
	@Transactional(readOnly = true)
	@GetMapping
	public ResponseEntity<List<Sale>> findAll() {
		List<Sale> sales = service.findAll();
		if (!sales.isEmpty()) {
			return ResponseEntity.ok(sales);
		}
		return ResponseEntity.noContent().build();
	}

	@Transactional(readOnly = true)
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Sale>> findAll(Pageable pageable) {
		Page<Sale> sales = service.findAll(pageable);
		if (!sales.isEmpty()) {
			return ResponseEntity.ok(sales);
		}
		return ResponseEntity.noContent().build();
	}
	
	@Transactional(readOnly = true)
	@GetMapping(value = "/search-id/{id}")
	public ResponseEntity<Sale> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id).get());
	}
	
	@Transactional
	@PostMapping(value = "/debitcard")
	public ResponseEntity<Sale> saleDebitCard(@RequestBody DebitCard debitCard){
		LocalDate dateNow = LocalDate.now();
		return ResponseEntity.ok(service.validateSale(debitCard, dateNow));
	}
}
