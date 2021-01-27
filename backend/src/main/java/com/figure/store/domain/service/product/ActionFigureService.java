package com.figure.store.domain.service.product;

import com.figure.store.domain.exception.DomainException;
import com.figure.store.domain.exception.EntityNotFoundException;
import com.figure.store.domain.model.product.ActionFigure;
import com.figure.store.domain.repository.product.ActionFigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author LucasDonizeti
 */

@Service
public class ActionFigureService {
    private ActionFigureRepository actionFigureRepository;
    private CategoryService categoryService;
    private MaterialService materialService;
    private ManufacturerService manufacturerService;

    @Autowired
    public ActionFigureService(ActionFigureRepository actionFigureRepository, CategoryService categoryService,
                               MaterialService materialService, ManufacturerService manufacturerService) {
        this.actionFigureRepository = actionFigureRepository;
        this.categoryService = categoryService;
        this.materialService = materialService;
        this.manufacturerService = manufacturerService;
    }


    public List<ActionFigure> findAll() {
        return actionFigureRepository.findAll();
    }

    public ActionFigure getById(Long id) {
        if (actionFigureRepository.existsById(id))
            return actionFigureRepository.findById(id).get();
        throw new EntityNotFoundException("ACtion Figure não encontrado");
    }

    public ActionFigure save(ActionFigure actionFigure) {
        if (actionFigureRepository.existsByName(actionFigure.getName()))
            throw new DomainException("Já existe uma ActionFigure cadastrado com esse nome.");

        actionFigure.getCategories().forEach(category -> {
            if (!categoryService.exists(category))
                throw new EntityNotFoundException("Categoria relacionada id{" + category.getId() + "} não existe");
        });

        if (!manufacturerService.exists(actionFigure.getManufacturer()))
            throw new EntityNotFoundException("Fornecedor relacionado não existe");

        actionFigure.getMaterials().forEach(material -> {
            if (!materialService.exists(material))
                throw new EntityNotFoundException("Material relacionada id{" + material.getId() + "} não existe");
        });

        return actionFigureRepository.save(actionFigure);
    }

    public void removeById(Long id) {
        if (!actionFigureRepository.existsById(id))
            throw new EntityNotFoundException("Action Figure não existe");

        actionFigureRepository.delete(actionFigureRepository.findById(id).get());
    }

    public ActionFigure updateActionFigures(ActionFigure actionFigure) {
        if (!actionFigureRepository.existsById(actionFigure.getId()))
            throw new EntityNotFoundException("Action figure não existe");

        actionFigure.getCategories().forEach(category -> {
            if (!categoryService.exists(category))
                throw new EntityNotFoundException("Categoria relacionada id{" + category.getId() + "} não existe");
        });

        if (!manufacturerService.exists(actionFigure.getManufacturer()))
            throw new EntityNotFoundException("Fornecedor relacionado não existe");

        actionFigure.getMaterials().forEach(material -> {
            if (!materialService.exists(material))
                throw new EntityNotFoundException("Categoria relacionada id{" + material.getId() + "} não existe");
        });

        return actionFigureRepository.save(actionFigure);
    }

    public List<ActionFigure> findByCategoryId(long categoryId){
        return actionFigureRepository.findByCategoriesId(categoryId);
    }

}
