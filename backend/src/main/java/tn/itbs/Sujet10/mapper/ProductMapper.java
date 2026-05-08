package tn.itbs.Sujet10.mapper;

import org.springframework.stereotype.Component;
import tn.itbs.Sujet10.dto.ProductDTO;
import tn.itbs.Sujet10.entity.Product;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        if (product == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setType(product.getType());
        dto.setReference(product.getReference());
        dto.setPrice(product.getPrice());
        dto.setQuantityStock(product.getQuantityStock());
        dto.setSupplier(product.getSupplier());
        return dto;
    }

    public Product fromDto(ProductDTO dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setType(dto.getType());
        product.setReference(dto.getReference());
        product.setPrice(dto.getPrice());
        product.setQuantityStock(dto.getQuantityStock());
        product.setSupplier(dto.getSupplier());
        return product;
    }

    public List<ProductDTO> toListDto(List<Product> products) {
        return products.stream().map(this::toDto).collect(Collectors.toList());
    }
}