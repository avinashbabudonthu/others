package com.url.to.pdf;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import com.evopdf.HorizontalTextAlign;
import com.evopdf.HtmlToPdfConverter;
import com.evopdf.HtmlToPdfElement;
import com.evopdf.LineElement;
import com.evopdf.PdfFont;
import com.evopdf.PdfPageOrientation;
import com.evopdf.RgbColor;
import com.evopdf.TextElement;

public class URLToPdf {

	@Test
	public void urlToPdf() throws Exception {
		final String outFilePath = "C:/practice-projects/tools/pdf/evo-pdf/output-files/url-to-pdf.pdf";
		byte[] outputPdfBuffer = convert();
		writeBytesToFile(outputPdfBuffer, outFilePath);
	}

	private byte[] convert() throws Exception {
		String serverIP = "127.0.0.1";
		int port = 40001;
		final Boolean drawHeaderLine = Boolean.TRUE;
		final Boolean addPageNumbers = Boolean.TRUE;
		final Boolean drawFooterLine = Boolean.TRUE;

		// create the HTML to PDF converter
		HtmlToPdfConverter htmlToPdfConverter = new HtmlToPdfConverter(serverIP, port);

		// set license key
		htmlToPdfConverter.setLicenseKey("B4mYiJubiJiInoaYiJuZhpmahpGRkZGImg==");

		String urlToConvert = "http://www.evopdf.com";

		drawHeader(htmlToPdfConverter, drawHeaderLine);
		drawFooter(htmlToPdfConverter, addPageNumbers, drawFooterLine);

		byte[] outputPdfBuffer = htmlToPdfConverter.convertUrl(urlToConvert);

		return outputPdfBuffer;
	}

	private void writeBytesToFile(byte[] bytes, String outFilePath) throws Exception {
		// write the bytes into a file
		OutputStream fs = null;
		try {
			fs = new FileOutputStream(outFilePath);
			fs.write(bytes, 0, bytes.length);
		} catch (Exception ex) {
			throw new Exception(
					String.format("Could not write the output file '%1$s' : %2$s", outFilePath, ex.getMessage()));
		} finally {
			if (fs != null)
				fs.close();
		}
	}

	private void drawHeader(HtmlToPdfConverter htmlToPdfConverter, boolean drawHeaderLine) {
		String headerHtmlString = "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>HTML in Header</title>" + "</head>"
				+ "<body style=\"font-family: 'Times New Roman'; font-size: 14px\">" + "<table>" + "<tr>"
				+ "<td style=\"font-weight: bold; color: navy\">HTML in Header</td>" + "</tr>" + "<tr>" + "<td>"
				+ "<a href=\"http://www.evopdf.com\">"
				+ "<img alt=\"Logo Image\" style=\"width: 200px\" src=\"http://www.evopdf.com/images/evopdf_logo.jpg\" /></a>"
				+ "</td>" + "</tr>" + "</table>" + "</body>" + "</html>";

		// Set the header height in points
		htmlToPdfConverter.pdfHeaderOptions().setHeaderHeight(60);

		// Set header background color
		htmlToPdfConverter.pdfHeaderOptions().setHeaderBackColor(RgbColor.WHITE);

		// Create a HTML element to be added in header
		HtmlToPdfElement headerHtml = new HtmlToPdfElement(headerHtmlString, null);

		// Set the HTML element to fit the container height
		headerHtml.setFitHeight(true);

		// Add HTML element to header
		htmlToPdfConverter.pdfHeaderOptions().addElement(headerHtml);

		if (drawHeaderLine) {
			// Calculate the header width based on PDF page size and margins
			boolean portraitOrientation = htmlToPdfConverter.pdfDocumentOptions()
					.pdfPageOrientation() == PdfPageOrientation.Portrait;
			float headerWidth = (portraitOrientation ? htmlToPdfConverter.pdfDocumentOptions().pdfPageSize().width()
					: htmlToPdfConverter.pdfDocumentOptions().pdfPageSize().height())
					- htmlToPdfConverter.pdfDocumentOptions().leftMargin()
					- htmlToPdfConverter.pdfDocumentOptions().rightMargin();

			// Calculate header height
			float headerHeight = htmlToPdfConverter.pdfHeaderOptions().headerHeight();

			// Create a line element for the bottom of the header
			LineElement headerLine = new LineElement(0, headerHeight - 1, headerWidth, headerHeight - 1);

			// Set line color
			headerLine.setForeColor(RgbColor.GRAY);

			// Add line element to the bottom of the header
			htmlToPdfConverter.pdfHeaderOptions().addElement(headerLine);
		}

		htmlToPdfConverter.pdfHeaderOptions().setShowInFirstPage(true);
	}

