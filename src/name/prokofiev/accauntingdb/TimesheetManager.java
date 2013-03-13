/**
 * @author prokofiev_aa
 */

package name.prokofiev.accauntingdb;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import name.prokofiev.accauntingdb.entities.Employee;
import name.prokofiev.accauntingdb.entities.Timesheet;

/**
 * Управляет доступам к сущностям Табель
 */
public class TimesheetManager {
	/**
	 * Загружает сущность Табель по идентификатору
	 * @param ses сессия
	 * @param id идентификатор
	 * @return найденный табель
	 */
	public Timesheet loadById(Session ses, Integer id) {
		Timesheet ret = null;
		try{
			ses.beginTransaction();
			ret = (Timesheet)ses.createCriteria(Timesheet.class)
					.add(Restrictions.eq("timesheetId", id))
					.uniqueResult();
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		}
		return ret;
	}

	/**
	 * Создает новый Табель для пользователя
	 * @param emp сущность Пользователь
	 * @return сущность Табель
	 */
	public Timesheet createForEmployee(Employee emp) {
		Timesheet ts = new Timesheet();
		ts.setEmployeeId(emp);
		return ts;
	}
	
	/**
	 * Сохраняет сущность Табель
	 * @param ses сессия
	 * @param ts сущность Табель
	 */
	public void save(Session ses, Timesheet ts){
		//TODO Выполнять валидацию связанных сущностей
		try {
			ses.beginTransaction();
			ses.saveOrUpdate(ts);
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		}
	}
	
	/**
	 * Загружает список табелей для сотрудника
	 * @param ses сессия
	 * @param emp сотрудник
	 * @return список табелей, принадлежащий сотруднику
	 */
	@SuppressWarnings("unchecked")
	public List<Timesheet> findForEmployee(Session ses, Employee emp) {
		List<Timesheet> ret = null;
		try{
			ses.beginTransaction();
			ret = (List<Timesheet>)ses.createCriteria(Timesheet.class)
					.add(Restrictions.eq("employeeId", emp))
					.list();
			ses.getTransaction().commit();
		} catch(HibernateException ex) {
			if(ses.getTransaction().isActive()) {
				ses.getTransaction().rollback();
			}
		}
		return ret;
	}
	
}
