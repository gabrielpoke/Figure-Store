package com.figure.store.domain.repository.product;

import com.figure.store.domain.model.product.ActionFigure;
import com.figure.store.domain.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionFigureRepository extends JpaRepository<ActionFigure, Long> {
    Boolean existsByName(String name);
    List<ActionFigure> findByCategoriesId(long categoriesId);
}
