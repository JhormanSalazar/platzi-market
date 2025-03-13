package com.platzi.market_jh.persistence;

import com.platzi.market_jh.persistence.crud.ProductoCrudRepository;
import com.platzi.market_jh.persistence.entities.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Asi le indicamos a spring que esta clase interactua con la Base de datos;
// @Component tambien es valido, pero es una generalizacion, asi que es mejor usar @Repository ya que le decimos el tipo
// de componente que és.
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
    return productoCrudRepository.findByIdCategoriaOrderByNomreAsc(idCategoria);
    }

    Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public List<Producto> getByName(String nombre){
        return productoCrudRepository.findByNombreAndOrderByCantidadStock(nombre);
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findByIdProducto(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
