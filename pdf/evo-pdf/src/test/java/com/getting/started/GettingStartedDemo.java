package com.getting.started;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.evopdf.HorizontalTextAlign;
import com.evopdf.HtmlToPdfConverter;
import com.evopdf.HtmlToPdfElement;
import com.evopdf.LineElement;
import com.evopdf.PdfFont;
import com.evopdf.PdfPageOrientation;
import com.evopdf.PdfPageSize;
import com.evopdf.RgbColor;
import com.evopdf.TextElement;

public class GettingStartedDemo {

	private JFrame frameHtmlToPdf;

	private JTextArea textServerIP;
	private JTextArea textServerPort;

	private JTextArea textServicePassword;

	private JTextArea textHtmlViewerWidth;
	private JTextArea textHtmlViewerHeight;
	private JTextArea textTimeout;
	private JTextArea textConversionDelay;

	private JTextArea textUrl;
	private JTextArea textHtml;
	private JTextArea textBaseUrl;

	private JComboBox comboBoxPageSize;
	private JComboBox comboBoxPageOrientation;

	private JTextArea textLeftMargin;
	private JTextArea textRightMargin;
	private JTextArea textTopMargin;
	private JTextArea textBottomMargin;

	private JCheckBox chckbxAddHeader;
	private JCheckBox chckbxAddFooter;
	private JCheckBox chckbxAddInFirstPage;

