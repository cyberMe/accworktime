/*
 * author prokofiev_aa
 */
package name.prokofiev.accauntingdb.entities;

import javax.persistence.*;

/**
 * Сущность Сотрудник
 */
@Entity
public class Employee {

	/** Идентификатор */
	@Id @GeneratedValue
	private Integer employeeId;
	
	/** Имя */
	@Column(length = 100, nullable=false) 
	private String name;
	
	/** Код сотрудника */
	@Column(length = 1)
	private String employeeCode;
	
	/** Пароль */
	@Column(length = 10, nullable=false)
	private String password;

	@Column
	private Integer managerEmployeeId;
	
	/** Почта */
	@Column(length = 255)
	private String email;

	public int getEmployeeId() {
		return employeeId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
