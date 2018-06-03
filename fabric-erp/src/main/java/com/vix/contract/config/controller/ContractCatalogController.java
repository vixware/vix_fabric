package com.vix.contract.config.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.contract.config.domain.ContractCatalogDomain;
import com.vix.core.web.Pager;

/**
 * @ClassName: 
 * @Description: 完成对前端业务请求的参数处理和理解,并调用Domain层对象
 */
@Controller("contractCatalogController")
@Scope("prototype")
public class ContractCatalogController extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	/** 键值从配置文件获取 */
	public static String industryIdentify = "GeneralIndustry";

	Logger logger = Logger.getLogger("ContractCatalogController");

	@Autowired
	private ContractCatalogDomain contractCatalogDomain;
	
	/** 获取列表数据 */
	public Pager goSingleList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractCatalogDomain.findPagerByHqlConditions(params, pager);
		return p;
	}
	/** 获取列表数据 */
	public Pager goSearchList(Map<String, Object> params, Pager pager)throws Exception {
		Pager p = contractCatalogDomain.findPagerByOrHqlConditions(params, pager);
		return p;
	}

}
