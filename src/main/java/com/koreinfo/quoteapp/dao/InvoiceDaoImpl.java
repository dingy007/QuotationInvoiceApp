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

import com.koreinfo.quoteapp.beans.Invoice;
import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.utils.HibernateUtils;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements InvoiceDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	Logger logger = LoggerFactory.getLogger(InvoiceDaoImpl.class);
	@Override
	public void addInvoice(Invoice invoice) {
/*		Session session = HibernateUtils.openSession();
		session.beginTransaction();*/
//		Session session = sessionFactory.getCurrentSession();
//		session.save(invoice);
		logger.info("Adding invoice: "+ invoice.toString() 
				+ "in InvoiceDaoImpl.addInvoice");
		sessionFactory.getCurrentSession().save(invoice);
		/*try {
			  // create session
			  tx = session.beginTransaction();
			  // do something
			  if (!tx.wasCommitted()){
			    tx.commit();
			  }
			} catch (Exception exp) {
			  tx.rollback();
			  // close session
			}*/
/*		session.getTransaction().commit();
		HibernateUtils.closeSession(session);*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invoice> listAllInvoice() {
//		Session session = HibernateUtils.openSession();
		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
		List<Invoice> allInvoice = null;
		try{
			allInvoice = session.createQuery("from Invoice").list();
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
//		session.close();
		return allInvoice;
	}

	@Override
	public List<Invoice> getInvoiceFrmCInitials(String search) {
//		Session session = HibernateUtils.openSession();
//		session.beginTransaction();
/*		
 * 		Session session = sessionFactory.getCurrentSession();
		List<Invoice> allInvoiceWCInitials = null;
		Criteria crit = session.createCriteria(Quote.class);
		String searchCrit = "%" + search + "%" ;
		crit.add(Restrictions.ilike("c_initials", searchCrit));
		allInvoiceWCInitials = crit.list();
		session.getTransaction().commit();
		*/
		List<Invoice> allInvoiceWCInitials = null;
//		Session session = sessionFactory.getCurrentSession();
		logger.info("SessionFactory created in listAllQuotes...");
//		try{
//			allQuotes = session.createQuery("from Quote").list();
//			allInvoiceWCInitials = session.createCriteria(Invoice.class).list();
			if (sessionFactory.getCurrentSession().isOpen()) {
				allInvoiceWCInitials = sessionFactory.getCurrentSession().createCriteria(Invoice.class).list();
			}
			else 
				allInvoiceWCInitials = sessionFactory.openSession().createCriteria(Invoice.class).list();
			logger.info("listAllQuotes created Query for getting all Invoices...");
//		}catch(HibernateException e){
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		session.close();
		return allInvoiceWCInitials;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
