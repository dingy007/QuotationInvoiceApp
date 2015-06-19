package com.koreinfo.quoteapp.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.bsf.util.Bean;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author dineshkp
 * Main Table
 *
 */
@Entity
@Table(name="Quote")
public class Quote implements Serializable{
	@TableGenerator(name="quote_id_gen", table="id_gen", pkColumnName="gen_name"
			, valueColumnName = "gen_val", pkColumnValue = "quoteId_gen", initialValue=1
			, allocationSize=100)
	
	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="quote_id_gen")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="QuoteId")
//	@Min(value=1)
	private int id;
	@Column(name="company_name")
	@NotNull @NotBlank
	private String c_name;
	@Column(name="company_add")
	private String c_add;
	@Column(name="company_initials",nullable=false)
	@Size(max=3, min=3)
	@NotNull @NotBlank
	private String c_initials;
	@Column(name="company_phone")
	private int c_phone;
	@Column(name="company_quote_count")
	private int c_quote_count;
	@Column(name="quotation_number",unique=true,nullable=false)
//	@NotNull @NotBlank
	private String quote_num;
	@Column(name="person_in_charge")
	private String person_in_charge;
	@Column(name="sales_person")
	private String sales_person;
	@Column(name="total_amount", precision=2)
	private double amount;
	@Column(name="quotation_date")
	private Date quote_date;
	@OneToOne(mappedBy = "quote",targetEntity=Invoice.class)
	private Invoice invoice;
	
	public Quote() {
		super();
	}

	@Autowired
	public Quote(String c_name, String c_add, String c_initials,
			int c_phone, String person_in_charge,
			double amount, Date quote_date, String sales_person) {
		super();
		this.sales_person = sales_person;
		this.c_name = c_name;
		this.c_add = c_add;
		this.c_initials = c_initials;
		this.c_phone = c_phone;
		this.person_in_charge = person_in_charge;
		this.amount = amount;
		this.quote_date = quote_date;
	}


	public String getSales_person() {
		return sales_person;
	}
	@Autowired
	public void setSales_person(String sales_person) {
		this.sales_person = sales_person;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote [id=");
		builder.append(id);
		builder.append(", c_name=");
		builder.append(c_name);
		builder.append(", c_add=");
		builder.append(c_add);
		builder.append(", c_initials=");
		builder.append(c_initials);
		builder.append(", c_phone=");
		builder.append(c_phone);
		builder.append(", c_quote_count=");
		builder.append(c_quote_count);
		builder.append(", quote_num=");
		builder.append(quote_num);
		builder.append(", person_in_charge=");
		builder.append(person_in_charge);
		builder.append(", sales_person=");
		builder.append(sales_person);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", quote_date=");
		builder.append(quote_date);
		builder.append("]");
		return builder.toString();
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_add() {
		return c_add;
	}

	public void setC_add(String c_add) {
		this.c_add = c_add;
	}

	public String getC_initials() {
		return c_initials;
	}

	public void setC_initials(String c_initials) {
		this.c_initials = c_initials;
	}

	public int getC_phone() {
		return c_phone;
	}

	public void setC_phone(int c_phone) {
		this.c_phone = c_phone;
	}

	public int getC_quote_count() {
		return c_quote_count;
	}

	public void setC_quote_count(int c_quote_count) {
		this.c_quote_count = c_quote_count;
	}

	public String getQuote_num() {
		return quote_num;
	}

	public void setQuote_num(String quote_num) {
		this.quote_num = quote_num;
	}

	public String getPerson_in_charge() {
		return person_in_charge;
	}

	public void setPerson_in_charge(String person_in_charge) {
		this.person_in_charge = person_in_charge;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getQuote_date() {
		return quote_date;
	}

	public void setQuote_date(Date quote_date) {
		this.quote_date = quote_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
//	@JoinColumn(name="invoice_fk", referencedColumnName="quotation_number")

	public Invoice getInvoice() {
		return invoice;
	}
	@Autowired
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


}
