package com.example.webapp.util;

import com.example.webapp.modelos.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImp implements ProductoService{
    @Override
    public List<Producto> listar() {
        return Arrays.asList(new Producto(1L,"notebook","computacion", 175000),
                new Producto(2L, "mesa escritorio", "oficina", 100),
                new Producto(3L, "teclado mecanico", "computacion", 40000));
    }

    @Override
    public Optional<Producto> porId(Long id) {
        return listar().stream().filter(producto -> producto.getId().equals(id)).findAny();
    }
}
