package com.koreinfo.quoteapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.dao.QuoteDao;
import com.koreinfo.quoteapp.dao.QuoteDaoImpl;

public class QuoteServiceImpl implements QuoteService{

	private QuoteDao quoteDao = new QuoteDaoImpl();
	
	private Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);
	
	@Override
	public void addQuoteService(Quote quote) {
		logger.info("Getting the Quotation number for quote");
		String quotation_no = quote.getQuote_num();
		logger.info("Quotation number : " + quotation_no);
		quote.setQuote_date(new Date(System.currentTimeMillis()));
		quote.setQuote_num(quotation_no);
		quoteDao.addQuote(quote);
	}
	
	public void setQuotationNumber(Quote quote) {
		StringBuilder quotationNumber = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		String salesPerson = quote.getSales_person();
		QuoteServiceImpl quoteSvc = new QuoteServiceImpl();
		
		String companyInitials = quote.getC_initials();
		List<Quote> listOfQuotesForCompany = quoteSvc.getQuoteFrmCInitialsSvc(companyInitials);
		int numOfQuoteForCompany = 1;
		if (!(listOfQuotesForCompany.isEmpty())) {
			numOfQuoteForCompany += listOfQuotesForCompany.size();
		}
		quote.setC_quote_count(numOfQuoteForCompany);
//		cal.setTime(quote.getQuote_date());
		int year = cal.get(Calendar.YEAR);
		quotationNumber.append(year);
		quotationNumber.append("/");
		quotationNumber.append(salesPerson);
		quotationNumber.append("/");
		quotationNumber.append(companyInitials);
		quotationNumber.append("/");
		quotationNumber.append(numOfQuoteForCompany);
		
		quote.setQuote_num(quotationNumber.toString());
	}

	@Override
	public void deleteQuoteService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Quote> getQuoteFrmCInitialsSvc(String companyInitials) {
		return quoteDao.getQuoteFrmCInitials(companyInitials);
	}

	@Override
	public List<Quote> listAllQuotesService() {
		return quoteDao.listAllQuotes();
	}

	
}
