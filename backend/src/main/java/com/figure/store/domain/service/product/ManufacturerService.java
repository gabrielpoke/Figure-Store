package com.figure.store.domain.service.product;

import com.figure.store.domain.exception.DomainException;
import com.figure.store.domain.exception.EntityNotFoundException;
import com.figure.store.domain.model.product.Category;
import com.figure.store.domain.model.product.Manufacturer;
import com.figure.store.domain.repository.product.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturer save(Manufacturer manufacturer) {
        if (manufacturer.getId() != null) {
            String manufacturerName = manufacturer.getName();
            boolean manufacturerAlreadyExists = manufacturerRepository.existsByName(manufacturerName);

            if (manufacturerAlreadyExists) {
                throw new DomainException("Já existe um fabricante cadastrado com esse nome");
            }
        }

        return manufacturerRepository.save(manufacturer);
    }

    public Collection<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer findById(Long id) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);

        if(!optionalManufacturer.isPresent()) {
            throwEntityNotFoundException();
        }

        return optionalManufacturer.get();
    }

    public void removeById(Long id) {
        if (!manufacturerRepository.existsById(id)) {
            throwEntityNotFoundException();
        }

        manufacturerRepository.deleteById(id);
    }

    public Boolean exists(Manufacturer manufacturer){
        return manufacturerRepository.existsById(manufacturer.getId());
    }

    private void throwEntityNotFoundException() {
        throw new EntityNotFoundException("Fabricante não encontrado");
    }
}
