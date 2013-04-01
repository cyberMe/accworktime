/*
 * author prokofiev_aa
 */
package name.prokofiev.accauntingservice.jaxws;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import name.prokofiev.accauntingdb.entities.Department;

/**
 * Служит для передачи списка всех подразделений в клиент
 */
@XmlRootElement
public class AllDepartment {
	
	@XmlElement
	public List<Department> departmetns;
}
