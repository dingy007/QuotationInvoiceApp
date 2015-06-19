package com.koreinfo.quoteapp.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.koreinfo.quoteapp.beans.Invoice;
import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.dao.InvoiceDao;
import com.koreinfo.quoteapp.dao.InvoiceDaoImpl;

@Service("invoiceSvc")
public class InvoiceSvcImpl implements InvoiceSvc {

	private InvoiceDao invoiceDao = new InvoiceDaoImpl();
	
	@Override
	@Transactional
	public void addInvoice(Invoice invoice) {
		InvoiceSvcImpl invoiceSvc = new InvoiceSvcImpl(); 
		Quote quote = invoice.getQuote();
		String companyInitials = quote.getC_initials();

		List<Invoice> listOfInvoiceForCompany = invoiceSvc.getInvoiceFrmCInitialsSvc(companyInitials);
		
		int numOfInvoiceForCompany = 1;
		if (!(listOfInvoiceForCompany.isEmpty())) {
			numOfInvoiceForCompany += listOfInvoiceForCompany.size();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(quote.getQuote_date());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		StringBuilder invoiceNumber = new StringBuilder();
		
		invoiceNumber.append(year);
		invoiceNumber.append("/");
		invoiceNumber.append(month);
		invoiceNumber.append("/");
		invoiceNumber.append(invoiceSvc.getInvoiceNumber());
		invoiceNumber.append("/");
		invoiceNumber.append(numOfInvoiceForCompany);
		
		invoice.setInvoiceNumber(invoiceNumber.toString());
		invoice.setC_initials(companyInitials);
		invoiceDao.addInvoice(invoice);
	}
	
	@Transactional
	public int getInvoiceNumber() {
		InvoiceSvcImpl invoiceSvc = new InvoiceSvcImpl(); 
		List<Invoice> listOfInvoice = invoiceSvc.listAllInvoice();
		int numOfInvoiceNumber = 1;
		if (!(listOfInvoice.isEmpty())) {
			numOfInvoiceNumber += listOfInvoice.size();
		}
		return numOfInvoiceNumber;
	}
	

	@Override
	@Transactional
	public String getInvoiceDetails(Invoice invoice) {
		StringBuilder invoiceDetails = new StringBuilder();
		invoiceDetails.append("Qutoation Details: \n");
//		invoiceDetails.append(invoice.getQuotation().toString());
		invoiceDetails.append("***********************");
		invoiceDetails.append("Invoice Details: \n");
		invoiceDetails.append(invoice.toString());
		invoiceDetails.append("***********************");
		return invoiceDetails.toString();
	}


	@Override
	@Transactional
	public void addInvoice(String quoteNumber) {
		// TODO implement adding Quote Service by Quote Number
	}
	
	@Override
	@Transactional
	public List<Invoice> getInvoiceFrmCInitialsSvc(String companyInitials) {
		return invoiceDao.getInvoiceFrmCInitials(companyInitials);
	}

	@Override
	@Transactional
	public List<Invoice> listAllInvoice() {
		return invoiceDao.listAllInvoice();
	}

}
