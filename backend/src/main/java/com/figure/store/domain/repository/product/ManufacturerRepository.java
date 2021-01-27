package com.figure.store.domain.repository.product;

import com.figure.store.domain.model.product.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    boolean existsByName(String name);

    boolean existsById(Long id);
}
