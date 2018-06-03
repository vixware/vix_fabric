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
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.controler.WelfareArchivesController;
import com.vix.hr.job.entity.HrCategory;
import com.vix.hr.job.entity.WelfareArchives;
import com.vix.hr.job.service.IWelfareArchivesService;

/**
 * @Description: 福利档案
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class WelfareArchivesAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(WelfareArchives.class);

	/** 注入service */
	@Autowired
	private WelfareArchivesController welfareArchivesController;
	private List<WelfareArchives> welfareArchivesList;
	private WelfareArchives welfareArchives;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IWelfareArchivesService iWelfareArchivesService;
	private String id;

	public List<WelfareArchives> getWelfareArchivesList() {
		return welfareArchivesList;
	}

	public void setWelfareArchivesList(List<WelfareArchives> welfareArchivesList) {
		this.welfareArchivesList = welfareArchivesList;
	}

	public WelfareArchives getWelfareArchives() {
		return welfareArchives;
	}

	public void setWelfareArchives(WelfareArchives welfareArchives) {
		this.welfareArchives = welfareArchives;
	}

	public IWelfareArchivesService getiWelfareArchivesService() {
		return iWelfareArchivesService;
	}

	public void setiWelfareArchivesService(IWelfareArchivesService iWelfareArchivesService) {
		this.iWelfareArchivesService = iWelfareArchivesService;
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
			welfareArchivesList = welfareArchivesController.findWelfareArchivesIndex();
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

	/*
	 * public String goTopDynamicMenuContent() { return
	 * "goTopDynamicMenuContent"; }
	 */
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
			Pager pager = welfareArchivesController.goSingleList(params, getPager());
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
				pager = welfareArchivesController.goSearchList(params, getPager());
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
				pager = welfareArchivesController.goSingleList(params, getPager());
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
			Pager pager = welfareArchivesController.doSubSingleList(params, getPager());
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {// if (null !=
																// id &&
																// id.longValue()
																// > 0) {
				welfareArchives = welfareArchivesController.doListEntityById(id);
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
			if (null != welfareArchives.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(welfareArchives);
			welfareArchives = welfareArchivesController.doSaveWelfareArchives(welfareArchives);
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
			WelfareArchives welfareArchives = welfareArchivesController.doListEntityById(id);
			if (null != welfareArchives) {
				welfareArchivesController.deleteByEntity(welfareArchives);
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
				listCategory = iWelfareArchivesService.findAllSubEntity(HrCategory.class, "parentCategory.id", id, params);
			} else {
				listCategory = iWelfareArchivesService.findAllSubEntity(HrCategory.class, "parentCategory.id", null, params);
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
}
