package com.platzi.market_jh.persistence.mapper;

import com.platzi.market_jh.domain.Category;
import com.platzi.market_jh.persistence.entities.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source="idCategoria", target = "categoryId"),
            @Mapping(source="descripcion", target = "category"),
            @Mapping(source="estado", target = "active")
    })
    Category toCategory(Categoria cateogria);

    //En caso de que queramos convertir category (el objeto de dominio) a Categoria (la entidad)

    @InheritInverseConfiguration //Con esta notación, hacemos que el mapping de arriba se aplique pero de manera
    //inversa, es decir, cada @Mapping se va a ejecutar inversamente, categoryId -> idCategoria;
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
