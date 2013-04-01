/*
 * author: prokofiev_aa
 */
package name.prokofiev.accauntingservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import name.prokofiev.accauntingservice.jaxws.AllDepartment;
import name.prokofiev.accauntingservice.jaxws.TSResult;
import name.prokofiev.accauntingservice.jaxws.TimeSheetData;

/**
 * Основной интерфейс сервиса
 */
@WebService
public interface Ultima {
	
	@WebMethod(operationName = "setTimeSheet", action = "urn:SetTimeSheet")
	public TSResult setTimeSheet(@WebParam(name="arg0") TimeSheetData ts);

	@WebMethod(operationName = "getAllDepartment", action = "urn:GetAllDepartment")
	public AllDepartment getAllDepartment();
	
	public Integer getNextVal();
}
