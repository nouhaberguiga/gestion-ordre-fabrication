package tn.itbs.Sujet10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.itbs.Sujet10.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}