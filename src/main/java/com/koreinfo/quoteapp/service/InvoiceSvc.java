package com.koreinfo.quoteapp.service;

import java.util.List;

import com.koreinfo.quoteapp.beans.Invoice;

public interface InvoiceSvc {
	void addInvoice(Invoice invoice);
	String getInvoiceDetails(Invoice invoice);
	void addInvoice(String quoteNumber);
	List<Invoice> listAllInvoice();
	List<Invoice> getInvoiceFrmCInitialsSvc(String companyInitials);
	public int getInvoiceNumber();
}
