package com.koreinfo.quoteapp.dao;

import java.util.List;

import com.koreinfo.quoteapp.beans.Quote;

public interface QuoteDao {
	void addQuote(Quote quote);
	void deleteQuote();
	List<Quote> listAllQuotes();
	 List<Quote> getQuoteFrmCInitials(String search);

}
