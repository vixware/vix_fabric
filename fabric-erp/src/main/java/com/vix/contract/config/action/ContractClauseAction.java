package com.vix.contract.config.action;

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
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.contract.config.controller.ContractClauseController;
import com.vix.contract.config.entity.ContractClause;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ContractClauseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ContractClauseController.class);
	@Autowired
	private ContractClauseController contractClauseController;
	private String id;
	private String pageNo;
	private ContractClause contractClause;
	private Date updateTime;
	private List<ContractClause> contractClauseList;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			contractClauseList = contractClauseController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			String updateTime = getRequestParameter("updateTime");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
			Pager pager = contractClauseController.doSubSingleList(params,getPager());
			logger.info("获取待办合同列表数据");
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
			if (null != searchContent && !"".equals(searchContent)) {
				searchContent = URLDecoder.decode(searchContent, "utf-8");
			}
			// 名称
			String name = getRequestParameter("name");		
			//条款内容
			String clauseContent = getRequestParameter("clauseContent");			
			if (null != clauseContent && !"".equals(clauseContent)) {
				clauseContent = URLDecoder.decode(clauseContent, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {			
				params.put("clauseContent," + SearchCondition.ANYLIKE,searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = contractClauseController.goSearchList(params,getPager());
			}
			// 高级搜索
			else {
				//将内容(name)的值remove掉
				if(params.containsKey("name,anylike")){
					params.remove("name,anylike");
				}				
				if (null != name && !"".equals(name)) {
					params.put("contractCode," + SearchCondition.ANYLIKE,name);
				}			
				if (null != clauseContent && !"".equals(clauseContent)) {
					params.put("clauseContent," + SearchCondition.ANYLIKE,clauseContent);
				}
				pager = contractClauseController.goSingleList(params,getPager());
			}
			logger.info("获取合同条款列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}


	/** 获取列表数据 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = contractClauseController.doSubSingleList(params,getPager());
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
				contractClause = contractClauseController.doListEntityById(id);
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
			if (contractClause != null && null != contractClause.getId()) {
				isSave = false;
			}
			contractClause = contractClauseController.doSaveSalesOrder(contractClause);
			initEntityBaseController.initEntityBaseAttribute(contractClause);
			logger.info("对合同条款进行了修改！");
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
			ContractClause supplier = contractClauseController.doListEntityById(id);
			if (null != supplier) {
				contractClauseController.doDeleteByEntity(supplier);
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

	public ContractClause getContractClause() {
		return contractClause;
	}

	public void setContractClause(ContractClause contractClause) {
		this.contractClause = contractClause;
	}

	public List<ContractClause> getContractClauseList() {
		return contractClauseList;
	}

	public void setContractClauseList(List<ContractClause> contractClauseList) {
		this.contractClauseList = contractClauseList;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
