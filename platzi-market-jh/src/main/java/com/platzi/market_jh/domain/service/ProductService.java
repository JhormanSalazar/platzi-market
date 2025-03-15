package com.platzi.market_jh.domain.service;

import com.platzi.market_jh.domain.Product;
import com.platzi.market_jh.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired //Desde spring 4.3 no es necesario usar Autowired si solo tienes 1 constructor, pero lo puedes dejar para que sea mas explicito .
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }
}
