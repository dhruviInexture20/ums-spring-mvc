package ums.dao;


import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import ums.bean.User;




@Repository
public class GenericDaoImpl<T> implements GenericDao<T> {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Transactional
	@Override
	public int create(T t) {
		// TODO Auto-generated method stub
		
		int id = (int) this.hibernateTemplate.save(t);
		return id;
		
	}

	@Transactional
	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		
//		Object obj = hibernateTemplate.get(User.class , userid);
		hibernateTemplate.delete(t);
		
	}
	
	@Transactional
	@Override
	public T update(T t) {
		
		
		hibernateTemplate.merge(t);
		
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isEmailExist(String email) {
	
		List<T> users = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session ;
		if(factory !=null) {
			session = factory.openSession();
			
			if(session != null) {
				 users = session.createQuery("FROM User U WHERE U.email = :email").setParameter("email", email)
			                .getResultList();
			}
		}
		
		return (users!=null && users.size()>0) ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(String email, String password) {
		// TODO Auto-generated method stub
		
		
		List<T> users = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session ;
		if(factory !=null) {
			session = factory.openSession();
			
			if(session != null) {
				users = session.createQuery("FROM User U WHERE U.email = :email AND password = :password" )
						.setParameter("email", email)
						.setParameter("password", password)
						.getResultList();
			
			}
		}
			
//		if(users.size() > 0) {
//			return users.get(0);
//		}
//		
//		return null;
		
		return (users!=null && users.size()>0) ? users.get(0) : null ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<T> users = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session ;
		if(factory !=null) {
			session = factory.openSession();
			
			if(session != null) {
				users = session.createQuery("FROM User WHERE role = :role " ).setParameter("role", "user")
						.getResultList();
			}
			
		}
		
		
		
		return users;
	}

	@Override
	public User findById(int userid) {
		
		return hibernateTemplate.get( User.class , userid);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getUserByEmail(String email) {
		// TODO Auto-generated method stub
		
		
		List<T> users = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		Session session ;
		if(factory !=null) {
			session = factory.openSession();
			
			if(session != null) {
				users = session.createQuery("FROM User U WHERE U.email = :email").setParameter("email", email)
		                .getResultList();
			}
		}
		
//		if(users.size() > 0) {
//			return users.get(0);
//		}
//		
//		return null;
		
		return (users!=null && users.size()>0) ? users.get(0) : null ;
		
		
	}

}
