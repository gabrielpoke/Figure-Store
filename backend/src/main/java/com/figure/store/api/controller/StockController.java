package com.figure.store.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.figure.store.domain.model.stock.Stock;
import com.figure.store.domain.service.stock.StockService;

@RestController
@RequestMapping(path ="/stocks")
public class StockController {

	private final StockService stockService;

	@Autowired
	public StockController(StockService stockService){
		this.stockService = stockService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Stock save(@Valid @RequestBody Stock stock){
		return stockService.save(stock);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStock(@Valid @RequestBody Stock stock){
		return new ResponseEntity<>(stockService.updateStock(stock),HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		return ResponseEntity.ok(stockService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Stock> findById(@PathVariable Long id) {
		return ResponseEntity.ok(stockService.findById(id));
	}
}
