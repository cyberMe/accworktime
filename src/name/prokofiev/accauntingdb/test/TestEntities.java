/**
 * @author prokofiev_aa
 */


package name.prokofiev.accauntingdb.test;

import junit.framework.Assert;

import name.prokofiev.accauntingdb.entities.Department;
import name.prokofiev.accauntingdb.entities.Employee;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Простые проверки работы с сущностями
 *
 */
public class TestEntities {
	SessionFactory sessionFactory;
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Проверка создания сущности Пользователь в БД
	 */
	@Test
	public void testCreateEmployee() {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Employee emp = new Employee();
			emp.setName("Alan Corb");
			emp.setPassword("12345678");
			emp.setEmail("alan@mail.ru");
			session.saveOrUpdate(emp);
			session.getTransaction().commit();
		} catch (HibernateException ex) {
			if(session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
		} finally{
			session.close();
		}
	}
	
	/**
	 * Проверка поиска сущности Пользователь в БД
	 */
	@Test
	public void testFindEmployee(){
		Session ses = sessionFactory.openSession();
		try{
			ses.beginTransaction();
			Criteria crit = ses.createCriteria(Employee.class)
					.add(Restrictions.eq("name", "Alan Corb"))
					.add(Restrictions.eq("password", "12345678"))
					.setMaxResults(1);
			Employee em = (Employee)crit.uniqueResult();
			if(em != null){
				Assert.assertEquals(em.getEmail(), "alan@mail.ru");
			}
			ses.getTransaction().commit();
		}catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		} finally{
			ses.close();
		}
	}

	/**
	 * Проверка сохранения сущности Подразделение в БД
	 */
	@Test
	public void testCreateDepartment() {
		Session ses = sessionFactory.openSession();
		try{
			ses.beginTransaction();
			Department dep = new Department();
			dep.setDepartmentCode("sd");
			dep.setName("Software Development");
			ses.saveOrUpdate(dep);
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		} finally {
			ses.close();
		}
	}
	
	/**
	 * Проверка поиска сущности Подразделение в БД
	 */
	@Test
	public void testFindDepartment() {
		Session ses = sessionFactory.openSession();
		try {
			ses.beginTransaction();
			Criteria crit = ses.createCriteria(Department.class)
					.add(Restrictions.eq("departmentCode", "sd"))
					.setMaxResults(1);
			Department dep = (Department) crit.uniqueResult();
			if(dep != null) {
				Assert.assertEquals(dep.getName(), "Software Development");
			}
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		} finally {
			ses.close();
		}
	}
	
}
