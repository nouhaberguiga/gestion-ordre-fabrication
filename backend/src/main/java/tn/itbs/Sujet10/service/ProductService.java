package tn.itbs.Sujet10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.Sujet10.entity.Product;
import tn.itbs.Sujet10.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product p) {
        return productRepository.save(p);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product update(Long id, Product p) {
        Product existing = productRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(p.getName());
            existing.setReference(p.getReference());
            existing.setPrice(p.getPrice());
            existing.setQuantityStock(p.getQuantityStock());

            return productRepository.save(existing);
        }

        return null;
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}