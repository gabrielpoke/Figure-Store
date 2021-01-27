package com.figure.store.domain.service.stock;

import com.figure.store.domain.exception.EntityNotFoundException;
import com.figure.store.domain.model.stock.StockHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.figure.store.domain.exception.DomainException;
import com.figure.store.domain.model.stock.Stock;
import com.figure.store.domain.repository.stock.StockRepository;

import java.util.Collection;

@Service
public class StockService {
	
	private StockRepository stockRepository;
	private StockHistoryService stockHistoryService;

	@Autowired
	public StockService(StockRepository stockRepository, StockHistoryService stockHistoryService) {
		this.stockRepository = stockRepository;
		this.stockHistoryService = stockHistoryService;
	}

	public Collection<Stock> findAll() {
		return stockRepository.findAll();
	}

	public Stock findById(Long id) {
		checkIfIdNotExists(id);
		return stockRepository.findById(id).get();
	}

	public Stock save(Stock stock){
		Long actionFigureId = stock.getActionFigure().getId();
		boolean existingStock = stockRepository.existsByActionFigureId(actionFigureId);

		if(existingStock){
			throw new DomainException("Produto já existente no stock");
		}

		Stock stockSaved = stockRepository.save(stock);
		stockHistoryRegister(stockSaved);

		return stockSaved;
	}
	
	public Stock updateStock(Stock stock){
		checkIfIdNotExists(stock.getId());

		Stock stockSaved = stockRepository.save(stock);
		stockHistoryRegister(stockSaved);

		return stockSaved;
	}

	private void checkIfIdNotExists(Long id) {
		if (!stockRepository.existsById(id)) {
			throw new EntityNotFoundException("Estoque não encontrado");
		}
	}

	private void stockHistoryRegister(Stock stock){
		StockHistory stockHistory=new StockHistory();
		stockHistory.setStock(stock);
		stockHistory.setAmount(stock.getAmount());
		stockHistoryService.save(stockHistory);
	}
}
