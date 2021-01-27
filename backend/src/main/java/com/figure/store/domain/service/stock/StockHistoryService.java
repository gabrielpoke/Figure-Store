package com.figure.store.domain.service.stock;

import com.figure.store.domain.model.stock.Stock;
import com.figure.store.domain.model.stock.StockHistory;
import com.figure.store.domain.repository.stock.StockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Service
public class StockHistoryService {
    private StockHistoryRepository stockHistoryRepository;

    @Autowired
    public StockHistoryService(StockHistoryRepository stockHistoryRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
    }

    public List<StockHistory> findAll(){
        return stockHistoryRepository.findAll();
    }

    public void save(StockHistory stockHistory){
        stockHistory.setDate(LocalDateTime.now());
        stockHistoryRepository.save(stockHistory);
    }

    public List<StockHistory> findByStockId(long id){
        Stock stock=new Stock();
        stock.setId(id);
        return stockHistoryRepository.findByStock(stock);
    }
}
