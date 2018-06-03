package com.vix.hr.job.action;

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
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.Organization;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.initialSetup.entity.Proficiency;
import com.vix.hr.initialSetup.entity.TechnicalLevel;
import com.vix.hr.initialSetup.entity.TitleGrade;
import com.vix.hr.initialSetup.entity.TitleType;
import com.vix.hr.job.controler.TitleSysController;
import com.vix.hr.job.entity.HrTitleSys;
import com.vix.hr.job.service.ITitleSysService;

/**
 * @Description: 职称体系
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class TitleSysAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(HrTitleSys.class);

	/** 注入service */
	@Autowired
	private TitleSysController titleSysController;
	private List<HrTitleSys> hrTitleSysList;
	private HrTitleSys hrTitleSys;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private ITitleSysService iTitleSysService;
	private String id;
	private String pageNo;
	/** 职称类型 */
	private List<TitleType> titleTypeList;
	/** 职称等级 */
	private List<TitleGrade> titleGradeList;
	/** 技术水平 */
	private List<TechnicalLevel> technicalLevelList;
	/** 熟练程度 */
	private List<Proficiency> proficiencyList;

	public List<HrTitleSys> getHrTitleSysList() {
		return hrTitleSysList;
	}

	public void setHrTitleSysList(List<HrTitleSys> hrTitleSysList) {
		this.hrTitleSysList = hrTitleSysList;
	}

	public HrTitleSys getHrTitleSys() {
		return hrTitleSys;
	}

	public void setHrTitleSys(HrTitleSys hrTitleSys) {
		this.hrTitleSys = hrTitleSys;
	}

	public ITitleSysService getiTitleSysService() {
		return iTitleSysService;
	}

	public void setiTitleSysService(ITitleSysService iTitleSysService) {
		this.iTitleSysService = iTitleSysService;
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

	public TitleSysController getTitleSysController() {
		return titleSysController;
	}

	public void setTitleSysController(TitleSysController titleSysController) {
		this.titleSysController = titleSysController;
	}

	public List<TitleType> getTitleTypeList() {
		return titleTypeList;
	}

	public void setTitleTypeList(List<TitleType> titleTypeList) {
		this.titleTypeList = titleTypeList;
	}

	public List<TitleGrade> getTitleGradeList() {
		return titleGradeList;
	}

	public void setTitleGradeList(List<TitleGrade> titleGradeList) {
		this.titleGradeList = titleGradeList;
	}

	public List<TechnicalLevel> getTechnicalLevelList() {
		return technicalLevelList;
	}

	public void setTechnicalLevelList(List<TechnicalLevel> technicalLevelList) {
		this.technicalLevelList = technicalLevelList;
	}

	public List<Proficiency> getProficiencyList() {
		return proficiencyList;
	}

	public void setProficiencyList(List<Proficiency> proficiencyList) {
		this.proficiencyList = proficiencyList;
	}

	@Override
	public String goList() {
		try {
			hrTitleSysList = titleSysController.findTitleSysIndex();
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

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 按最近使用 */
			String startTime = getRequestParameter("startTime");
			if (startTime != null && !"".equals(startTime)) {
				params.put("startTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(startTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = titleSysController.goSingleList(params, getPager());
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
			/* 职称编码 */
			String titlesysCode = getRequestParameter("titlesysCode");
			if (null != titlesysCode && !"".equals(titlesysCode)) {
				titlesysCode = URLDecoder.decode(titlesysCode, "utf-8");
			}
			/* 职称名称 */
			String titlesysName = getRequestParameter("titlesysName");
			if (null != titlesysName && !"".equals(titlesysName)) {
				titlesysName = URLDecoder.decode(titlesysName, "utf-8");
			}
			/* 技术名称 */
			String technicalName = getRequestParameter("technicalName");
			if (null != technicalName && !"".equals(technicalName)) {
				technicalName = URLDecoder.decode(technicalName, "utf-8");
			}
			/* 工作经验 */
			String workExperience = getRequestParameter("workExperience");
			if (null != workExperience && !"".equals(workExperience)) {
				workExperience = URLDecoder.decode(workExperience, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("titlesysCode," + SearchCondition.ANYLIKE, titlesysCode);
				pager = titleSysController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != titlesysCode && !"".equals(titlesysCode)) {
					params.put("titlesysCode," + SearchCondition.ANYLIKE, titlesysCode);
				}
				if (null != titlesysName && !"".equals(titlesysName)) {
					params.put("titlesysName," + SearchCondition.ANYLIKE, titlesysName);
				}
				if (null != technicalName && !"".equals(technicalName)) {
					params.put("technicalName," + SearchCondition.ANYLIKE, technicalName);
				}
				if (null != workExperience && !"".equals(workExperience)) {
					params.put("workExperience," + SearchCondition.ANYLIKE, workExperience);
				}
				pager = titleSysController.goSingleList(params, getPager());
			}
			logger.info("获取职称体系搜索列表数据页");
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
			Pager pager = titleSysController.doSubSingleList(params, getPager());
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
			titleTypeList = iTitleSysService.findAllByEntityClass(TitleType.class);
			titleGradeList = iTitleSysService.findAllByEntityClass(TitleGrade.class);
			technicalLevelList = iTitleSysService.findAllByEntityClass(TechnicalLevel.class);
			proficiencyList = iTitleSysService.findAllByEntityClass(Proficiency.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrTitleSys = titleSysController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(hrTitleSys.getId()) && !"0".equals(hrTitleSys.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrTitleSys.getTitlesysName();
			String py = ChnToPinYin.getPYString(title);
			hrTitleSys.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrTitleSys);
			hrTitleSys = titleSysController.doSaveTitleSys(hrTitleSys);
			logger.info("对职称体系进行了修改！");
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
			HrTitleSys hrTitleSys = titleSysController.doListEntityById(id);
			if (null != hrTitleSys) {
				titleSysController.deleteByEntity(hrTitleSys);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除职称体系");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 弹框选择组织 */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = iTitleSysService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iTitleSysService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
}
