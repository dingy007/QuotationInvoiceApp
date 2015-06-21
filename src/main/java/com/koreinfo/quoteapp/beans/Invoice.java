package com.koreinfo.quoteapp.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dineshkp
 * Dependent table, with Foreign key quote_fk pointing to the quotation_number
 *
 */
@Entity
@Table(name="Invoice")
public class Invoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private Logger logger = LoggerFactory.getLogger(Quote.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Invoiceid")
	private int id;
	@Column(name="Invoice_number")
	private String invoiceNumber;
	
	@OneToOne(orphanRemoval=true, fetch=FetchType.EAGER, optional=true)
//	@JoinColumn(name="quote_fk",referencedColumnName="quotation_number")
//	@PrimaryKeyJoinColumn()
//	@JoinColumn(name="quote_fk",referencedColumnName="quotation_number")
	@JoinColumn(name="quote_fk")
	private Quote quote;
	
	@Column(name="Comapny_initials")
	private String c_initials;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [id=");
		builder.append(id);
		builder.append(", Invoice Number=");
		builder.append(invoiceNumber);
		builder.append(", quotation_ref=");
		builder.append("]");
		return builder.toString();
	}

	public Invoice() {
		super();
	}


	public Quote getQuote() {
		Restrictions.ne(quote.getQuote_num(), null);
		return quote;
	}
	public void setQuote(Quote quote) {
		
		this.quote = quote;
	}
	public String getC_initials() {
		return c_initials;
	}
	public void setC_initials(String c_initials) {
		this.c_initials = c_initials;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


}
