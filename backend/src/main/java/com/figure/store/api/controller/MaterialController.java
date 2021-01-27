package com.figure.store.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.figure.store.domain.model.product.Material;
import com.figure.store.domain.service.product.MaterialService;

/**
 * author Gabriel Victor
 */

@RestController
@RequestMapping(path = "/materials")
public class MaterialController {

	private final MaterialService materialService;

	@Autowired
	public MaterialController(MaterialService materialService){
		this.materialService = materialService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Material save(@Valid @RequestBody Material material){
		return materialService.save(material);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Material> listAll(){
		return materialService.listAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> listById(@PathVariable("id") Long id){
		return new ResponseEntity<>(materialService.listById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		materialService.removeById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateMaterial(@Valid @RequestBody Material material) {
		return new ResponseEntity<>(materialService.updateMaterials(material), HttpStatus.OK);
	}
}
