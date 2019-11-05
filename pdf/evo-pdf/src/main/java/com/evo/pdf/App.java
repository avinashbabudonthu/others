package com.evo.pdf;

import java.util.Objects;

import com.html.to.pdf.HTMLToPdf;

public class App {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println("Arguments must be passed. 1st = input html file path, 2nd == output pdf file path");
			return;
		}
		Objects.requireNonNull(args[0], "Argument 0 - Input file path cannot be empty");
		Objects.requireNonNull(args[1], "Argument 1 - Output file path cannot be empty");

		final String inputFilePath = args[0];
		final String outputFilePath = args[1] + "\\pdf-" + System.currentTimeMillis() + ".pdf";

		System.out.println(String.format("Started HTML to PDF coversion. Input File=%s", inputFilePath));
		HTMLToPdf htmlToPdf = new HTMLToPdf();
		byte[] outputPdfBuffer = htmlToPdf.convert(inputFilePath);
		htmlToPdf.writeBytesToFile(outputPdfBuffer, outputFilePath);

		System.out.println("PDF Generateed in the location=" + outputFilePath);
	}

}