package com.platzi.market_jh.persistence.crud;

import com.platzi.market_jh.persistence.entities.Producto;
import org.springframework.data.jpa.repository.Query; // Por si usamos @Query
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
   // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    //Si usamos la notacion @Query, no tendremos que usar queryMethods, pero en este caso si los vamos a usar,
   // asi que comentamos esa linea.
    List<Producto> findByIdCategoriaOrderByNomreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

    List<Producto> findByNombreAndOrderByCantidadStock(String nombre);

    Optional<Producto> findByIdProducto(int idProducto);
}
