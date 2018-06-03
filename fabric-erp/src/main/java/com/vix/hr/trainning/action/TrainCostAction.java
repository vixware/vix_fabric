package com.vix.hr.trainning.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.trainning.controller.TrainingExpensesController;
import com.vix.hr.trainning.entity.TrainingExpenses;
import com.vix.hr.trainning.service.ITrainingExpensesService;

@Controller
@Scope("prototype")
public class TrainCostAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingExpenses.class);

	/** 注入service */
	@Autowired
	private TrainingExpensesController trainingExpensesController;
	private List<TrainingExpenses> trainingExpenseList;
	private TrainingExpenses trainingExpenses;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITrainingExpensesService iTrainingExpensesService;
	private String id;

	private String pageNo;
	private String parentId;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<TrainingExpenses> getTrainingExpenseList() {
		return trainingExpenseList;
	}

	public void setTrainingExpenseList(List<TrainingExpenses> trainingExpenseList) {
		this.trainingExpenseList = trainingExpenseList;
	}

	public TrainingExpenses getTrainingExpenses() {
		return trainingExpenses;
	}

	public void setTrainingExpenses(TrainingExpenses trainingExpenses) {
		this.trainingExpenses = trainingExpenses;
	}

	public ITrainingExpensesService getiTrainingExpensesService() {
		return iTrainingExpensesService;
	}

	public void setiTrainingExpensesService(ITrainingExpensesService iTrainingExpensesService) {
		this.iTrainingExpensesService = iTrainingExpensesService;
	}

	@Override
	public String goList() {
		try {
			trainingExpenseList = trainingExpensesController.findTrainingExpensesIndex();
		} catch (Exception e) {
			// TODO: handle exception
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

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			String status = getRequestParameter("status");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 按状态查询
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			if (null != parentId && !"".equals(parentId)) {
				params.put("organizationUnit.id," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = trainingExpensesController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
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
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 姓名
			String sName = getRequestParameter("sName");
			// 部门
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("applicantsName," + SearchCondition.ANYLIKE, searchContent);
				params.put("employmentObjective," + SearchCondition.ANYLIKE, searchContent);
				params.put("applicantsDepartment," + SearchCondition.ANYLIKE, searchContent);
				pager = trainingExpensesController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("applicantsDepartment," + SearchCondition.ANYLIKE, contacts);
				}
				if (null != sName && !"".equals(sName)) {
					params.put("applicantsName," + SearchCondition.ANYLIKE, sName);
				}
				params.remove("name");
				pager = trainingExpensesController.goSingleList(params, getPager());
			}
			logger.info("获取列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据，跳转 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = trainingExpensesController.doSubSingleList(params, getPager());
			logger.info("");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				trainingExpenses = trainingExpensesController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != trainingExpenses.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainingExpenses);
			trainingExpenses = trainingExpensesController.doSaveTrainingExpenses(trainingExpenses);
			logger.info("对订单进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			TrainingExpenses trainingExpenses = trainingExpensesController.doListEntityById(id);
			if (null != trainingExpenses) {
				trainingExpensesController.doDeleteByEntity(trainingExpenses);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 弹框选择组织
	public void findTreeToJson() {
		try {
			List<HrCategory> listCategory = new ArrayList<HrCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iTrainingExpensesService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iTrainingExpensesService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				HrCategory cc = listCategory.get(i);
				if (cc.getSubCategorys().size() > 0) {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 弹出选择部门，职位窗体 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}
}
