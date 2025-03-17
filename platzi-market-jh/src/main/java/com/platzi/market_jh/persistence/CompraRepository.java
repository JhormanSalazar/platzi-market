package com.platzi.market_jh.persistence;

import com.platzi.market_jh.domain.Purchase;
import com.platzi.market_jh.domain.repository.PurchaseRepository;
import com.platzi.market_jh.persistence.crud.CompraCrudRepository;
import com.platzi.market_jh.persistence.entities.Compra;
import com.platzi.market_jh.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;
    private final PurchaseMapper mapper;

    @Autowired
    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
