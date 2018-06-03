/**
 * 
 */
package com.vix.system.code.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.code.AutoCreateCode;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsType;
import com.vix.system.code.domain.CodeDomain;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("codeController")
@Scope("prototype")
public class CodeController {

	Logger logger = Logger.getLogger(CodeController.class);

	@Autowired
	private CodeDomain codeDomain;
	/**
	 * 自动生成编码
	 */
	@Autowired
	private AutoCreateCode autoCreateCode;

	/**
	 * 
	 * @param baseCoder
	 * @return
	 * @throws Exception
	 */
	public EncodingRulesTableInTheMiddle doSaveEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {
		EncodingRulesTableInTheMiddle baseCoder1 = null;
		/* 拼接前缀字符串 */
		StringBuffer sequenceID = new StringBuffer();
		if (encodingRulesTableInTheMiddle.getLevel1value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel1value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel1value());
		}
		if (encodingRulesTableInTheMiddle.getLevel2value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel2value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel2value());
		}
		if (encodingRulesTableInTheMiddle.getLevel3value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel3value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel3value());
		}
		if (encodingRulesTableInTheMiddle.getLevel4value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel4value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel4value());
		}
		if (encodingRulesTableInTheMiddle.getLevel5value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel5value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel5value());
		}
		if (encodingRulesTableInTheMiddle.getLevel6value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel6value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel6value());
		}
		if (encodingRulesTableInTheMiddle.getLevel7value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel7value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel7value());
		}
		if (encodingRulesTableInTheMiddle.getLevel8value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel8value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel8value());
		}
		if (encodingRulesTableInTheMiddle.getLevel9value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel9value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel9value());
		}
		if (encodingRulesTableInTheMiddle.getLevel10value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel10value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel10value());
		}
		if (encodingRulesTableInTheMiddle.getLevel11value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel11value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel11value());
		}
		if (encodingRulesTableInTheMiddle.getLevel12value() != null && !"".equals(encodingRulesTableInTheMiddle.getLevel12value())) {
			sequenceID.append(encodingRulesTableInTheMiddle.getLevel12value());
		}
		if (encodingRulesTableInTheMiddle.getSerialNumberBegin() != null && !"".equals(encodingRulesTableInTheMiddle.getSerialNumberBegin())) {
			encodingRulesTableInTheMiddle.setInitValue(encodingRulesTableInTheMiddle.getSerialNumberBegin());
		}
		encodingRulesTableInTheMiddle.setSequenceID(sequenceID.toString());
		baseCoder1 = codeDomain.mergeEncodingRulesTableInTheMiddle(encodingRulesTableInTheMiddle);
		if (baseCoder1.getCodingPreview() != null && !"".equals(baseCoder1.getCodingPreview())) {
		} else {
			if (baseCoder1 != null && baseCoder1.getBillType() != null) {
				baseCoder1.setCodingPreview(autoCreateCode.getBillNO(baseCoder1.getBillType()));
				baseCoder1 = codeDomain.mergeEncodingRulesTableInTheMiddle(baseCoder1);
			}
		}
		return baseCoder1;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EncodingRulesTableInTheMiddle doListEncodingRulesTableInTheMiddleByAttribute(String billTypeCode) throws Exception {
		EncodingRulesTableInTheMiddle

		// 3.执行查询操作
		encodingRulesTableInTheMiddle = codeDomain.findEncodingRulesTableInTheMiddleByAttribute(billTypeCode);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return encodingRulesTableInTheMiddle;
	}

	public BillsType doListBillsTypeById(String id) throws Exception {
		BillsType billsType = null;

		// 3.执行查询操作
		billsType = codeDomain.findBillsType(id);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return billsType;
	}

	public EncodingRulesTableInTheMiddle doListEncodingRulesTableInTheMiddleById(String id) throws Exception {
		EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = null;

		// 3.执行查询操作
		encodingRulesTableInTheMiddle = codeDomain.findEncodingRulesTableInTheMiddle(id);
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return encodingRulesTableInTheMiddle;
	}

	public void doDeleteByEntity(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {

		// 3. 执行删除操作
		codeDomain.deleteByEntity(encodingRulesTableInTheMiddle);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
	}

	/**
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<EncodingRulesTableInTheMiddle> doListEncodingRulesTableInTheMiddleIndex() throws Exception {
		List<EncodingRulesTableInTheMiddle> encodingRulesTableInTheMiddle = null;

		// 3. 执行查询操作
		encodingRulesTableInTheMiddle = codeDomain.findEncodingRulesTableInTheMiddleIndex();
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "");
		return encodingRulesTableInTheMiddle;
	}

	public List<BillsType> doListBillsTypeList() throws Exception {
		List<BillsType> billsTypeList = null;

		// 3. 执行查询操作
		billsTypeList = codeDomain.findBillsTypeList();
		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return billsTypeList;
	}

	public Pager doListEncodingRulesTableInTheMiddle(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；

		// 3.执行保存操作
		p = codeDomain.findPagerByHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	/**
	 * 异常错误日志方便程序员调试
	 * 
	 * @param message
	 */
	public void executeErrorLogger(String... errorMessage) {
	}

	/**
	 * bizFlowExecute 需要对流程进行操作时调用
	 * 
	 * @param flowName
	 * @param flowParameter
	 */
	protected void bizFlowExecute(String flowName, Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName, Map<String, Object> flowParameter) {
		// to do something

	}

	/**
 * 
 */
	public void doProcessEvent() {
		// to do something

	}

	/**
	 * 
	 */
	public void doExecute() {
		// to do something
	}

	/**
	 * 
	 * @return
	 */
	public Object getIndustryOrderAction() {
		return null;
	}

	/**
	 * 
	 */
	public void doPrint() {
		// to do something
	}

	/**
	 * 
	 */
	public void doDrools() {
		// to do something
	}
}
