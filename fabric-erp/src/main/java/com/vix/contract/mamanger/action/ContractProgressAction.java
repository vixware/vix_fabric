package com.vix.contract.mamanger.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.ExchangeRate;
import com.vix.contract.contractInquiry.controller.ContractInquiryController;
import com.vix.contract.contractInquiry.entity.ContractChildItem;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;

/**
 * 
 * @ClassName: ContractProgressAction
 * @Description: 合同进度
 * @author bobchen
 * @date 2015年12月30日 下午4:25:16
 *
 */
@Controller
@Scope("prototype")
public class ContractProgressAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractInquiry.class);
	/** 注入service */
	@Autowired
	private ContractInquiryController contractInquiryController;
	/*
	 * @Autowired private IUseCache useCache;
	 */
	private List<ContractInquiry> contractInquiryList;
	private ContractInquiry contractInquiry;
	/** 合同子项 */
	private ContractChildItem childItem;
	/** 合同标的 */
	private ContractSubject subject;
	/** 合同预警 */
	private ContractWarning warning;
	/** 合同审批单 */
	private ApplicationForm applications;
	/** 价格条件 */
	private PriceConditions price;
	/** 合同采购定价条件 */
	private ContractPricingConditions pricingConditions;
	/** 销售定价条件 */
	private ContractMarket market;
	private Item item;
	private String id;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;
	private String parentId;
	private String pageNo;
	public Integer contractType;

	/** 汇率 */
	private List<ExchangeRate> exchangeRateList;

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String goList() {
		try {
			contractInquiryList = contractInquiryController.findContractIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 类型 */
			String contractType = getRequestParameter("contractType");
			if (null != contractType && !"".equals(contractType)) {
				params.put("contractType," + SearchCondition.EQUAL, Integer.parseInt(contractType));
			}
			/* 按最近使用 */
			String contractStartTime = getRequestParameter("contractStartTime");
			if (contractStartTime != null && !"".equals(contractStartTime)) {
				params.put("contractStartTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(contractStartTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("contractStartTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = contractInquiryController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/* 主合同编码 */
			String masterContractCoding = getRequestParameter("masterContractCoding");
			if (null != masterContractCoding && !"".equals(masterContractCoding)) {
				masterContractCoding = URLDecoder.decode(masterContractCoding, "utf-8");
			}
			/* 合同编码 */
			String contractCode = getRequestParameter("contractCode");
			if (null != contractCode && !"".equals(contractCode)) {
				contractCode = URLDecoder.decode(contractCode, "utf-8");
			}
			/* 合同名称 */
			String contractName = getRequestParameter("contractName");
			if (null != contractName && !"".equals(contractName)) {
				contractName = URLDecoder.decode(contractName, "utf-8");
			}
			/* 项目代码 */
			String projectCode = getRequestParameter("projectCode");
			if (null != projectCode && !"".equals(projectCode)) {
				projectCode = URLDecoder.decode(projectCode, "utf-8");
			}
			/* 项目名称 */
			String projectName = getRequestParameter("projectName");
			if (null != projectName && !"".equals(projectName)) {
				projectName = URLDecoder.decode(projectName, "utf-8");
			}
			/* 所属部门 */
			String departmentName = getRequestParameter("departmentName");
			if (null != departmentName && !"".equals(departmentName)) {
				departmentName = URLDecoder.decode(departmentName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contractName," + SearchCondition.ANYLIKE, contractName);
				pager = contractInquiryController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != masterContractCoding && !"".equals(masterContractCoding)) {
					params.put("masterContractCoding," + SearchCondition.ANYLIKE, masterContractCoding);
				}
				if (null != contractCode && !"".equals(contractCode)) {
					params.put("contractCode," + SearchCondition.ANYLIKE, contractCode);
				}
				if (null != contractName && !"".equals(contractName)) {
					params.put("contractName," + SearchCondition.ANYLIKE, contractName);
				}
				if (null != projectCode && !"".equals(projectCode)) {
					params.put("projectCode," + SearchCondition.ANYLIKE, projectCode);
				}
				if (null != projectName && !"".equals(projectName)) {
					params.put("projectName," + SearchCondition.ANYLIKE, projectName);
				}
				if (null != departmentName && !"".equals(departmentName)) {
					params.put("departmentName," + SearchCondition.ANYLIKE, departmentName);
				}
				pager = contractInquiryController.goSingleList(params, getPager());
			}
			logger.info("获取合同查询搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至采购合同修改页面 */
	public String goSaveOrUpdate() {
		try {
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至采购框架协议修改页面 */
	public String goPurchaseFA() {
		try {
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseFA";
	}

	/** 跳转至销售合同修改页面 */
	public String goSalesContract() {
		try {
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesContract";
	}

	/** 跳转至销售定价条件修改页面 */
	public String goContractMarket() {
		try {
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goContractMarket";
	}

	/** 跳转至项目合同修改页面 */
	public String goPmContract() {
		try {
			exchangeRateList = baseHibernateService.findAllByEntityClass(ExchangeRate.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				contractInquiry = contractInquiryController.doListEntityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPmContract";
	}

	/************************************************************* 合同子项 *******************************************************************/
	/** 获取合同子项明细json数据 */
	public void getContractLineJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractChildItem>(ci.getContractChildItems()), ci.getContractChildItems().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************************** 合同标的 **************************************************************************/
	/** 获取合同标的明细json数据 */
	public void getContractSubjectJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractSubject>(ci.getContractSubjects()), ci.getContractSubjects().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************************** 合同预警 **************************************************************************/
	/** 获取合同预警明细json数据 */
	public void getContractWarningJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractWarning>(ci.getContractWarnings()), ci.getContractWarnings().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************************** 合同审批单 **************************************************************************/
	/** 获取合同审批明细json数据 */
	public void getApplicationFormJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ApplicationForm>(ci.getApplicationForms()), ci.getApplicationForms().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************************** 价格条件 **************************************************************************/
	/** 获取价格条件明细json数据 */
	public void getPriceConditionsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<PriceConditions>(ci.getPriceConditionss()), ci.getPriceConditionss().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************************** 采购定价条件 **************************************************************************/
	/** 获取采购定价条件明细json数据 */
	public void getContractPricingConditionsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractPricingConditions>(ci.getContractPricingConditionss()), ci.getContractPricingConditionss().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/****************************************************** 销售定价条件 **************************************************************************/
	/** 获取采销售定价条件明细json数据 */
	public void getContractMarketJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				ContractInquiry ci = contractInquiryController.findEntityById(id);
				if (ci != null) {
					json = convertListToJson(new ArrayList<ContractMarket>(ci.getContractMarkets()), ci.getContractMarkets().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/********************************************************************************************************************************/
	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = baseHibernateService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ContractInquiry> getContractInquiryList() {
		return contractInquiryList;
	}

	public void setContractInquiryList(List<ContractInquiry> contractInquiryList) {
		this.contractInquiryList = contractInquiryList;
	}


	public ContractChildItem getChildItem() {
		return childItem;
	}

	public void setChildItem(ContractChildItem childItem) {
		this.childItem = childItem;
	}

	public ContractInquiry getContractInquiry() {
		return contractInquiry;
	}

	public void setContractInquiry(ContractInquiry contractInquiry) {
		this.contractInquiry = contractInquiry;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<ExchangeRate> getExchangeRateList() {
		return exchangeRateList;
	}

	public void setExchangeRateList(List<ExchangeRate> exchangeRateList) {
		this.exchangeRateList = exchangeRateList;
	}

	public ContractSubject getSubject() {
		return subject;
	}

	public void setSubject(ContractSubject subject) {
		this.subject = subject;
	}

	public ContractWarning getWarning() {
		return warning;
	}

	public void setWarning(ContractWarning warning) {
		this.warning = warning;
	}

	public ApplicationForm getApplications() {
		return applications;
	}

	public void setApplications(ApplicationForm applications) {
		this.applications = applications;
	}

	public PriceConditions getPrice() {
		return price;
	}

	public void setPrice(PriceConditions price) {
		this.price = price;
	}

	public ContractPricingConditions getPricingConditions() {
		return pricingConditions;
	}

	public void setPricingConditions(ContractPricingConditions pricingConditions) {
		this.pricingConditions = pricingConditions;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ContractMarket getMarket() {
		return market;
	}

	public void setMarket(ContractMarket market) {
		this.market = market;
	}

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

}