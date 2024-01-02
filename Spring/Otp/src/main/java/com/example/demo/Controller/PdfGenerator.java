package com.example.demo.Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Element;

import com.itextpdf.text.Font;

import com.itextpdf.text.FontFactory;

import com.itextpdf.text.PageSize;

import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;


public class PdfGenerator {

	static Calendar calendar = Calendar.getInstance();

	static Date currentDate = (Date) calendar.getTime();
	public static void main( String[] args ) throws FileNotFoundException, DocumentException
	    {
	    	Document document = new Document(PageSize.A4, 50, 50, 50, 50);

	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Flash Recharge invoice.pdf"));

	        writer.setPdfVersion(PdfWriter.VERSION_1_7);


	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.DARK_GRAY);

	        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);

	        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

 
	        BaseColor accentColor = new BaseColor(0, 102, 204);

	        document.open();

	        Paragraph title = new Paragraph("Recharge Invoice", titleFont);

	        title.setAlignment(Element.ALIGN_CENTER);

	        document.add(title);

	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

	        String invoiceDate = dateFormat.format(currentDate);

	        Paragraph invoiceDetails = new Paragraph("Invoice Date: " + invoiceDate);

	        invoiceDetails.setAlignment(Element.ALIGN_RIGHT);

	        document.add(invoiceDetails);

	        Paragraph total = new Paragraph("Total Amount: $50.00", headerFont);

	        total.setAlignment(Element.ALIGN_RIGHT);

	        document.add(total);


	        document.close();


	        System.out.println("recharge invoice generated successfully!");

 

	    }

 

	}
