package com.vix.common.base;

import java.util.Map;

public interface IBizObjectProcess {

	abstract public void doSave(Map<String,Object> parameters) throws Exception;
	abstract public void doApproval(Map<String,Object> parameters) throws Exception;
	abstract public void doEdit(Map<String,Object> parameters) throws Exception;
	
}
