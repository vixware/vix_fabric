package com.vix.core.drools;

import java.io.Serializable;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

public class RuleFactory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static RuleBase ruleBase;
	
	public static RuleBase getRuleBase(){
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}
