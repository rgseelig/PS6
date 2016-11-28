package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import domain.PersonDomainModel;
import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL {
	
	/**
	 * addPerson - Method adds a Person to the database
	 * @param pers
	 * @return
	 */
	public static PersonDomainModel addPerson(PersonDomainModel pers) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int employeeID = 0;
		try {
			tx = session.beginTransaction();
			session.save(pers);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pers;
	}
	
	public static ArrayList<PersonDomainModel> getPersons() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel persGet = null;		
		ArrayList<PersonDomainModel> perss = new ArrayList<PersonDomainModel>();
		
		try {
			tx = session.beginTransaction();	
			
			List Persons = session.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = Persons.iterator(); iterator.hasNext();) {
				PersonDomainModel pers = (PersonDomainModel) iterator.next();
				perss.add(pers);

			}
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return perss;
	}		
	
	public static PersonDomainModel getPerson(UUID persID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel persGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			Query query = session.createQuery("from PersonDomainModel where PersonId = :id ");
			query.setParameter("id", persID.toString());
			
			List<?> list = query.list();
			persGet = (PersonDomainModel)list.get(0);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return persGet;
	}
	
	
	public static void deletePerson(UUID persID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel persGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			PersonDomainModel pers = (PersonDomainModel) session.get(PersonDomainModel.class, persID);
			session.delete(pers);
		
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
	
	public static PersonDomainModel updatePerson(PersonDomainModel pers) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		PersonDomainModel persGet = null;		
		
		try {
			tx = session.beginTransaction();	
									
			session.update(pers);
	
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return pers;
	}		
	

}
