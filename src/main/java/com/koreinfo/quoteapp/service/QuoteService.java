package com.koreinfo.quoteapp.service;

import java.util.List;

import com.koreinfo.quoteapp.beans.Quote;

public interface QuoteService {

	void addQuoteService(Quote quote);
	void deleteQuoteService();
	List<Quote> listAllQuotesService();
	List<Quote> getQuoteFrmCInitialsSvc(String companyInitials);
	void setQuotationNumber(Quote quote);
}
