package com.vix.core.drools;

import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.StatelessSession;
import org.drools.compiler.PackageBuilder;

public class DroolsEngine implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private RuleBase ruleBase;
	
	public void initEngine(List<Reader> readerList) {
		// 设置时间格式
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		try {
			synchronized (this) {
				ruleBase =  RuleFactory.getRuleBase();
				PackageBuilder backageBuilder = new PackageBuilder();
				for (Reader r : readerList) {
					backageBuilder.addPackageFromDrl(r);
				}
				ruleBase.addPackages(backageBuilder.getPackages());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public StatelessSession getStatelessSession(){
		return ruleBase.newStatelessSession();
	}
	
	public StatefulSession getStatefulSession(){
		return ruleBase.newStatefulSession();
	}
	
	public boolean isInit(){
		if(null == ruleBase){
			return false;
		}else{
			return true;
		}
	}
}