	private void drawFooter(HtmlToPdfConverter htmlToPdfConverter, boolean addPageNumbers, boolean drawFooterLine) {
		String footerHtmlString = "<!DOCTYPE html>" + "<html>" + "<head>" + "<title>HTML in Footer</title>" + "</head>"
				+ "<body style=\"font-family: 'Times New Roman'; font-size: 14px\">" + "<table>" + "<tr>"
				+ "<td style=\"font-weight: bold; color: green\">HTML in Footer</td>" + "</tr>" + "<tr>" + "<td>"
				+ "<a href=\"http://www.evopdf.com\">"
				+ "<img alt=\"Logo Image\" style=\"width: 200px\" src=\"http://www.evopdf.com/images/evopdf_logo.jpg\" /></a>"
				+ "</td>" + "</tr>" + "</table>" + "</body>" + "</html>";

		// Set the footer height in points
		htmlToPdfConverter.pdfFooterOptions().setFooterHeight(60);

		// Set footer background color
		htmlToPdfConverter.pdfFooterOptions().setFooterBackColor(RgbColor.WHITE_SMOKE);

		// Create a HTML element to be added in footer
		HtmlToPdfElement footerHtml = new HtmlToPdfElement(footerHtmlString, null);

		// Set the HTML element to fit the container height
		footerHtml.setFitHeight(true);

		// Add HTML element to footer
		htmlToPdfConverter.pdfFooterOptions().addElement(footerHtml);

		// Add page numbering
		if (addPageNumbers) {
			// Create a text element with page numbering place holders &p; and &P;
			TextElement footerText = new TextElement(0, 30, "Page &p; of &P;  ",
					new PdfFont("Times New Roman", 10, true));

			// Align the text at the right of the footer
			footerText.setTextAlign(HorizontalTextAlign.Right);

			// Set page numbering text color
			footerText.setForeColor(RgbColor.NAVY);

			// Embed the text element font in PDF
			footerText.setEmbedSysFont(true);

			// Add the text element to footer
			htmlToPdfConverter.pdfFooterOptions().addElement(footerText);
		}

		if (drawFooterLine) {
			// Calculate the footer width based on PDF page size and margins
			boolean portraitOrientation = htmlToPdfConverter.pdfDocumentOptions()
					.pdfPageOrientation() == PdfPageOrientation.Portrait;
			float footerWidth = (portraitOrientation ? htmlToPdfConverter.pdfDocumentOptions().pdfPageSize().width()
					: htmlToPdfConverter.pdfDocumentOptions().pdfPageSize().height())
					- htmlToPdfConverter.pdfDocumentOptions().leftMargin()
					- htmlToPdfConverter.pdfDocumentOptions().rightMargin();

			// Create a line element for the top of the footer
			LineElement footerLine = new LineElement(0, 0, footerWidth, 0);

			// Set line color
			footerLine.setForeColor(RgbColor.GRAY);

			// Add line element to the bottom of the footer
			htmlToPdfConverter.pdfFooterOptions().addElement(footerLine);
		}

		htmlToPdfConverter.pdfFooterOptions().setShowInFirstPage(true);
	}
}
