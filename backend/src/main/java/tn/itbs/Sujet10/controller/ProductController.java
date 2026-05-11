package tn.itbs.Sujet10.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.itbs.Sujet10.entity.Product;
import tn.itbs.Sujet10.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public Product create(@Valid @RequestBody Product p) {
        return productService.save(p);
    }

    // GET ALL
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Product getById( @PathVariable Long id) {
        return productService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Product update( @PathVariable Long id,@Valid  @RequestBody Product p) {
        return productService.update(id, p);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id) {
        productService.delete(id);
    }
}