package com.example.webapp.controladores;

import com.example.webapp.modelos.Cliente;
import com.example.webapp.repositorios.ClienteRepositorioImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/cargarInformacion")
public class CargarInformacionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        Cliente cliente = new Cliente();

        ClienteRepositorioImpl usuarioRepositorio = new ClienteRepositorioImpl();

        List<Cliente> clientes = usuarioRepositorio.listar();

        for(int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).getEmail().equals(email)){
                cliente.setNombre(clientes.get(i).getNombre());
                cliente.setEmail(clientes.get(i).getEmail());
                cliente.setPrimerApellido(clientes.get(i).getPrimerApellido());
                cliente.setSegundoApellido(clientes.get(i).getSegundoApellido());
                cliente.setContrasenia(clientes.get(i).getContrasenia());
                cliente.setId(clientes.get(i).getId());
            }
        }

        System.out.println(cliente.getNombre());

        req.setAttribute("usuario", cliente);
        getServletContext().getRequestDispatcher("/modificar.jsp").forward(req, resp);
    }
}
