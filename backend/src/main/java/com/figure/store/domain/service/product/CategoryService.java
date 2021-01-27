package com.figure.store.domain.service.product;

import com.figure.store.domain.exception.ConstraintViolationException;
import com.figure.store.domain.exception.DomainException;
import com.figure.store.domain.exception.EntityNotFoundException;
import com.figure.store.domain.model.product.Category;
import com.figure.store.domain.repository.product.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author LucasDonizeti
 */
@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        Boolean categoryAlreadyExists = categoryRepository.existsByName(category.getName());

        if (categoryAlreadyExists)
            throw new DomainException("Já existe uma categoria cadastrada com esse nome.");

        return categoryRepository.save(category);
    }

    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        if (!categoryRepository.findById(id).isPresent())
            throw new EntityNotFoundException("Categoria não encontrada");
        return categoryRepository.findById(id).get();
    }

    public void removeById(Long id) {
        if (!categoryRepository.existsById(id))
            throw new EntityNotFoundException("Categoria não encontrada");
        try{
            categoryRepository.delete(categoryRepository.findById(id).get());
        }catch (DataIntegrityViolationException e) {
            throw new ConstraintViolationException("Categoria "+ id + "não pode ser deletada");
        }
    }

    public Category updateCategory(Category category) {
        if (!categoryRepository.findById(category.getId()).isPresent())
            throw new EntityNotFoundException("Categoria não encontrada");
        return categoryRepository.save(category);
    }

    public Boolean exists(Category category){
        return categoryRepository.existsById(category.getId());
    }
}
