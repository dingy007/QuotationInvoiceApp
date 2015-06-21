package com.koreinfo.quoteapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreinfo.quoteapp.beans.Quote;

@Repository("quoteDao")
public class QuoteDaoImpl implements QuoteDao{
	
	private Logger logger = LoggerFactory.getLogger(QuoteDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addQuote(Quote quote) {
/*		Session session = HibernateUtils.openSession();
		session.beginTransaction();*/
		sessionFactory.getCurrentSession().save(quote);
//		session.save(quote);
		/*
		session.getTransaction().commit();
		HibernateUtils.closeSession(session);*/
	}

	@Override
	public void deleteQuote() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Quote> listAllQuotes() {
//		Session session = HibernateUtils.openSession();
//		session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		List<Quote> allQuotes = null;
		logger.info("SessionFactory created in listAllQuotes...");
		try{
//			allQuotes = session.createQuery("from Quote").list();
			allQuotes = session.createCriteria(Quote.class).list();
			logger.info("listAllQuotes created Query for getting all Quotes...");
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//		session.getTransaction().commit();
//		session.close();
		return allQuotes;
	}
	
	@Override
	public List<Quote> getQuoteFrmCInitials(String search) {
		
//		List<Quote> quotes = sessionFactory.getCurrentSession()
//							.createQuery("")
		
		/*
		users = sessionFactory.getCurrentSession()
				.createQuery("from User where userName = :username")
				.setParameter("username", userName)
				.list();
		
		if(users.size() > 0)
			return users.get(0);
		else
			return null;
		
		*/
//		Session session = sessionFactory.getCurrentSession();
//		Session session = HibernateUtils.openSession();
//		session.beginTransaction();
		logger.info("QuoteDaoImpl.getQuoteFrmCInitials()");
		logger.info("Printing all the Quotes List based on Company Initials.");
		List<Quote> allQuotesWCInitials = null;
		Session session = sessionFactory.getCurrentSession();
		logger.info("SessionFactory created in listAllQuotes...");
		try{
//			allQuotes = session.createQuery("from Quote").list();
			allQuotesWCInitials = session.createCriteria(Quote.class).list();
			logger.info("listAllQuotes created Query for getting all Quotes...");
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		/*
		List<Quote> allQuotesWCInitials = null;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Quote.class);
		String searchCrit = "%" + search + "%" ;
		crit.add(Restrictions.ilike("c_initials", searchCrit));
		allQuotesWCInitials = crit.list();
		*/
		
		
//		session.getTransaction().commit();
//		session.close();
		return allQuotesWCInitials;
	}

}
