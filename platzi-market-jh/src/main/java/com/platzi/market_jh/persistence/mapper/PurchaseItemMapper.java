package com.platzi.market_jh.persistence.mapper;

import com.platzi.market_jh.domain.PurchaseItem;
import com.platzi.market_jh.persistence.entities.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = ProductMapper.class)

public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"), // si se llaman igual, podemos quitar esta linea y el mapeo se hara correctamente
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(source = "productId", target = "id.idProducto"),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProdcto(PurchaseItem purchaseItem);
}
