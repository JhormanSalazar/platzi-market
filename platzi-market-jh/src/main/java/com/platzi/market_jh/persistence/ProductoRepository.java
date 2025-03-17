package com.platzi.market_jh.persistence;

import com.platzi.market_jh.domain.Product;
import com.platzi.market_jh.domain.repository.ProductRepository;
import com.platzi.market_jh.persistence.crud.ProductoCrudRepository;
import com.platzi.market_jh.persistence.entities.Producto;
import com.platzi.market_jh.persistence.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Asi le indicamos a spring que esta clase interactua con la Base de datos;
// @Component tambien es valido, pero es una generalizacion, asi que es mejor usar @Repository ya que le decimos el tipo
// de componente que és.
public class ProductoRepository implements ProductRepository {
    private final ProductoCrudRepository productoCrudRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductoRepository(ProductoCrudRepository productoCrudRepository, ProductMapper mapper){
        this.productoCrudRepository = productoCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(Integer categoryId) {
        List<Producto> productos =  productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return  productoCrudRepository.findByIdProducto(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return  mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(Long idProduct){
        productoCrudRepository.deleteById(idProduct);
    }

    public List<Producto> getByName(String nombre){
        return productoCrudRepository.findByNombreOrderByCantidadStock(nombre);
    }

}
