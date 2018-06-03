package com.vix.contract.mamanger.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.contract.mamanger.controller.ContractController;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

/**
 * 合同查询
 */
@Controller
@Scope("prototype")
public class ContractInquiriesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractController.class);
	/** 注入service */
	@Autowired
	private ContractController contractController;
	private List<Contract> contractList;
	private String id;
	private String pageNo;
	private Contract contract;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			contractList = contractController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String contractName = getRequestParameter("contractName");
			String operator = getRequestParameter("operator");
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 记录当前页面
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			if (null != contractName && !"".equals(contractName)) {
				params.put("contractName," + SearchCondition.ANYLIKE,contractName);
			}
			if (null != operator && !"".equals(operator)) {
				params.put("operator," + SearchCondition.ANYLIKE, operator);
			}
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
			Pager pager = contractController.doSubSingleList(params, getPager());
			logger.info("获取合同查询列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 计算最近使用日期 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
	}

	/** 获取搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 经办人
			String operator = getRequestParameter("operator");
			String code = getRequestParameter("code");
			// 批准人
			String approval = getRequestParameter("approval");			
			if (null != approval && !"".equals(approval)) {
				approval = URLDecoder.decode(approval, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("contractName," + SearchCondition.ANYLIKE,searchContent);
				params.put("operator," + SearchCondition.ANYLIKE, searchContent);
				params.put("approval," + SearchCondition.ANYLIKE, searchContent);
				pager = contractController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {					
				if (null != approval && !"".equals(approval)) {
					params.put("approval," + SearchCondition.ANYLIKE, approval);
				}
				if (null != operator && !"".equals(operator)) {
					params.put("operator," + SearchCondition.ANYLIKE, operator);
				}
				if (null != code && !"".equals(code)) {
					params.put("contractName," + SearchCondition.ANYLIKE, code);
				}
				pager = contractController.goSingleList(params, getPager());
			}
			logger.info("获取合同查询列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = contractController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Contract supplier = contractController.doListEntityById(id);
			if (null != supplier) {
				contractController.doDeleteByEntity(supplier);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除合同信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
