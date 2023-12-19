package com.myapi.logger.service;

import com.myapi.logger.dto.ProductDTO;
import com.myapi.logger.entity.Product;
import com.myapi.logger.exception.ProductNotFoundException;
import com.myapi.logger.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    public Product addProduct(ProductDTO productDTO) {
//        if (productRepository.existsById(productDTO.getId())) {
//            throw new IllegalArgumentException("Product with ID " + productDTO.getId() + " already exists");
//        }

        return productRepository.save(Product.builder()
                .name(productDTO.getName())
                .expirationDate(productDTO.getExpirationDate())
                .price(productDTO.getPrice())
                .build());
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setExpirationDate(productDTO.getExpirationDate());

        return productRepository.save(existingProduct);
    }
}
