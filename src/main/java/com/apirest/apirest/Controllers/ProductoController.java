package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepositoriy;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositoriy productoRepositoriy;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoRepositoriy.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepositoriy.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el ID: " + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepositoriy.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto producto = productoRepositoriy.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el ID: " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepositoriy.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id) {
        Producto producto = productoRepositoriy.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el ID: " + id));

                productoRepositoriy.delete(producto);

                return "El producto con ID: " + id + " ha sido borrado correctamente";
    }

}
