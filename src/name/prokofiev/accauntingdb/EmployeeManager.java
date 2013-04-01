/*
 * author: prokofiev_aa
 */
package name.prokofiev.accauntingdb;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import name.prokofiev.accauntingdb.entities.Employee;

/**
 * Управляет доступом к объектам employee
 */
public class EmployeeManager {

	/**
	 * Выполняет поиск Employee
	 * @param ses сессия
	 * @param id идентификтор 
	 * @param password пароль
	 * @return 
	 */
	public Employee findEmployee(Session ses, Integer id, String password) {
		Employee ret = null;
	
		try {
			ses.beginTransaction();
			ret = (Employee) ses.createCriteria(Employee.class)
					.add(Restrictions.eq("employeeId", id))
					.add(Restrictions.eq("password", password))
					.setMaxResults(1)
					.uniqueResult();
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if (ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		}
		return ret;
	}
}
