package com.platzi.market_jh.domain.service;

import com.platzi.market_jh.domain.Product;
import com.platzi.market_jh.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    //Desde spring 4.3 no es necesario usar Autowired si solo tienes 1 constructor, pero lo puedes dejar para que sea mas explicito .
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        if (product.getProductId() != null) {
            throw new IllegalArgumentException("No se debe enviar ID al crear un producto");
        }
        return productRepository.save(product);
    }

    public boolean delete(Long productId) {
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }
}
