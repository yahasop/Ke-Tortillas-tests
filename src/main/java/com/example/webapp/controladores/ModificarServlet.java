package com.example.webapp.controladores;

import com.example.webapp.modelos.Cliente;
import com.example.webapp.repositorios.ClienteRepositorioImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/modificar-cliente")
public class ModificarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname1 = req.getParameter("surname1");
        String surname2 = req.getParameter("surname2");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Map<String, String> errores = new HashMap<>();

        if(name == null || name.isEmpty()){
            errores.put("name","El nombre es requerido");
        }
        if (password == null || password.isBlank()) {
            errores.put("password","el password no puede ser vacio.");
        }
        if(email == null || !email.contains("@")){
            errores.put("email","el email es requerido debe tener un formato de correo.");
        }
        if(surname1 == null || surname1.isBlank()){
            errores.put("surname1","El primer apellido es requerido.");
        }
        if(surname2 == null || surname2.isBlank()){
            errores.put("surname2","El segundo apellido es requerido.");
        }

        if(errores.isEmpty()) {
            boolean userModified = true;
            long hiddenId = Long.parseLong(req.getParameter("hiddenId"));
            long idUsuario = 1;

            ClienteRepositorioImpl usuarioRepositorio = new ClienteRepositorioImpl();

            List<Cliente> clientes = usuarioRepositorio.listar();

            for(int i = 0; i < clientes.size(); i++){
                if(clientes.get(i).getId() == hiddenId){
                    idUsuario = clientes.get(i).getId();
                }
            }

            Cliente cliente = new Cliente(name, email, surname1, surname2, password, idUsuario, 2L);

            usuarioRepositorio.guardar(cliente);

            req.setAttribute("userModified", userModified);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            boolean userModified = false;
            req.setAttribute("errores", errores);
            req.setAttribute("userModified", userModified);
            getServletContext().getRequestDispatcher("/registro.jsp").forward(req, resp);
        }
    }
}
