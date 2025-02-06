/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Pdf;
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
import java.util.stream.Stream;

/**
 *
 * @author aayush
 */
public class PdfService {
    
    Pdf pdf;
    public PdfService(Pdf pdf){
    
        this.pdf = pdf;
        
    }
    
    public void generatePdf() throws FileNotFoundException, DocumentException, URISyntaxException, IOException{
        
        Path path = Paths.get(ClassLoader.getSystemResource("pictures/logo.png").toURI());
        URL resource = getClass().getClassLoader().getResource("pictures/logo.png");
        
        System.out.println(resource);
        System.out.println(path);
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdf.getName()+".pdf"));

        document.open();
        
        Font heading = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
        Chunk title = new Chunk("Team75 Management", heading);
        
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Pay Slip of "+pdf.getName(), font);

        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);
        addRows(table);
        
        
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
        Stream.of("", "")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
      
    }
    
    private void addRows(PdfPTable table) {
        table.addCell("Employee ID: ");
        table.addCell(this.pdf.getId().toString());
        table.addCell("Name: ");
        table.addCell(this.pdf.getName());
        table.addCell("Email: ");
        table.addCell(pdf.getEmail());
        table.addCell("Salary: ");
        table.addCell(pdf.getSalary());
        table.addCell("Allowances: ");
        table.addCell(pdf.getAllowances());
        table.addCell("Deductions: ");
        table.addCell(pdf.getDeductions());
        table.addCell("total: ");
        table.addCell(pdf.getTotal());
    } 
    
    
}
