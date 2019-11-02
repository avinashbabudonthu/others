package com.pdf2dom;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

@Slf4j
public class PDFHTMLConverter {

    @Test
    public void pdfToHtml() {
        try (Writer writer = new PrintWriter("C:\\practice-projects\\tools\\pdf\\pdf-2-dom\\files\\pdf-to-html.html", "utf-8");){
            final String fileName = getClass().getClassLoader().getResource("git-cheat-sheet-education.pdf").getPath();
            PDDocument pdfDocument = PDDocument.load(new File(fileName));
            new PDFDomTree().writeText(pdfDocument, writer);

            log.info("pdf to html conversion completed");
        } catch (IOException | ParserConfigurationException e) {
            log.info("Exception while creating html from pdf",e);
        }
    }

    @Test
    public void htmlToPdf(){
        try {
            final String fileName = getClass().getClassLoader().getResource("git-cheat-sheet-education.html").getPath();
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("C:\\practice-projects\\tools\\pdf\\pdf-2-dom\\files\\html-to-pdf.pdf"));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(fileName));
            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error creating pdf from html", e);
        }
    }
}
