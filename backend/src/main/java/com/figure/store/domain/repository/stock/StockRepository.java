package com.figure.store.domain.repository.stock;

import com.figure.store.domain.model.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    boolean existsByActionFigureId(Long actionFigureId);

}