	private byte[] convertHtmlToPdf(boolean convertURL) throws Exception {
		String serverIP = textServerIP.getText();
		int port = Integer.parseInt(textServerPort.getText());

		// create the HTML to PDF converter
		HtmlToPdfConverter htmlToPdfConverter = new HtmlToPdfConverter(serverIP, port);

		// set license key
		htmlToPdfConverter.setLicenseKey("B4mYiJubiJiInoaYiJuZhpmahpGRkZGImg==");

		// set service password if necessary
		if (textServicePassword.getText().length() > 0)
			htmlToPdfConverter.setServicePassword(textServicePassword.getText());

		// set HTML viewer width
		int viewerWidth = Integer.parseInt(textHtmlViewerWidth.getText());
		htmlToPdfConverter.setHtmlViewerWidth(viewerWidth);

		// set HTML viewer height if necessary
		if (textHtmlViewerHeight.getText().length() > 0) {
			int viewerHeight = Integer.parseInt(textHtmlViewerHeight.getText());
			htmlToPdfConverter.setHtmlViewerHeight(viewerHeight);
		}

		// set navigation timeout
		int navigationTimeout = Integer.parseInt(textHtmlViewerWidth.getText());
		htmlToPdfConverter.setNavigationTimeout(navigationTimeout);

		// set conversion delay if necessary
		if (textConversionDelay.getText().length() > 0) {
			int conversionDelay = Integer.parseInt(textConversionDelay.getText());
			htmlToPdfConverter.setConversionDelay(conversionDelay);
		}

		// set PDF page size
		htmlToPdfConverter.pdfDocumentOptions().setPdfPageSize(selectedPdfPageSize());

		// set PDF page orientation
		htmlToPdfConverter.pdfDocumentOptions().setPdfPageOrientation(selectedPdfPageOrientation());

		// set margins
		int leftMargin = Integer.parseInt(textLeftMargin.getText());
		htmlToPdfConverter.pdfDocumentOptions().setLeftMargin(leftMargin);

		int rightMargin = Integer.parseInt(textRightMargin.getText());
		htmlToPdfConverter.pdfDocumentOptions().setRightMargin(rightMargin);

		int topMargin = Integer.parseInt(textTopMargin.getText());
		htmlToPdfConverter.pdfDocumentOptions().setTopMargin(topMargin);

		int bottomMargin = Integer.parseInt(textBottomMargin.getText());
		htmlToPdfConverter.pdfDocumentOptions().setBottomMargin(bottomMargin);

		// add header
		if (chckbxAddHeader.isSelected()) {
			htmlToPdfConverter.pdfDocumentOptions().setShowHeader(true);
			drawHeader(htmlToPdfConverter, true);
		}

		// add footer
		if (chckbxAddFooter.isSelected()) {
			htmlToPdfConverter.pdfDocumentOptions().setShowFooter(true);
			drawFooter(htmlToPdfConverter, true, true);
		}

		byte[] outPdfBuffer = null;

		if (convertURL) {
			// convert URL to PDF

			String urlToConvert = textUrl.getText();

			outPdfBuffer = htmlToPdfConverter.convertUrl(urlToConvert);
		} else {
			// convert HTML to PDF

			String html = textHtml.getText();
			String baseUrl = textBaseUrl.getText();

			outPdfBuffer = htmlToPdfConverter.convertHtml(html, baseUrl);
		}

		return outPdfBuffer;
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

		htmlToPdfConverter.pdfHeaderOptions().setShowInFirstPage(chckbxAddInFirstPage.isSelected());

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

		htmlToPdfConverter.pdfFooterOptions().setShowInFirstPage(chckbxAddInFirstPage.isSelected());
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

	private PdfPageSize selectedPdfPageSize() {
		String pdfPageSizeName = (comboBoxPageSize.getSelectedItem().toString());

		if (pdfPageSizeName == "A0")
			return PdfPageSize.A0;
		if (pdfPageSizeName == "A1")
			return PdfPageSize.A1;
		if (pdfPageSizeName == "A10")
			return PdfPageSize.A10;
		if (pdfPageSizeName == "A2")
			return PdfPageSize.A2;
		if (pdfPageSizeName == "A3")
			return PdfPageSize.A3;
		if (pdfPageSizeName == "A4")
			return PdfPageSize.A4;
		if (pdfPageSizeName == "A5")
			return PdfPageSize.A5;
		if (pdfPageSizeName == "A6")
			return PdfPageSize.A6;
		if (pdfPageSizeName == "A7")
			return PdfPageSize.A7;
		if (pdfPageSizeName == "A8")
			return PdfPageSize.A8;
		if (pdfPageSizeName == "A9")
			return PdfPageSize.A9;
		if (pdfPageSizeName == "ArchA")
			return PdfPageSize.ArchA;
		if (pdfPageSizeName == "ArchB")
			return PdfPageSize.ArchB;
		if (pdfPageSizeName == "ArchC")
			return PdfPageSize.ArchC;
		if (pdfPageSizeName == "ArchD")
			return PdfPageSize.ArchD;
		if (pdfPageSizeName == "ArchE")
			return PdfPageSize.ArchE;
		if (pdfPageSizeName == "B0")
			return PdfPageSize.B0;
		if (pdfPageSizeName == "B1")
			return PdfPageSize.B1;
		if (pdfPageSizeName == "B2")
			return PdfPageSize.B2;
		if (pdfPageSizeName == "B3")
			return PdfPageSize.B3;
		if (pdfPageSizeName == "B4")
			return PdfPageSize.B4;
		if (pdfPageSizeName == "B5")
			return PdfPageSize.B5;
		if (pdfPageSizeName == "Flsa")
			return PdfPageSize.Flsa;
		if (pdfPageSizeName == "HalfLetter")
			return PdfPageSize.HalfLetter;
		if (pdfPageSizeName == "Ledger")
			return PdfPageSize.Ledger;
		if (pdfPageSizeName == "Legal")
			return PdfPageSize.Legal;
		if (pdfPageSizeName == "Letter")
			return PdfPageSize.Letter;
		if (pdfPageSizeName == "Letter11x17")
			return PdfPageSize.Letter11x17;
		if (pdfPageSizeName == "Note")
			return PdfPageSize.Note;

		return PdfPageSize.A4;
	}

	private PdfPageOrientation selectedPdfPageOrientation() {
		return (comboBoxPageOrientation.getSelectedItem().toString() == "Portrait") ? PdfPageOrientation.Portrait
				: PdfPageOrientation.Landscape;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GettingStartedDemo window = new GettingStartedDemo();
					window.frameHtmlToPdf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GettingStartedDemo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameHtmlToPdf = new JFrame();
		frameHtmlToPdf.setTitle("EVO HTML to PDF Demo");
		frameHtmlToPdf.setBounds(100, 100, 600, 750);
		frameHtmlToPdf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameHtmlToPdf.getContentPane().setLayout(null);

		JLabel label = new JLabel("Converter Settings");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 11, 153, 34);
		frameHtmlToPdf.getContentPane().add(label);

		JLabel label_1 = new JLabel("Server IP:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 57, 65, 20);
		frameHtmlToPdf.getContentPane().add(label_1);

		textServerIP = new JTextArea();
		textServerIP.setText("127.0.0.1");
		textServerIP.setBounds(72, 59, 115, 22);
		frameHtmlToPdf.getContentPane().add(textServerIP);

		JLabel label_2 = new JLabel("Server Port:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(208, 57, 74, 20);
		frameHtmlToPdf.getContentPane().add(label_2);

		textServerPort = new JTextArea();
		textServerPort.setText("40001");
		textServerPort.setBounds(289, 59, 55, 22);
		frameHtmlToPdf.getContentPane().add(textServerPort);

		JLabel label_3 = new JLabel("Service Password:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(365, 57, 105, 20);
		frameHtmlToPdf.getContentPane().add(label_3);

		textServicePassword = new JTextArea();
		textServicePassword.setBounds(469, 59, 94, 22);
		frameHtmlToPdf.getContentPane().add(textServicePassword);

		JLabel label_4 = new JLabel("HTML Viewer Width:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(10, 90, 121, 20);
		frameHtmlToPdf.getContentPane().add(label_4);

		textHtmlViewerWidth = new JTextArea();
		textHtmlViewerWidth.setText("1024");
		textHtmlViewerWidth.setBounds(141, 91, 44, 22);
		frameHtmlToPdf.getContentPane().add(textHtmlViewerWidth);

		JLabel label_5 = new JLabel("px");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(195, 90, 19, 20);
		frameHtmlToPdf.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("HTML Viewer Height:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(256, 90, 121, 20);
		frameHtmlToPdf.getContentPane().add(label_6);

		textHtmlViewerHeight = new JTextArea();
		textHtmlViewerHeight.setBounds(387, 91, 44, 22);
		frameHtmlToPdf.getContentPane().add(textHtmlViewerHeight);

		JLabel label_7 = new JLabel("px");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(441, 90, 19, 20);
		frameHtmlToPdf.getContentPane().add(label_7);

		JLabel label_8 = new JLabel("Timeout:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_8.setBounds(10, 131, 55, 20);
		frameHtmlToPdf.getContentPane().add(label_8);

		textTimeout = new JTextArea();
		textTimeout.setText("60");
		textTimeout.setBounds(72, 132, 44, 22);
		frameHtmlToPdf.getContentPane().add(textTimeout);

		JLabel label_9 = new JLabel("sec");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_9.setBounds(126, 131, 29, 20);
		frameHtmlToPdf.getContentPane().add(label_9);

		JLabel label_10 = new JLabel("Delay Conversion:");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_10.setBounds(256, 131, 121, 20);
		frameHtmlToPdf.getContentPane().add(label_10);

		textConversionDelay = new JTextArea();
		textConversionDelay.setText("2");
		textConversionDelay.setBounds(387, 132, 44, 22);
		frameHtmlToPdf.getContentPane().add(textConversionDelay);

		JLabel label_11 = new JLabel("sec");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_11.setBounds(441, 131, 29, 20);
		frameHtmlToPdf.getContentPane().add(label_11);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 300, 553, 4);
		frameHtmlToPdf.getContentPane().add(separator);

		JLabel lblConvertUrlTo = new JLabel("Convert URL to PDF");
		lblConvertUrlTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConvertUrlTo.setBounds(10, 315, 153, 34);
		frameHtmlToPdf.getContentPane().add(lblConvertUrlTo);

		JLabel label_13 = new JLabel("Enter URL to Convert:");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_13.setBounds(10, 361, 136, 20);
		frameHtmlToPdf.getContentPane().add(label_13);

		textUrl = new JTextArea();
		textUrl.setText("http://www.evopdf.com");
		textUrl.setBounds(156, 362, 407, 22);
		frameHtmlToPdf.getContentPane().add(textUrl);

		JButton btnConvertUrlToPdf = new JButton("Convert URL to PDF");
		btnConvertUrlToPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConvertUrlToPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// convert the URL to a PDF document in a buffer
					byte[] outPdfBuffer = convertHtmlToPdf(true);

					String outFilePath = "EvoHtmlToPdf.pdf";

					// write the buffer to a file
					writeBytesToFile(outPdfBuffer, outFilePath);

					Desktop.getDesktop().open(new File(outFilePath));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frameHtmlToPdf,
							String.format("Conversion failed: %1$s", ex.getMessage()));
				}
			}
		});
		btnConvertUrlToPdf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConvertUrlToPdf.setBounds(10, 404, 185, 23);
		frameHtmlToPdf.getContentPane().add(btnConvertUrlToPdf);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 445, 553, 4);
		frameHtmlToPdf.getContentPane().add(separator_1);

