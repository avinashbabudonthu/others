package com.local.file.to.pdf;

import com.pdf.crowd.utils.Constants;
import com.pdfcrowd.Pdfcrowd;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class ConvertLocalFileToPdf {

    @Test
    public void localHtmlFileToPdf(){
        log.info("Converting html to pdf");
        final String inputOutputFileName = "html-with-image";
        final String localHtmlFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/input-files/"+inputOutputFileName+".html";
        final String pdfFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/output-files/"+inputOutputFileName+".pdf";

        try {
            // create the API client instance
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient(Constants.un, Constants.key);

            // run the conversion and write the result to a file
            client.convertFileToFile(localHtmlFilePath, pdfFilePath);

            log.info("pdf generated successfully");
        }
        catch(Pdfcrowd.Error e) {
            log.error("error while generating pdf",e);
        }
        catch(IOException e) {
            log.error("exception while generating pdf",e);
        }

    }
}
