package com.example.Kafein.Repository;

import com.example.Kafein.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByBarcode(String barcode); //JPA Data budur.

    @Query("Select a from Product a where a.barcode = ?1")
    List<Product> findByBarcodeJPQL(String barcode);

    @Query(value = "Select * from Product a where a.barcode = ?1",nativeQuery = true)
    List<Product> findByBarcodeSQL(String barcode);
}
