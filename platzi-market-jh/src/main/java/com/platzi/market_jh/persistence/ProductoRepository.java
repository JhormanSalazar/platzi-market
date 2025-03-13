package com.platzi.market_jh.persistence;

import com.platzi.market_jh.domain.Product;
import com.platzi.market_jh.domain.repository.ProductRepository;
import com.platzi.market_jh.persistence.crud.ProductoCrudRepository;
import com.platzi.market_jh.persistence.entities.Producto;
import com.platzi.market_jh.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Asi le indicamos a spring que esta clase interactua con la Base de datos;
// @Component tambien es valido, pero es una generalizacion, asi que es mejor usar @Repository ya que le decimos el tipo
// de componente que és.
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos =  productoCrudRepository.findByIdCategoriaOrderByNomreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return  productoCrudRepository.findByIdProducto(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return  mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public void delete(int idProduct){
        productoCrudRepository.deleteById(idProduct);
    }

    public List<Producto> getByName(String nombre){
        return productoCrudRepository.findByNombreAndOrderByCantidadStock(nombre);
    }

}
