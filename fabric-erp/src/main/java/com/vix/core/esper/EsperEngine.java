package com.vix.core.esper;

import java.io.Serializable;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class EsperEngine implements Serializable{

	private static final long serialVersionUID = 1L;

	public static void execute(String packageName,String expression,UpdateListener listener,Object entity){
		Configuration config= new Configuration();  
        config.addEventTypeAutoName(packageName);//添加包路径，这样在查询表达式中就不需要写类的全路径了  
        //Creating a statement  
        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);  
        EPStatement statement = epService.getEPAdministrator().createEPL(expression);  
        //Adding a listener  
        statement.addListener(listener);  
        epService.getEPRuntime().sendEvent(entity);  
	}
}
