package com.figure.store.api.controller;

import com.figure.store.domain.model.product.Manufacturer;
import com.figure.store.domain.service.product.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(path = "/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Manufacturer> save(@Valid @RequestBody Manufacturer manufacturer) {
        Manufacturer savedManufacturer = manufacturerService.save(manufacturer);
        return ResponseEntity.ok(savedManufacturer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        return ResponseEntity.ok(manufacturer);
    }

    @GetMapping
    public ResponseEntity<Collection<Manufacturer>> findAll() {
        Collection<Manufacturer> manufacturers = manufacturerService.findAll();
        return ResponseEntity.ok(manufacturers);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Manufacturer> update(@Valid @RequestBody Manufacturer manufacturerToUpdate) {
        Manufacturer manufacturerUpdated = manufacturerService.save(manufacturerToUpdate);
        return ResponseEntity.ok(manufacturerUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        manufacturerService.removeById(id);
    }
}
