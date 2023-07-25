package com.example.webapp.util;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import com.example.webapp.modelos.Carro;
import com.example.webapp.modelos.ItemCarro;

import java.util.Iterator;

public class GenerarPDF {
    public static void save(Carro carro, String nombre){
        Document document = new Document();
        Page page = document.getPages().add();

        page.getParagraphs().add(new TextFragment("Hola: " + nombre + "\n\n"));
        page.getParagraphs().add(new TextFragment("Aqui esta el desglose de su pedido: \n\n"));
        page.getParagraphs().add(new TextFragment(("------------------------------\n")));
        Iterator<ItemCarro> iterator =  carro.getItems().listIterator();
        while(iterator.hasNext()){
            ItemCarro itemCarro = iterator.next();
            page.getParagraphs().add(new TextFragment(itemCarro.getProducto().getNombre() + " x " + itemCarro.getCantidad() + "......$" + itemCarro.getProducto().getPrecio() + ".00 c/u"));
        }

        page.getParagraphs().add(new TextFragment(("\n------------------------------\n\n")));

        page.getParagraphs().add(new TextFragment("El gran total de su compra es: $" + carro.getTotal() + ".00"));

        document.save("C:\\Users\\Owner\\Downloads\\" + nombre + "_pedido.pdf");
    }
}
