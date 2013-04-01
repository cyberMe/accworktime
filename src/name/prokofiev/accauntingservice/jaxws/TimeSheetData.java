/*
 * author: prokofiev_aa
 */
package name.prokofiev.accauntingservice.jaxws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Передает новые часы для табеля пользователя
 */
@XmlRootElement
public class TimeSheetData {
	
	/** Идентификатор табеля*/
	@XmlElement
	public int tsId;
	
	/** Код подразделения, к которому относится табель*/
	@XmlElement
	public String departmentCode;
	
	@XmlElement
	public int modayTime;
	@XmlElement
	public int thuesdayTime;
	@XmlElement
	public int wedsdayTime;
	@XmlElement
	public int thursdayTime;
	@XmlElement
	public int fridayTime;
	@XmlElement
	public int saturdayTime;
	@XmlElement
	public int sundayTime;
}
