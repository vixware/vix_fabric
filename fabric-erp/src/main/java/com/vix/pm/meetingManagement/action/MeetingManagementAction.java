package com.vix.pm.meetingManagement.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.pm.meetingManagement.controller.MeetingManagementController;
import com.vix.pm.meetingManagement.entity.MeetingManagement;

/**
 * 
 * @ClassName: EquipmentAction
 * @Description: 设备资源管理
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2014-2-18 下午3:54:50
 */
@Controller
@Scope("prototype")
public class MeetingManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(MeetingManagementController.class);

	@Autowired
	private MeetingManagementController meetingManagementController;

	private List<MeetingManagement> meetingManagementList;
	private String id;
	private String parentId;
	private String pageNo;
	private MeetingManagement meetingManagement;

	/** 加载顶端工具栏 */
	public String goTopDynamicMenuContent() {

		return "goTopDynamicMenuContent";
	}

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			meetingManagementList = baseHibernateService.findAllByEntityClass(MeetingManagement.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId) {
				params.put("parentCategory.id," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), MeetingManagement.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				meetingManagement = baseHibernateService.findEntityById(MeetingManagement.class, id);
			} else {
				if (null != parentId && !"undefined".equals(parentId)) {
					MeetingManagement c = baseHibernateService.findEntityById(MeetingManagement.class, parentId);
					if (null != c) {
						meetingManagement = new MeetingManagement();
						meetingManagement.setParentCategory(c);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null == meetingManagement.getParentCategory() || null == meetingManagement.getParentCategory().getId()) {
				meetingManagement.setParentCategory(null);
			}
			if (null != meetingManagement.getId()) {
				isSave = false;
			}
			meetingManagement = baseHibernateService.merge(meetingManagement);
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
			baseHibernateService.deleteById(MeetingManagement.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<MeetingManagement> listCategory = new ArrayList<MeetingManagement>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = baseHibernateService.findAllSubEntity(MeetingManagement.class, "parentCategory.id", id, params);
			} else {
				listCategory = baseHibernateService.findAllSubEntity(MeetingManagement.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				MeetingManagement cc = listCategory.get(i);
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
			/// 名称
			String name = getRequestParameter("name");
			// 描述
			String memo = getRequestParameter("memo");
			if (null != memo && !"".equals(memo)) {
				memo = URLDecoder.decode(memo, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("memo," + SearchCondition.ANYLIKE, searchContent);
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
				pager = meetingManagementController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				// 将内容(name)的值remove掉
				if (params.containsKey("name,anylike")) {
					params.remove("name,anylike");
				}
				if (null != name && !"".equals(name)) {
					params.put("name," + SearchCondition.ANYLIKE, name);
				}
				if (null != memo && !"".equals(memo)) {
					params.put("memo," + SearchCondition.ANYLIKE, memo);
				}

				pager = meetingManagementController.goSingleList(params, getPager());
			}
			logger.info("获取合同分类表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goChooseCategory() {
		return "goChooseCategory";
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MeetingManagementController getMeetingManagementController() {
		return meetingManagementController;
	}

	public void setMeetingManagementController(MeetingManagementController meetingManagementController) {
		this.meetingManagementController = meetingManagementController;
	}

	public List<MeetingManagement> getMeetingManagementList() {
		return meetingManagementList;
	}

	public void setMeetingManagementList(List<MeetingManagement> meetingManagementList) {
		this.meetingManagementList = meetingManagementList;
	}

	public MeetingManagement getMeetingManagement() {
		return meetingManagement;
	}

	public void setMeetingManagement(MeetingManagement meetingManagement) {
		this.meetingManagement = meetingManagement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
