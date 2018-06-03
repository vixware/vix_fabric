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
import com.vix.hr.initialSetup.entity.Grading;
import com.vix.hr.initialSetup.entity.PostsysTypes;
import com.vix.hr.initialSetup.entity.Powers;
import com.vix.hr.initialSetup.entity.Rank;
import com.vix.hr.job.controler.PostSysController;
import com.vix.hr.job.entity.HrPostSys;
import com.vix.hr.job.service.IPostSysService;

/**
 * @Description: 职务体系
 * @author 李大鹏
 */
@Controller
@Scope("prototype")
public class PostSysAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(HrPostSys.class);

	/** 注入service */
	@Autowired
	private PostSysController postSysController;
	private List<HrPostSys> hrPostSysList;
	private HrPostSys hrPostSys;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IPostSysService iPostSysService;
	private String id;
	private String pageNo;
	/** 职务类型 */
	private List<PostsysTypes> postsysTypesList;
	/** 职级 */
	private List<Rank> rankList;
	/** 职等 */
	private List<Grading> gradingList;
	/** 职权 */
	private List<Powers> powersList;

	public List<HrPostSys> getHrPostSysList() {
		return hrPostSysList;
	}

	public void setHrPostSysList(List<HrPostSys> hrPostSysList) {
		this.hrPostSysList = hrPostSysList;
	}

	public HrPostSys getHrPostSys() {
		return hrPostSys;
	}

	public void setHrPostSys(HrPostSys hrPostSys) {
		this.hrPostSys = hrPostSys;
	}

	public IPostSysService getiPostSysService() {
		return iPostSysService;
	}

	public void setiPostSysService(IPostSysService iPostSysService) {
		this.iPostSysService = iPostSysService;
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

	public List<PostsysTypes> getPostsysTypesList() {
		return postsysTypesList;
	}

	public void setPostsysTypesList(List<PostsysTypes> postsysTypesList) {
		this.postsysTypesList = postsysTypesList;
	}

	public List<Rank> getRankList() {
		return rankList;
	}

	public void setRankList(List<Rank> rankList) {
		this.rankList = rankList;
	}

	public List<Grading> getGradingList() {
		return gradingList;
	}

	public void setGradingList(List<Grading> gradingList) {
		this.gradingList = gradingList;
	}

	public List<Powers> getPowersList() {
		return powersList;
	}

	public void setPowersList(List<Powers> powersList) {
		this.powersList = powersList;
	}

	@Override
	public String goList() {
		try {
			hrPostSysList = postSysController.findpostsysIndex();
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
			Pager pager = postSysController.goSingleList(params, getPager());
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
			/* 职务编码 */
			String postsysCode = getRequestParameter("postsysCode");
			if (null != postsysCode && !"".equals(postsysCode)) {
				postsysCode = URLDecoder.decode(postsysCode, "utf-8");
			}
			/* 职务名称 */
			String postsysName = getRequestParameter("postsysName");
			if (null != postsysName && !"".equals(postsysName)) {
				postsysName = URLDecoder.decode(postsysName, "utf-8");
			}
			/* 所属部门 */
			String theDepartment = getRequestParameter("theDepartment");
			if (null != theDepartment && !"".equals(theDepartment)) {
				theDepartment = URLDecoder.decode(theDepartment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("postsysCode," + SearchCondition.ANYLIKE, postsysCode);
				pager = postSysController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != postsysCode && !"".equals(postsysCode)) {
					params.put("postsysCode," + SearchCondition.ANYLIKE, postsysCode);
				}
				if (null != postsysName && !"".equals(postsysName)) {
					params.put("postsysName," + SearchCondition.ANYLIKE, postsysName);
				}
				if (null != theDepartment && !"".equals(theDepartment)) {
					params.put("theDepartment," + SearchCondition.ANYLIKE, theDepartment);
				}
				pager = postSysController.goSingleList(params, getPager());
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
			Pager pager = postSysController.doSubSingleList(params, getPager());
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
			postsysTypesList = iPostSysService.findAllByEntityClass(PostsysTypes.class);
			rankList = iPostSysService.findAllByEntityClass(Rank.class);
			gradingList = iPostSysService.findAllByEntityClass(Grading.class);
			powersList = iPostSysService.findAllByEntityClass(Powers.class);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				hrPostSys = postSysController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(hrPostSys.getId()) && !"0".equals(hrPostSys.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = hrPostSys.getPostsysName();
			String py = ChnToPinYin.getPYString(title);
			hrPostSys.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(hrPostSys);
			hrPostSys = postSysController.doSavePostSys(hrPostSys);
			logger.info("对职务体系进行了修改！");
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

	/**
	 * 弹出选择部门，职位窗体
	 * 
	 * @return
	 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			HrPostSys hrPostSys = postSysController.doListEntityById(id);
			if (null != hrPostSys) {
				postSysController.deleteByEntity(hrPostSys);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除职务体系信息");
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
				listOrganization = iPostSysService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = iPostSysService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
