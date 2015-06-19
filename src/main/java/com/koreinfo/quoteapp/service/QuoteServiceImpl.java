package com.koreinfo.quoteapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.dao.QuoteDao;
import com.koreinfo.quoteapp.dao.QuoteDaoImpl;

@Service("quoteSvc")
public class QuoteServiceImpl implements QuoteService{

	@Autowired(required=false)
	private QuoteDaoImpl quoteDao;// = new QuoteDaoImpl();
	
	private Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);
	
	@Override
	@Transactional
	public void addQuoteService(Quote quote) {
		logger.info("Getting the Quotation number for quote");
		String quotation_no = quote.getQuote_num();
		logger.info("Quotation number : " + quotation_no);
		quote.setQuote_date(new Date(System.currentTimeMillis()));
		quote.setQuote_num(quotation_no);
		quoteDao.addQuote(quote);
	}
	
	@Transactional
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
		logger.info("New Quote Number: " + quotationNumber);
		quote.setQuote_num(quotationNumber.toString());
	}

	@Override
	@Transactional
	public void deleteQuoteService() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<Quote> getQuoteFrmCInitialsSvc(String companyInitials) {
		logger.info("Getting List of Quotes based on Company Initials for: " + companyInitials);
		
		return quoteDao.getQuoteFrmCInitials(companyInitials);
	}

	@Override
	@Transactional
	public List<Quote> listAllQuotesService() {
		return quoteDao.listAllQuotes();
	}

	
}
