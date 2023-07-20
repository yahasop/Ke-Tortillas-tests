package com.example.webapp.util;

import com.example.webapp.modelos.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImp implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"Tortilla tradicional","alimentos", 35),
                new Producto(2L, "Tortilla para taco", "alimentos", 40),
                new Producto(3L, "Tortilla sobaquera", "alimentos", 120));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(producto -> producto.getId().equals(id)).findAny();
    }
}
