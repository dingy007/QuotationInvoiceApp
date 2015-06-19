package com.koreinfo.quoteapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreinfo.quoteapp.beans.Invoice;
import com.koreinfo.quoteapp.beans.Quote;
import com.koreinfo.quoteapp.utils.HibernateUtils;

@Repository("invoiceDao")
public class InvoiceDaoImpl implements InvoiceDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addInvoice(Invoice invoice) {
/*		Session session = HibernateUtils.openSession();
		session.beginTransaction();*/
		Session session = sessionFactory.getCurrentSession();
		session.save(invoice);
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
		Session session = sessionFactory.getCurrentSession();
		List<Invoice> allInvoiceWCInitials = null;
		Criteria crit = session.createCriteria(Quote.class);
		String searchCrit = "%" + search + "%" ;
		crit.add(Restrictions.ilike("c_initials", searchCrit));
		allInvoiceWCInitials = crit.list();
		session.getTransaction().commit();
//		session.close();
		return allInvoiceWCInitials;
	}

}
