package com.batuhan.eindprojectBatuhan.repository;

import com.batuhan.eindprojectBatuhan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);


    //Query voor logica om te filteren en sorteren
    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "ORDER BY " +
            "CASE WHEN :priceOrder = 'asc' THEN p.price END ASC, " +
            "CASE WHEN :priceOrder = 'desc' THEN p.price END DESC")
    List<Product> findByCategoryAndSort(@Param("categoryId") Long categoryId, @Param("priceOrder") String priceOrder);

}