		JLabel lblConvertHtmlTo = new JLabel("Convert HTML to PDF");
		lblConvertHtmlTo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConvertHtmlTo.setBounds(10, 456, 175, 34);
		frameHtmlToPdf.getContentPane().add(lblConvertHtmlTo);

		JLabel label_15 = new JLabel("Enter HTML to Convert:");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_15.setBounds(10, 501, 153, 20);
		frameHtmlToPdf.getContentPane().add(label_15);

		textHtml = new JTextArea();
		textHtml.setWrapStyleWord(true);
		textHtml.setText(
				"Enter the HTML String to Convert and optionally set a Base URL if the HTML string references external resources by relative URLs");
		textHtml.setLineWrap(true);

		JLabel label_16 = new JLabel("Enter Base URL:");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_16.setBounds(10, 612, 136, 20);
		frameHtmlToPdf.getContentPane().add(label_16);

		textBaseUrl = new JTextArea();
		textBaseUrl.setBounds(10, 634, 407, 22);
		frameHtmlToPdf.getContentPane().add(textBaseUrl);

		JButton btnConvertHtmlToPdf = new JButton("Convert HTML to PDF");
		btnConvertHtmlToPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					// convert the HTML string to a PDF document in a buffer
					byte[] outPdfBuffer = convertHtmlToPdf(false);

