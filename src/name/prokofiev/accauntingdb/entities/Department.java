/*
 * author prokofiev_aa
 */
package name.prokofiev.accauntingdb.entities;

import javax.persistence.*;

/**
 * Сущность Подразделение
 */
@Entity
public class Department {

	/** Код подразделения */
	@Id @Column(length=2)
	private String departmentCode;
	
	/** Наименование подразделения */
	@Column(length = 255, nullable = false)
	private String name;

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;		
	}

	public String getDepartmentCode() {
		return departmentCode;
	}
	
	public void setName(String name) {
		this.name = name;		
	}

	public String getName() {
		return name;
	}	
}
