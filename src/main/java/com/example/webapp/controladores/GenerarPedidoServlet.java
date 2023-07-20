package com.example.webapp.controladores;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/generar-pedido")
public class GenerarPedidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Document document = new Document();
        Page page = document.getPages().add();
        page.getParagraphs().add(new TextFragment("Hello world!"));
        document.save("\"C:\\Users\\Owner\\Downloads\"");
    }
}
