package com.figure.store.api.controller;

import com.figure.store.domain.service.stock.StockHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping(path ="/stock-histories")
public class StockHistoryController {

    private final StockHistoryService stockHistoryService;

    @Autowired
    public StockHistoryController(StockHistoryService stockHistoryService) {
        this.stockHistoryService = stockHistoryService;
    }

    @GetMapping
    public ResponseEntity<?> getStockHistory(){
        return new ResponseEntity<>(stockHistoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStockById(@PathVariable long id){
        return new ResponseEntity<>(stockHistoryService.findByStockId(id), HttpStatus.OK);
    }
}
