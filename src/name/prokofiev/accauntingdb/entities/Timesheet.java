/**
 * @author prokofiev_aa
 */

package name.prokofiev.accauntingdb.entities;

import java.util.*;
import javax.persistence.*;

/**
 * Сущность Табель времени
 */
@Entity
public class Timesheet {
	@Id @GeneratedValue
	private Integer timesheetId;
	
	/**
	 * Владелец табеля
	 */
	@ManyToOne
	@JoinColumn(name="employeeId")
	public Employee employeeId;
	
	/**
	 * Статус: принят или отклонен менеджером
	 * 'y' - принят. иначе - отклонен
	 * NULL - ожидает решения
	 */
	@Column(length = 1)
	public String statusCode;
	
	/**
	 * Связанное подразделение
	 */
	@ManyToOne
	@JoinColumn(name="departmentCode")
	public Department departmentCode;
	
	/**
	 * Последний день недели, за которую предоставляется табель
	 */
	@Column
	public Date periodEndingDate;
	
	/**
	 * потраченное время в минутах
	 */
	@Column(nullable=false)
	public Integer minutesMon;
	
	@Column(nullable=false)
	public Integer minutesTue;
	
	@Column(nullable=false)
	public Integer minutesWed;
	
	@Column(nullable=false)
	public Integer minutesThu;
	
	@Column(nullable=false)
	public Integer minutesFri;
	
	@Column(nullable=false)
	public Integer minutesSat;
	
	@Column(nullable=false)
	public Integer minutesSun;
	
	public Timesheet() {
		minutesMon = 0;
		minutesTue = 0;
		minutesWed = 0;
		minutesThu = 0;
		minutesFri = 0;
		minutesSat = 0;
		minutesSun = 0;
	}
}
