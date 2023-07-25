package com.example.webapp.controladores;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import com.example.webapp.modelos.Carro;
import com.example.webapp.modelos.Cliente;
import com.example.webapp.repositorios.ClienteRepositorioImpl;
import com.example.webapp.util.EnviarCorreoConArchivo;
import com.example.webapp.util.GenerarPDF;
import com.example.webapp.util.TransferFTP;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/generar-pedido")
public class GenerarPedidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = new String();
        Carro carro = (Carro) req.getSession().getAttribute("carro");
        String nombre = "";

        if(Optional.of(req.getSession().getAttribute("username")).isPresent()){
            nombre = (String) req.getSession().getAttribute("username");
            ClienteRepositorioImpl clienteRepositorio = new ClienteRepositorioImpl();
            List<Cliente> clientes = clienteRepositorio.listar();
            Optional<Cliente> clienteEnSesion = clientes.stream().filter(cliente -> cliente.getNombre().equals(req.getSession().getAttribute("username"))).findFirst();
            if(clienteEnSesion.isPresent()){
                correo = clienteEnSesion.get().getEmail();
            }
        }

        if(!nombre.equals("")) GenerarPDF.save(carro, nombre);
        else GenerarPDF.save(carro, "");

        String host = "smtp.gmail.com"; // Cambiar por el servidor de correo saliente que utilices
        int port = 587; // Cambiar el puerto si es necesario (587 es el puerto estándar para TLS)
        String username = "mariojazael666m@gmail.com"; // Cambiar por tu dirección de correo
        String password = "wnrgrpuovjjfuere"; // Cambiar por tu contraseña de correo

        String destinatario = correo; // Cambiar por la dirección del destinatario
        String asunto = "Resumen de tu pedido";
        String cuerpo = "En este correo encontraras el desglose de tu pedido en el archivo adjunto.";
        String rutaArchivoAdjunto = "C:\\Users\\Owner\\Downloads\\" + nombre + "_pedido.pdf";

        EnviarCorreoConArchivo.enviarCorreoConArchivoAdjunto(host, port, username, password, destinatario, asunto, cuerpo, rutaArchivoAdjunto);
        //TransferFTP.TransferirFTP(nombre);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
