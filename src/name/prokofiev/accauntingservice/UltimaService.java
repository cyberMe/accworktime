/*
 * author prokofiev_aa
 */
package name.prokofiev.accauntingservice;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import name.prokofiev.accauntingdb.DepartmentManager;
import name.prokofiev.accauntingdb.TimesheetManager;
import name.prokofiev.accauntingdb.entities.Department;
import name.prokofiev.accauntingdb.entities.Timesheet;
import name.prokofiev.accauntingservice.jaxws.AllDepartment;
import name.prokofiev.accauntingservice.jaxws.TSResult;
import name.prokofiev.accauntingservice.jaxws.TimeSheetData;

/**
 * Реализация сервиса
 */
@WebService(endpointInterface = "name.prokofiev.accauntingservice.Ultima")
public class UltimaService implements Ultima {
	
	SessionFactory sf;
	
	private int keyval = 0;
	
	@SuppressWarnings("deprecation")
	public UltimaService() {
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	/**
	 *  Устанавливает новый временной табель
	 */
	@Override
	@WebMethod
	public TSResult setTimeSheet(TimeSheetData incTS) {
		TimesheetManager tsManager = new TimesheetManager();
		DepartmentManager dpManager = new DepartmentManager();
		Session ses = sf.openSession();
		Timesheet ts = tsManager.loadById(ses, incTS.tsId);
		Department dp = dpManager.getDepartmentByName(ses,
				incTS.departmentCode);
		
		if (dp == null) {
			return new TSResult(false, "Bad department name");
		}
		ts.departmentCode = dp;
		
		ts.minutesMon = incTS.modayTime;
		ts.minutesTue = incTS.thuesdayTime;
		ts.minutesWed = incTS.wedsdayTime;
		ts.minutesThu = incTS.thursdayTime;
		ts.minutesFri = incTS.fridayTime;
		ts.minutesSat = incTS.saturdayTime;
		ts.minutesSun = incTS.sundayTime;
		tsManager.save(ses, ts);
		ses.close();
		return new TSResult();
	}

	/**
	 * Возвращает список всех подразделений
	 */
	@Override
	@WebMethod
	public AllDepartment getAllDepartment() {
		DepartmentManager dpMan = new DepartmentManager();
		AllDepartment ret = new AllDepartment();
		
		Session ses = sf.openSession();
		List<Department> list = dpMan.getDerartments(ses);
		ses.close();
		
		ret.departmetns = list;
		return ret;
	}
	
	@Override
	@WebMethod
	@Deprecated
	public Integer getNextVal() {
		return new Integer(keyval++);
	}

}
