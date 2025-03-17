package com.platzi.market_jh.web.controller;

import com.platzi.market_jh.domain.Purchase;
import com.platzi.market_jh.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId){
        return purchaseService.getByClient(clientId).filter(Predicate.not(List::isEmpty))
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        Purchase savedPurchase = purchaseService.save(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }
}
