/*
 * author prokofiev_aa
 */
package name.prokofiev.accauntingservice.jaxws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Результат отправки запроса сохранения ведомости
 */
@XmlType(name="TSResult")
public class TSResult {
	
	/** сообщение об ошибке*/
	private String result;
	
	/** признак успешного завершения операции*/
	private boolean updateOk = true; 
	
	@XmlElement
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@XmlElement
	public boolean isUpdateOk() {
		return updateOk;
	}

	public void setUpdateOk(boolean updateOk) {
		this.updateOk = updateOk;
	}

	public TSResult() {
	}
	
	public TSResult(boolean ok, String message) {
		this.updateOk = ok;
		this.result = message;
	}
}
