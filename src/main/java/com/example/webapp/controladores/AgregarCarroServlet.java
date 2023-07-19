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

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        ProductoService service = new ProductoServiceImp();
        Optional<Producto> producto = service.porId(id);
        if(producto.isPresent()) {
            ItemCarro itemCarro = new ItemCarro(1, producto.get());
            HttpSession session = req.getSession();
            if((session.getAttribute("carro")) != null){
                Carro carro = (Carro) session.getAttribute("carro");
                carro.addItemCarro(itemCarro);
            } else{
                Carro carro = new Carro();
                session.setAttribute("carro", carro);
                carro.addItemCarro(itemCarro);
            }
        }else{
            resp.sendRedirect("index.jsp");
        }
        resp.sendRedirect("Pagina_CarritoCompras.jsp");
    }
}
