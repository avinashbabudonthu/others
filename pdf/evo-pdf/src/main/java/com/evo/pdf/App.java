package com.evo.pdf;

import com.html.to.pdf.HTMLToPdf;

public class App {

	public static void main(String[] args) throws Exception {
		/*final String outputFilePath = "C:/supports/bhavana/slr-test/slr-test/pdf1.pdf";
		final String inputFilePath = "C:/supports/bhavana/slr-test/slr-test/ESLRtest.html";*/

		final String inputFilePath = args[0];
		final String outputFilePath = args[1];

		System.out.println(String.format("Started HTML to PDF coversion. Input File=%s, Output file=%s", inputFilePath,
				outputFilePath));
		HTMLToPdf htmlToPdf = new HTMLToPdf();
		byte[] outputPdfBuffer = htmlToPdf.convert(inputFilePath);
		htmlToPdf.writeBytesToFile(outputPdfBuffer, outputFilePath);
	}

}