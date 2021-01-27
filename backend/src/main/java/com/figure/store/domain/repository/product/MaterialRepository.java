package com.figure.store.domain.repository.product;

import com.figure.store.domain.model.product.Material;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Boolean existsByName(String name);
}
