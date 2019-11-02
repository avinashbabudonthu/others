package com.web.page.to.pdf;

import com.pdf.crowd.utils.Constants;
import com.pdfcrowd.Pdfcrowd;
import com.pdfcrowd.PdfcrowdError;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class ConvertWebPageToPdf {

    @Test
    public void createPdf(){
        try{
            final String url = "http://www.example.com";
            final String pdfFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/output-files/example.pdf";

            // create the API client instance
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient(Constants.un, Constants.key);

            // run the conversion and write the result to a file
            client.convertUrlToFile(url,pdfFilePath);

            log.info("pdf generated successfully");
        }catch (PdfcrowdError error){
            log.info("Error while generating pdf", error);
        }catch(IOException e){
            log.error("Exception while generating pdf", e);
        }
    }
}
