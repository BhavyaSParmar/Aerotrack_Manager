/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

/**
 *
 * @author aayush
 */
public class PlaceOrderService {
    
    
    public void generatePdf(ArrayList<String[]> data) throws FileNotFoundException, DocumentException, URISyntaxException, IOException{
        
        System.out.println(data);
        Path path = Paths.get(ClassLoader.getSystemResource("pictures/logo.png").toURI());
        URL resource = getClass().getClassLoader().getResource("pictures/logo.png");
        
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("AircraftPurchaseOrder.pdf"));

        document.open();
        
        Font heading = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
        Chunk title = new Chunk("Team75 Management", heading);
        
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("PDF Generated on:  "+ new Date(), font);

        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        
        for(int i =0 ; i < data.size(); i++){
            addRows(table, data.get(i)); 
       }
        
        
        
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scaleToFit(100, 100);

        
        document.add(title);
        document.add(new Paragraph("\n"));
        document.add(chunk);
        document.add(new Paragraph("\n"));
        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(img);
        document.close();
    
    }
    
    private void addTableHeader(PdfPTable table) {
        Stream.of("Product ID", "Product Name", "Quantity", "Price Per Item", "Price")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
      
    }
    
    private void addRows(PdfPTable table, String[] arr) {
        
        for( int i = 0; i < 5; i++){
            table.addCell(arr[i]);
        }
        
        
    }
    
}
