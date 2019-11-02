package com.html.with.js;

import com.pdf.crowd.utils.Constants;
import com.pdfcrowd.Pdfcrowd;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ConvertHtmlWithJavascript {

    @Test
    public void convertHtmlFileWithJavascript(){
        log.info("Converting html to pdf");
        final String inputOutputFileName = "html-with-javascript";
        final String localHtmlFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/input-files/"+inputOutputFileName+".html";
        final String pdfFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/output-files/"+inputOutputFileName+".pdf";

        try {
            // create the API client instance
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient(Constants.un, Constants.key);
            client.setOnLoadJavascript("document.getElementById('date').innerHTML = Date()");

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

    @Test
    public void convertZipFiletoPdf(){
        log.info("Converting html to pdf");
        final String inputOutputFileName = "html-with-javascript";
        final String localHtmlFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/input-files/"+inputOutputFileName+".html";
        final String pdfFilePath = "C:/practice-projects/tools/pdf/pdf-crowd/output-files/"+inputOutputFileName+".pdf";

        try {
            // create the API client instance
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient(Constants.un, Constants.key);
            client.setOnLoadJavascript("document.getElementById('date').innerHTML = Date()");

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

    @Test
    public void convertHtmlWithReferenceToExternalJSFile(){
        log.info("Converting html to pdf");
        final List<String> jsFilesList = Arrays.asList("slr-reports.min-2.js");

        final String inputHtmlName = "ESLRtest";
        final String inputFilesPath = "C:/supports/bhavana/slr-test/slr-test/";
        final String outputFilesPath = "C:/practice-projects/tools/pdf/pdf-crowd/output-files/";

        final String localHtmlFilePath = inputFilesPath + inputHtmlName + ".html";
        final String pdfFilePath = outputFilesPath + inputHtmlName + ".pdf";

        try {

            StringBuilder totalJs = new StringBuilder();

            for (String jsFile : jsFilesList){
                String file = inputFilesPath+jsFile;
                log.info("reading the file={}", file);
                String jsFileContent = readFile(file);
                totalJs.append(jsFileContent);
            }

            // create the API client instance
            Pdfcrowd.HtmlToPdfClient client = new Pdfcrowd.HtmlToPdfClient(Constants.un, Constants.key);
            client.setOnLoadJavascript(totalJs.toString());
//            client.setCustomJavascript(totalJs.toString());


            // run the conversion and write the result to a file
            client.convertFileToFile(localHtmlFilePath, pdfFilePath);

            log.info("pdf generated successfully");
        }
        catch(Pdfcrowd.Error e) {
            log.error("error while generating pdf",e);
        }
        catch(Exception e) {
            log.error("exception while generating pdf",e);
        }
    }

    private String readFile(String file) throws Exception{

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = Files
                .newBufferedReader(Paths.get(file).toAbsolutePath())) {
            stringBuilder.append(bufferedReader.readLine());
        }catch(Exception e){
            log.error("exception while reading file", e);
            throw e;
        }

        return stringBuilder.toString();
    }
}
