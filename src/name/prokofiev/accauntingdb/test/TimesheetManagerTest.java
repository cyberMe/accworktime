/*
 * author prokofiev_aa
 */

package name.prokofiev.accauntingdb.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import name.prokofiev.accauntingdb.DepartmentManager;
import name.prokofiev.accauntingdb.EmployeeManager;
import name.prokofiev.accauntingdb.TimesheetManager;
import name.prokofiev.accauntingdb.entities.Department;
import name.prokofiev.accauntingdb.entities.Employee;
import name.prokofiev.accauntingdb.entities.Timesheet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Проверки работы с управляющими классами
 */
public class TimesheetManagerTest {

	private SessionFactory sf;
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		sf = new Configuration().configure().buildSessionFactory();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Пользователь может вводить рабочие часы и сохранять эти данные
	 */
	@Test
	public void loadChangeSave() {
		Session ses = sf.openSession();
		TimesheetManager tsman = new TimesheetManager();
		Timesheet ts = tsman.loadById(ses, 1);
		assertNotNull(ts);
		ts.minutesMon = 120;
		ts.minutesThu = 360;
		ts.minutesFri = 60;
		tsman.save(ses, ts);
		ses.close();
	}

	/**
	 * Пользователь может создать новый табель и настроить его
	 */
	@Test
	public void createAndAdjustTimesheet() {
		Session ses = sf.openSession();
		Employee emp = new EmployeeManager().findEmployee(ses, 1, "12345678");
		assertNotNull(emp);
		Timesheet ts = new TimesheetManager().createForEmployee(emp);
		assertNotNull(ts);
		List<Department> deps = new DepartmentManager().getDerartments(ses);
		assertNotNull(deps);
		assertTrue(deps.size() > 0);
		ts.departmentCode = deps.iterator().next();
		ts.periodEndingDate = Calendar.getInstance().getTime();
		new TimesheetManager().save(ses, ts);
		ses.close();
	}
	
	/**
	 * Сотрудник может просматривать список введенных ранее табелей учета 
	 * рабочего времени и щелкать на тех, 
	 * которые можно модифицировать
	 */
	@Test
	public void loadTsForEmp() {
		Session ses = sf.openSession();
		Employee emp = new EmployeeManager().findEmployee(ses, 1, "12345678");
		List<Timesheet> list = new TimesheetManager().findForEmployee(ses, emp);
		assertNotNull(list);
		System.out.println(list.toString());
		ses.close();
	}
	
	/**
	 * Пользователь может зарегистрироваться в системе, 
	 * используя допустимый идентификатор сотрудника и пароль
	 */
	@Test
	public void employerLogIn() {
		Session ses = sf.openSession();
		Employee emp = new EmployeeManager().findEmployee(ses, 1, "12345678");
		assertNotNull(emp);
		ses.close();
	}
	
}
