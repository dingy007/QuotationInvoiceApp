package com.koreinfo.quoteapp.dao;

import java.util.List;

import com.koreinfo.quoteapp.beans.Invoice;


public interface InvoiceDao {
	void addInvoice(Invoice invoice);
//	void getInvoiceDetails(Invoic)
	List<Invoice> listAllInvoice();
	List<Invoice> getInvoiceFrmCInitials(String search);

}