					String outFilePath = "EvoHtmlToPdf.pdf";

					// write the buffer to a file
					writeBytesToFile(outPdfBuffer, outFilePath);

					Desktop.getDesktop().open(new File(outFilePath));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frameHtmlToPdf,
							String.format("Conversion failed: %1$s", ex.getMessage()));
				}
			}
		});
		btnConvertHtmlToPdf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConvertHtmlToPdf.setBounds(10, 677, 185, 23);
		frameHtmlToPdf.getContentPane().add(btnConvertHtmlToPdf);

		JLabel lblPdfPageSettings = new JLabel("PDF Document Settings");
		lblPdfPageSettings.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPdfPageSettings.setBounds(10, 162, 204, 34);
		frameHtmlToPdf.getContentPane().add(lblPdfPageSettings);

		comboBoxPageSize = new JComboBox();
		comboBoxPageSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxPageSize.setModel(new DefaultComboBoxModel(new String[] { "A0", "A1", "A2", "A3", "A4", "A5", "A6",
				"A7", "A8", "A9", "A10", "B0", "B1", "B2", "B3", "B4", "B5", "ArchA", "ArchB", "ArchC", "ArchD",
				"ArchE", "Flsa", "HalfLetter", "Ledger", "Legal", "Letter", "Letter11x17", "Note" }));
		comboBoxPageSize.setBounds(79, 201, 116, 20);
		comboBoxPageSize.setSelectedItem("A4");
		frameHtmlToPdf.getContentPane().add(comboBoxPageSize);

		JLabel lblPageSize = new JLabel("Page Size:");
		lblPageSize.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPageSize.setBounds(10, 201, 65, 20);
		frameHtmlToPdf.getContentPane().add(lblPageSize);

		JLabel lblPageOrientation = new JLabel("Page Orientation:");
		lblPageOrientation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPageOrientation.setBounds(256, 201, 105, 20);
		frameHtmlToPdf.getContentPane().add(lblPageOrientation);

		comboBoxPageOrientation = new JComboBox();
		comboBoxPageOrientation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxPageOrientation.setModel(new DefaultComboBoxModel(new String[] { "Portrait", "Landscape" }));
		comboBoxPageOrientation.setBounds(369, 201, 116, 20);
		comboBoxPageOrientation.setSelectedItem("Portrait");
		frameHtmlToPdf.getContentPane().add(comboBoxPageOrientation);

		chckbxAddHeader = new JCheckBox("Add Header");
		chckbxAddHeader.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxAddHeader.setBounds(10, 270, 97, 23);
		frameHtmlToPdf.getContentPane().add(chckbxAddHeader);

		chckbxAddFooter = new JCheckBox("Add Footer");
		chckbxAddFooter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxAddFooter.setBounds(141, 270, 97, 23);
		frameHtmlToPdf.getContentPane().add(chckbxAddFooter);

		chckbxAddInFirstPage = new JCheckBox("Show Header and Footer in First Page");
		chckbxAddInFirstPage.setSelected(true);
		chckbxAddInFirstPage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxAddInFirstPage.setBounds(256, 270, 240, 23);
		frameHtmlToPdf.getContentPane().add(chckbxAddInFirstPage);

		JLabel lblMargins = new JLabel("Margins:");
		lblMargins.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMargins.setBounds(10, 232, 55, 20);
		frameHtmlToPdf.getContentPane().add(lblMargins);

		textLeftMargin = new JTextArea();
		textLeftMargin.setText("0");
		textLeftMargin.setBounds(111, 233, 29, 22);
		frameHtmlToPdf.getContentPane().add(textLeftMargin);

		JLabel lblLeft = new JLabel("Left");
		lblLeft.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLeft.setBounds(79, 232, 29, 20);
		frameHtmlToPdf.getContentPane().add(lblLeft);

		JLabel lblRight = new JLabel("Right");
		lblRight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRight.setBounds(173, 232, 29, 20);
		frameHtmlToPdf.getContentPane().add(lblRight);

		textRightMargin = new JTextArea();
		textRightMargin.setText("0");
		textRightMargin.setBounds(210, 233, 29, 22);
		frameHtmlToPdf.getContentPane().add(textRightMargin);

		JLabel lblTop = new JLabel("Top");
		lblTop.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTop.setBounds(289, 232, 29, 20);
		frameHtmlToPdf.getContentPane().add(lblTop);

		textTopMargin = new JTextArea();
		textTopMargin.setText("0");
		textTopMargin.setBounds(317, 233, 29, 22);
		frameHtmlToPdf.getContentPane().add(textTopMargin);

		JLabel lblBottom = new JLabel("Bottom");
		lblBottom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBottom.setBounds(387, 232, 49, 20);
		frameHtmlToPdf.getContentPane().add(lblBottom);

		textBottomMargin = new JTextArea();
		textBottomMargin.setText("0");
		textBottomMargin.setBounds(439, 233, 29, 22);
		frameHtmlToPdf.getContentPane().add(textBottomMargin);

		JScrollPane scrollPaneTextHtml = new JScrollPane(textHtml);
		scrollPaneTextHtml.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTextHtml.setBounds(10, 527, 564, 79);
		frameHtmlToPdf.getContentPane().add(scrollPaneTextHtml);

		JLabel lblPt = new JLabel("pt");
		lblPt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPt.setBounds(144, 232, 19, 20);
		frameHtmlToPdf.getContentPane().add(lblPt);

		JLabel label_12 = new JLabel("pt");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_12.setBounds(474, 232, 19, 20);
		frameHtmlToPdf.getContentPane().add(label_12);

		JLabel label_14 = new JLabel("pt");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_14.setBounds(349, 232, 19, 20);
		frameHtmlToPdf.getContentPane().add(label_14);

		JLabel label_17 = new JLabel("pt");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_17.setBounds(251, 232, 19, 20);
		frameHtmlToPdf.getContentPane().add(label_17);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(141, 404, 1, 1);
		frameHtmlToPdf.getContentPane().add(desktopPane);
	}
}
