package com.koreinfo.quoteapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.utils.HibernateUtils;

public class QuoteDaoImpl implements QuoteDao{

	@Override
	public void addQuote(Quote quote) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		session.save(quote);
		session.getTransaction().commit();
		HibernateUtils.closeSession(session);
	}

	@Override
	public void deleteQuote() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Quote> listAllQuotes() {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<Quote> allQuotes = null;
		try{
			allQuotes = session.createQuery("from Quote").list();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
		session.close();
		return allQuotes;
	}
	
	@Override
	public List<Quote> getQuoteFrmCInitials(String search) {
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		List<Quote> allQuotesWCInitials = null;
		Criteria crit = session.createCriteria(Quote.class);
		String searchCrit = "%" + search + "%" ;
		crit.add(Restrictions.ilike("c_initials", searchCrit));
		allQuotesWCInitials = crit.list();
		session.getTransaction().commit();
		session.close();
		return allQuotesWCInitials;
	}

}
