package com.platzi.market_jh.domain.repository;

import com.platzi.market_jh.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();

    Optional<List<Purchase>> getByClient(String clientId);

    Purchase save(Purchase purchase);
}
