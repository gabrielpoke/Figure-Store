package com.figure.store.domain.service.product;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.figure.store.domain.exception.EntityNotFoundException;
import com.figure.store.domain.model.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.figure.store.domain.exception.DomainException;
import com.figure.store.domain.model.product.Material;
import com.figure.store.domain.repository.product.MaterialRepository;

@Service
public class MaterialService {

	private MaterialRepository materialRepository;

	@Autowired
	public MaterialService(MaterialRepository materialRepository){
		this.materialRepository = materialRepository;
	}

	public Material save(Material material){

		boolean existingMaterial = materialRepository.existsByName(material.getName());

		if(existingMaterial){
			throw new DomainException("Material já existente cadastrado");
		}

		return materialRepository.save(material);
	}

	public List<Material> listAll(){

		return materialRepository.findAll();
	}

	public Material listById(Long id){

		if(!materialRepository.findById(id).isPresent())
			throw new EntityNotFoundException("Material não existe");

		return materialRepository.findById(id).get();

	}

	public void removeById(Long id){

		if(!materialRepository.findById(id).isPresent())
			throw new EntityNotFoundException("Material não existe");

		materialRepository.delete(materialRepository.findById(id).get());
	}

	public Material updateMaterials(Material material){

		if(!materialRepository.findById(material.getId()).isPresent())
			throw new EntityNotFoundException("Material não existe");

		return materialRepository.save(material);
	}

	public Boolean exists(Material material){
		return materialRepository.existsById(material.getId());
	}
}
