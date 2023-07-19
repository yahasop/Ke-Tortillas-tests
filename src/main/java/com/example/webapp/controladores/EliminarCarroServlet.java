package com.example.webapp.controladores;

import com.example.webapp.modelos.Carro;
import com.example.webapp.modelos.ItemCarro;
import com.example.webapp.modelos.Producto;
import com.example.webapp.util.ProductoService;
import com.example.webapp.util.ProductoServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/eliminar-carro")
public class EliminarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        // Optional<Integer> flag = Optional.of(Integer.valueOf(req.getParameter("all")));
        ProductoService service = new ProductoServiceImp();
        Optional<Producto> producto = service.porId(id);
        HttpSession session = req.getSession();
        Carro carro = (Carro) session.getAttribute("carro");
        if (producto.isPresent()) {
            carro.findProducto(String.valueOf(id)).get().setCantidad(carro.findProducto(String.valueOf(id)).get().getCantidad() - 1);
            if(carro.findProducto(String.valueOf(id)).get().getCantidad() == 0){
                carro.removeProducto(String.valueOf(id));
            }
        }
        if (Optional.of(req.getParameter("all")).isPresent()) {
            carro.removeProducto(String.valueOf(id));
        }
        resp.sendRedirect("Pagina_CarritoCompras.jsp");
    }
}

