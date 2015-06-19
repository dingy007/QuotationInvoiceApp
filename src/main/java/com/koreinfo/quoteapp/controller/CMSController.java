package com.koreinfo.quoteapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreinfo.quoteapp.beans.Invoice;
import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.service.InvoiceSvc;
import com.koreinfo.quoteapp.service.InvoiceSvcImpl;
import com.koreinfo.quoteapp.service.QuoteService;
import com.koreinfo.quoteapp.service.QuoteServiceImpl;

@Controller
public class CMSController {
	private static Logger logger = LoggerFactory.getLogger(CMSController.class);
	
	@Autowired
	private QuoteService quoteSvc;
	@Autowired
	private InvoiceSvc invoiceSvc;// = new InvoiceSvcImpl();
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showHomePage() {
		return "home";
	}
	
	/*
	 * For 'home.jsp' page
	 */
	@RequestMapping(value="/showQuoteForm", method=RequestMethod.GET)
	public String showContactForm(@ModelAttribute("quote")Quote quote, BindingResult result) { // Creating a 'quote' object (Quote quote) 
		// and pass it to the view 'QuoteForm.jsp'
		logger.info("Redirecting to Add new Quote Form");
		return "QuoteForm";
	}

	@RequestMapping(value="/listAllQuote", method=RequestMethod.GET)
	public ModelAndView showQuoteList() {
		logger.info("Listing all Quotes");
		ModelAndView mav = new ModelAndView("ListQuotes");
		mav.addObject("quoteList", quoteSvc.listAllQuotesService());
		return mav;
	}

	@RequestMapping(value="/listAllInvoice", method=RequestMethod.GET)
	public ModelAndView showInvoiceList() {
		logger.info("Listing All Invoices");
		ModelAndView mav = new ModelAndView("listAllQuote");
		mav.addObject("invoiceList", invoiceSvc.listAllInvoice());
		return mav;
	}

	@RequestMapping(value="/addQuote", method=RequestMethod.POST)
	public String showQuote(@ModelAttribute("quote") @Valid Quote quote, BindingResult result) {
		
		if (result.hasErrors()) {
			logger.error("Validation has errors! (BindingResult hasErrors)!");
			logger.error(result.getAllErrors().toString());
			return "QuoteForm";
		}
		else {
			logger.info("Listing the newly created Quote");
			logger.info("Quotes company: " + quote.getC_name());
			quoteSvc.setQuotationNumber(quote);
			quoteSvc.addQuoteService(quote);
			Invoice invoice = new Invoice();
			invoice.setQuote(quote);
			invoiceSvc.addInvoice(invoice);
			return "ShowCurrentQuote";
		}
	}

	/*
	 * For 'home.jsp' page
	 */
}
