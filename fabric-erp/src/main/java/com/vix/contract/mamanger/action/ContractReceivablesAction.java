package com.vix.contract.mamanger.action;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.contract.mamanger.controller.ContractPaymentPlanController;
import com.vix.contract.mamanger.entity.ContractPaymentPlan;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ContractReceivablesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractPaymentPlanController.class);
	@Autowired
	private ContractPaymentPlanController contractPaymentPlanController;
	private String id;
	private String pageNo;
	private ContractPaymentPlan contractPaymentPlan;
	private List<ContractPaymentPlan> contractPaymentPlanList;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			contractPaymentPlanList = contractPaymentPlanController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/*按最近使用*/
			String paymentTime = getRequestParameter("paymentTime");
			if (paymentTime != null && !"".equals(paymentTime)) {
				params.put("paymentTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(paymentTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("paymentTime");
				getPager().setOrderBy("desc");
			}
			/*uploadPersonId包含当前登录人id*/
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			Pager pager = contractPaymentPlanController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			/*合同编码*/
			String contractCode = getRequestParameter("contractCode");
			if (null != contractCode && !"".equals(contractCode)) {
				contractCode = URLDecoder.decode(contractCode, "utf-8");
			}
			/*合同名称*/
			String contractName = getRequestParameter("contractName");
			if (null != contractName && !"".equals(contractName)) {
				contractName = URLDecoder.decode(contractName, "utf-8");
			}
			/*收款单位名称*/
			String beneficiaryName = getRequestParameter("beneficiaryName");
			if (null != beneficiaryName && !"".equals(beneficiaryName)) {
				beneficiaryName = URLDecoder.decode(beneficiaryName, "utf-8");
			}
			/*付款人*/
			String paymentPeople = getRequestParameter("paymentPeople");
			if (null != paymentPeople && !"".equals(paymentPeople)) {
				paymentPeople = URLDecoder.decode(paymentPeople, "utf-8");
			}
			/*收款人*/
			String aprPerson = getRequestParameter("aprPerson");
			if (null != aprPerson && !"".equals(aprPerson)) {
				aprPerson = URLDecoder.decode(aprPerson, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contractName," + SearchCondition.ANYLIKE, contractName);
				pager = contractPaymentPlanController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contractCode && !"".equals(contractCode)) {
					params.put("contractCode," + SearchCondition.ANYLIKE, contractCode);
				}
				if (null != contractName && !"".equals(contractName)) {
					params.put("contractName," + SearchCondition.ANYLIKE, contractName);
				}
				if (null != beneficiaryName && !"".equals(beneficiaryName)) {
					params.put("beneficiaryName," + SearchCondition.ANYLIKE, beneficiaryName);
				}
				if (null != paymentPeople && !"".equals(paymentPeople)) {
					params.put("paymentPeople," + SearchCondition.ANYLIKE, paymentPeople);
				}
				if (null != aprPerson && !"".equals(aprPerson)) {
					params.put("aprPerson," + SearchCondition.ANYLIKE, aprPerson);
				}
				pager = contractPaymentPlanController.goSingleList(params, getPager());
			}
			logger.info("获取合同付款搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}

	/** 获取列表数据 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = contractPaymentPlanController.doSubSingleList(params,getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	// ** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				contractPaymentPlan = contractPaymentPlanController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (contractPaymentPlan != null && null != contractPaymentPlan.getId()) {
				isSave = false;
			}
			/**索引 */
			String title = contractPaymentPlan.getContractName();
			String py = ChnToPinYin.getPYString(title);
			contractPaymentPlan.setChineseFirstLetter(py.toUpperCase());
			
			contractPaymentPlan = contractPaymentPlanController.doSaveSalesOrder(contractPaymentPlan);
			initEntityBaseController.initEntityBaseAttribute(contractPaymentPlan);
			
			this.contractPaymentPlan.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.contractPaymentPlan.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			contractPaymentPlan.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.contractPaymentPlan);
			logger.info("对合同付款进行了修改！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			ContractPaymentPlan pb = contractPaymentPlanController.findEntityById(id);
			if (null != pb) {
				contractPaymentPlanController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseChkSimpleGrid() {
		return "goChooseChkSimpleGrid";
	}

	public String goChooseRadioSimpleGrid() {
		return "goChooseRadioSimpleGrid";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ContractPaymentPlan getContractPaymentPlan() {
		return contractPaymentPlan;
	}

	public void setContractPaymentPlan(ContractPaymentPlan contractPaymentPlan) {
		this.contractPaymentPlan = contractPaymentPlan;
	}

	public List<ContractPaymentPlan> getContractPaymentPlanList() {
		return contractPaymentPlanList;
	}

	public void setContractPaymentPlanList(
			List<ContractPaymentPlan> contractPaymentPlanList) {
		this.contractPaymentPlanList = contractPaymentPlanList;
	}

}
