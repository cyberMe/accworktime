/**
 * @author prokofiev_aa
 */
package name.prokofiev.accauntingdb;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import name.prokofiev.accauntingdb.entities.Department;

/**
 * Управляет доступом к сущностям Department
 */
public class DepartmentManager {
	/**
	 * Возвращает список всех зарегимтрированных подразделений
	 * @param ses сессия
	 * @return список
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDerartments(Session ses) {
		List<Department> ret = new ArrayList<Department>();
		try{
			ses.beginTransaction();
			ret = (List<Department>)ses.createCriteria(Department.class).list();
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		}
		return ret;
	}
}
