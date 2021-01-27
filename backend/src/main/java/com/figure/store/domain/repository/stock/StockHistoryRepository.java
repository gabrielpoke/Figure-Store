package com.figure.store.domain.repository.stock;

import com.figure.store.domain.model.stock.Stock;
import com.figure.store.domain.model.stock.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {
    List<StockHistory> findByStock(Stock stock);
}
