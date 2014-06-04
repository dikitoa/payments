package es.unileon.ulebank.documents;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import es.unileon.ulebank.assets.strategy.loan.ScheduledPayment;
import es.unileon.ulebank.assets.strategy.loan.StrategyLoan;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.client.Person;

public class GeneratePDFDocument {

	//private File ruta_destino = null;
	private Document mipdf;
	private int idContract;
	private Date start;
	private Date finish;
	private ArrayList<ScheduledPayment> payments;
	private Person client;

	public GeneratePDFDocument(int idContract, Date start, Date finish, StrategyLoan sl, Person p){
		this.idContract = idContract;
		this.start = start;
		this.finish = finish;
		this.payments = sl.doCalculationOfPayments();
		this.client = p;
		this.generatePDFDocument();
	}

	/* metodo que hace uso de la clase itext para manipular archivos PDF */
	public void generatePDFDocument() {
		// se crea instancia del documento
		mipdf = new Document();
		// se establece una instancia a un documento pdf
		try {
			PdfWriter.getInstance(mipdf, new FileOutputStream("_contrato.pdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		mipdf.open();// se abre el documento
		mipdf.addTitle("Plan de amortizacion de prestamo bancario"); // se a??ade el titulo
		mipdf.addAuthor("ULE-BANK"); // se a??ade el autor del documento
		mipdf.addSubject("Amortizacion de prestamo"); // se a??ade el asunto del documento
		addBase(); // se a??ade el contenido del PDF
		mipdf.close(); // se cierra el PDF
	}
	
	private void addBase(){
		addTitle();
		separator();
		addAnnex(idContract);
		addDates(start,finish);
		separator();
		addClientInformation();
		separator();
		addSignatures();
		separator();
		addAmortization();
	}
	
	/**
	 * A?ade los datos del cliente
	 */
	private void addClientInformation(){
		String name = "Nombre: " + client.getName() + " " + client.getSurnames();
		String address = "Direccion: " + client.getAddress();
		String civilState = "Estado civil: " + client.getCivilState();
		String telephone = "Telefono: " + client.getPhoneNumber(0);
		String proffesion = "Ocupacion: " + client.getProfession();
		String birthDate = "A?o nacimiento: " + client.getBirthDate();
		
		Font font = FontFactory.getFont("Times-Roman", 12, Font.NORMAL);
		
		try {
			mipdf.add(new Paragraph(name, font));
			mipdf.add(new Paragraph(address, font));
			mipdf.add(new Paragraph(civilState, font));
			mipdf.add(new Paragraph(telephone, font));
			mipdf.add(new Paragraph(proffesion, font));
			mipdf.add(new Paragraph(birthDate, font));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A??ade el cuadro de amortizacion
	 */
	private void addAmortization(){
		PdfPTable amortTable = null;
		try {
			mipdf.add(generateAmortTable(amortTable));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera la tabla de amortizacion y la rellena
	 * @param amort
	 * @return
	 */
	private PdfPTable generateAmortTable(PdfPTable amort) {
		
		// creamos una tabla con 3 columnas
		PdfPTable mitablacompleja = new PdfPTable(6);
		mitablacompleja.setWidthPercentage(100);
		// a??adimos texto con formato a la primera celda
		PdfPCell celda = new PdfPCell(new Paragraph(
				"VENCIMIENTO", FontFactory.getFont("Times-Roman", // fuente
						12, // tama??o
						Font.BOLD, // estilo
						BaseColor.BLACK))); // color
		// alineamos el contenido al centro de la celda
		celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
		//unimos esta celda con otra
		celda.setColspan(2);
		// alineamos el contenido al centro
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		// a??adimos un espaciado
		celda.setPadding(6.0f);
		// colocamos un color de fondo
		celda.setBackgroundColor(BaseColor.GRAY);
		// se a??ade a la tabla
		mitablacompleja.addCell(celda);

		PdfPCell celda2 = new PdfPCell(new Paragraph(
				"IMPORTE PLAZO", FontFactory.getFont("Times-Roman", // fuente
						12, // tama??o
						Font.BOLD, // estilo
						BaseColor.BLACK))); // color
		celda2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda2.setPadding(6.0f);
		celda2.setBackgroundColor(BaseColor.GRAY);
		mitablacompleja.addCell(celda2);
		
		PdfPCell celda3 = new PdfPCell(new Paragraph(
				"AMORT. CAPITAL", FontFactory.getFont("Times-Roman", // fuente
						12, // tama??o
						Font.BOLD, // estilo
						BaseColor.BLACK))); // color
		celda3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda3.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda3.setPadding(6.0f);
		celda3.setBackgroundColor(BaseColor.GRAY);
		mitablacompleja.addCell(celda3);
		
		PdfPCell celda4 = new PdfPCell(new Paragraph(
				"INTERESES", FontFactory.getFont("Times-Roman", // fuente
						12, // tama??o
						Font.BOLD, // estilo
						BaseColor.BLACK))); // color
		celda4.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda4.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda4.setPadding(6.0f);
		celda4.setBackgroundColor(BaseColor.GRAY);
		mitablacompleja.addCell(celda4);
		
		PdfPCell celda5 = new PdfPCell(new Paragraph(
				"CAPITAL PENDIENTE", FontFactory.getFont("Times-Roman", // fuente
						12, // tama??o
						Font.BOLD, // estilo
						BaseColor.BLACK))); // color
		celda5.setVerticalAlignment(Element.ALIGN_MIDDLE);
		celda5.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda5.setPadding(6.0f);
		celda5.setBackgroundColor(BaseColor.GRAY);
		mitablacompleja.addCell(celda5);
		
		
		//Introduccion de datos del cuadro
		
		for(int i=0; i<this.payments.size(); i++){
			ScheduledPayment sp = payments.get(i);
			
			celda = new PdfPCell(new Paragraph("" + sp.getExpiration(), FontFactory.getFont("Times-Roman", // fuente
					10, // tama??o
					Font.NORMAL, // estilo
					BaseColor.BLACK)));
			celda.setColspan(2);
			celda.setBackgroundColor(BaseColor.GREEN);
			mitablacompleja.addCell(celda);
			
			celda = new PdfPCell(new Paragraph("" + sp.getImportOfTerm() + "???", FontFactory.getFont("Times-Roman", // fuente
					10, // tama??o
					Font.NORMAL, // estilo
					BaseColor.BLACK)));
			//celda.setBackgroundColor(BaseColor.GREEN);
			mitablacompleja.addCell(celda);
			
			celda = new PdfPCell(new Paragraph("" + sp.getAmortization() + "???", FontFactory.getFont("Times-Roman", // fuente
					10, // tama??o
					Font.NORMAL, // estilo
					BaseColor.BLACK)));
			//celda.setBackgroundColor(BaseColor.GREEN);
			mitablacompleja.addCell(celda);
			
			celda = new PdfPCell(new Paragraph("" + sp.getInterests() + "???", FontFactory.getFont("Times-Roman", // fuente
					10, // tama??o
					Font.NORMAL, // estilo
					BaseColor.BLACK)));
			//celda.setBackgroundColor(BaseColor.GREEN);
			mitablacompleja.addCell(celda);
			
			celda = new PdfPCell(new Paragraph("" + sp.getOutstandingCapital() + "???", FontFactory.getFont("Times-Roman", // fuente
					10, // tama??o
					Font.NORMAL, // estilo
					BaseColor.BLACK)));
			//celda.setBackgroundColor(BaseColor.GREEN);
			mitablacompleja.addCell(celda);
			
		}
		
		// se retorna la tabla
		return mitablacompleja;
	}

	/**
	 * A??ade la ubicacion fisica y temporal y el sitio para firmas
	 */
	private void addSignatures() {
		String line1 = "\nEn prueba de conformidad, firman las partes el presente contrato en ..... hojas y tantos ejemplares como partes intervienen.";
		String line2 = "En "+ this.getGeolocalization() +" a " + new Date();
		String line3 = "El/Los Prestatario/s \t\t\t El/Los Fiador/es \t\t\t El Financiador \t\t\t El Fedatario\n\n\n\n\n\n";

		Font font = FontFactory.getFont("Times-Roman", 12, Font.ITALIC);

		try {
			mipdf.add(new Paragraph(line1, font));
			mipdf.add(new Paragraph(line2, font));
			mipdf.add(new Paragraph(line3, font));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Separador de contenidos
	 */
	private void separator() {
		String separator = "\n----------------------------------------------------------------------------------------------------------------------------------";
		try {
			mipdf.add(new Paragraph(separator+ "\n"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A??ade los limites temporales dek contrato
	 * @param start
	 * @param finish
	 */
	private void addDates(Date start, Date finish) {
		String titulo = "Celebrado entre: "+start +" y "+ finish;
		
		Font font = FontFactory.getFont("Times-Roman", 12, Font.NORMAL);
		
		try {
			mipdf.add(new Paragraph(titulo, font));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A??ade el anexo
	 * @param num
	 */
	private void addAnnex(int num) {
		String titulo = "Anexo al Contrato de Financiacion a comprador de bienes inmuebles, impreso n?? "+num;
		
		Font font = FontFactory.getFont("Times-Roman", 12, Font.UNDERLINE);
		
		try {
			mipdf.add(new Paragraph(titulo, font));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A??ade el titulo
	 */
	private void addTitle() {
		String titulo = "PLAN DE AMORTIZACION DE PRESTAMO";

        Font font = FontFactory.getFont("Times-Roman", 18, Font.BOLD);
		
		try {
			mipdf.add(new Paragraph(titulo, font));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtiene la localizacion en base a la direccion IP del equipo donde se ejecuta
	 * @return
	 */
	private String getGeolocalization(){
		String localization = "";
		org.jsoup.nodes.Document doc;
		try {
			//Conexion especial para la codificacion ISO-8859-15
			Connection connectionTest = Jsoup.connect("http://www.geoiptool.com/es/")
					.cookie("cookiereference", "cookievalue")
					.method(Method.POST);
			doc = Jsoup.parse(new String(
					connectionTest.execute().bodyAsBytes(),"ISO-8859-15"));
			
			Elements query=doc.select("td[class=arial_bold]");
			org.jsoup.nodes.Element interest = query.get(5);
			char [] charsInterest=interest.text().toCharArray();

			localization = String.copyValueOf(charsInterest,0,charsInterest.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return localization + " " + Locale.getDefault().getCountry();
	}

	/**
	 * A??ade cualquier tipo de contenido como texto plano
	 * @param contenido
	 */
	public void anyadirParrafo(String contenido){
		try {
			mipdf.add(new Paragraph(contenido));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
}