package com.vix.hr.job.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.controler.RecruitactivitityController;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.job.entity.HrRecruitactivitity;
import com.vix.hr.job.entity.HrRecruitactivitityDetails;
import com.vix.hr.job.service.IRecruitactivitityService;

/**
 * @Description: 职务体系
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class SalaryInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(HrRecruitactivitity.class);
	/** 注入Service */
	@Autowired
	private RecruitactivitityController recruitactivitityController;
	private List<HrRecruitactivitity> hrrecruitactivititiesList;
	private HrRecruitactivitity hrRecruitactivitity;
	@Autowired
	private IRecruitactivitityService iRecruitactivitityService;
	private HrRecruitactivitityDetails details;

	private String id;
	private List<HrRecruitactivitity> hrRecruitactivitities;

	public IRecruitactivitityService getiRecruitactivitityService() {
		return iRecruitactivitityService;
	}

	public void setiRecruitactivitityService(IRecruitactivitityService iRecruitactivitityService) {
		this.iRecruitactivitityService = iRecruitactivitityService;
	}

	public List<HrRecruitactivitity> getHrrecruitactivititiesList() {
		return hrrecruitactivititiesList;
	}

	public void setHrrecruitactivititiesList(List<HrRecruitactivitity> hrrecruitactivititiesList) {
		this.hrrecruitactivititiesList = hrrecruitactivititiesList;
	}

	public HrRecruitactivitity getHrRecruitactivitity() {
		return hrRecruitactivitity;
	}

	public void setHrRecruitactivitity(HrRecruitactivitity hrRecruitactivitity) {
		this.hrRecruitactivitity = hrRecruitactivitity;
	}

	@Override
	public String goList() {
		try {
			hrrecruitactivititiesList = recruitactivitityController.findRecruitactivitityIndex();
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

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			String status = getRequestParameter("status");
			String updateTime = getRequestParameter("updateTime");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// 按状态查询
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
			Pager pager = recruitactivitityController.goSingleList(params, getPager());
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
			// 内容
			String searchContent = getRequestParameter("searchContent");
			// 活动名称
			String sName = getRequestParameter("sName");
			// 活动地点
			String contacts = getRequestParameter("contacts");
			if (null != contacts && !"".equals(contacts)) {
				contacts = URLDecoder.decode(contacts, "utf-8");
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("activityName," + SearchCondition.ANYLIKE, searchContent);
				params.put("activityAddress," + SearchCondition.ANYLIKE, searchContent);
				params.put("recruitingObject," + SearchCondition.ANYLIKE, searchContent);
				params.put("activitystartDate," + SearchCondition.ANYLIKE, searchContent);
				params.put("activityendDate," + SearchCondition.ANYLIKE, searchContent);
				pager = recruitactivitityController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != contacts && !"".equals(contacts)) {
					params.put("recruitingObject," + SearchCondition.ANYLIKE, contacts);
				}
				if (null != sName && !"".equals(sName)) {
					params.put("activityName," + SearchCondition.ANYLIKE, sName);
				}
				params.remove("name");
				pager = recruitactivitityController.goSingleList(params, getPager());
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
			Pager pager = recruitactivitityController.doSubSingleList(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitactivitity = recruitactivitityController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 获取招聘活动明细json数据 */
	public void getHrRecruitactivitityDetailsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				HrRecruitactivitity hr = recruitactivitityController.findEntityById(id);
				List<HrRecruitactivitityDetails> hrTemp = new ArrayList<HrRecruitactivitityDetails>(hr.getHrRecruitactivitityDetails());
				for (HrRecruitactivitityDetails var : hrTemp) {
					if ("1".equals(var.getPublicationType())) {
						var.setPublicationType("内部");
					}
					if ("2".equals(var.getPublicationType())) {
						var.setPublicationType("外部");
					}
					if ("3".equals(var.getPublicationType())) {
						var.setPublicationType("内部和外部");
					}
					if ("1".equals(var.getPublicationStruts())) {
						var.setPublicationStruts("已发布");
					}
					if ("2".equals(var.getPublicationStruts())) {
						var.setPublicationStruts("未发布");
					}
				}
				json = convertListToJson(hrTemp, hrTemp.size(), "hrRecruitactivitity");
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

	/**
	 * 招聘明细
	 * 
	 * @return
	 */
	public String saveOrUpdateHrRecruitactivitityDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrRecruitactivitity = recruitactivitityController.doListEntityById(id);
				if (hrRecruitactivitity != null) {
					details.setHrRecruitactivitity(hrRecruitactivitity);
				}
				recruitactivitityController.doSaveHrRecruitactivitityDetails(details);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}

	/** 跳转到招聘计划明细页面 */
	public String goAddHrRecruitactivitityDetails() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				// hrRecruitplan=rePlanController.doListEntityById(id);
				details = recruitactivitityController.doListHrRecruitactivitityDetailsById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddHrRecruitactivitityDetails";
	}

	/** 处理修改/保存操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != hrRecruitactivitity.getId()) {
				isSave = false;
			}
			hrRecruitactivitity = recruitactivitityController.doSaveRecruitactivitity(hrRecruitactivitity);
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
			HrRecruitactivitity hrRecruitactivitity = recruitactivitityController.doListEntityById(id);
			if (null != hrRecruitactivitity) {
				recruitactivitityController.doDeleteByEntity(hrRecruitactivitity);
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

	/** 处理删除tab项招聘活动明细明细操作 */
	public String deleteHrRecruitactivitityDetailsById() {
		try {
			HrRecruitactivitityDetails hrRecruitactivitityDetails = recruitactivitityController.doListHrRecruitactivitityDetailsById(id);
			if (null != hrRecruitactivitityDetails) {
				recruitactivitityController.deleteHrRecruitactivitityDetailsDetailsEntity(hrRecruitactivitityDetails);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
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
				listCategory = iRecruitactivitityService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iRecruitactivitityService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<HrRecruitactivitity> getHrRecruitactivitities() {
		return hrRecruitactivitities;
	}

	public void setHrRecruitactivitities(List<HrRecruitactivitity> hrRecruitactivitities) {
		this.hrRecruitactivitities = hrRecruitactivitities;
	}

	public HrRecruitactivitityDetails getDetails() {
		return details;
	}

	public void setDetails(HrRecruitactivitityDetails details) {
		this.details = details;
	}

}
