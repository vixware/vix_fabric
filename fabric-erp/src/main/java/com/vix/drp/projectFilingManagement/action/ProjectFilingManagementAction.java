package com.vix.drp.projectFilingManagement.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.projectFilingManagement.controller.ProjectFilingManagementController;
import com.vix.drp.projectFilingManagement.entity.ProjectFilingManagement;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class ProjectFilingManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private ProjectFilingManagement projectFilingManagement;
	private List<ProjectFilingManagement> projectFilingManagementList;
	@Autowired
	private ProjectFilingManagementController projectFilingManagementController;
	private String parentId;
	private String treeType;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				if ("C".equals(treeType)) {
				} else if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			projectFilingManagementList = projectFilingManagementController.doListProjectFilingManagementList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				if ("C".equals(treeType)) {
				} else if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}

			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String code = getRequestParameter("code");
			String selectName = getDecodeRequestParameter("selectName");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (selectName != null && !"".equals(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = projectFilingManagementController.findPagerByHqlConditions(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				projectFilingManagement = projectFilingManagementController.doListProjectFilingManagementById(id);
			} else {

				Employee employee = getEmployee();
				if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
					if ("C".equals(treeType)) {
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor channelDistributor = projectFilingManagementController.doListChannelDistributorById(parentId);
						if (channelDistributor != null) {
							projectFilingManagement = new ProjectFilingManagement();
							projectFilingManagement.setCode(VixUUID.createCode(10));
							if (employee != null) {
								projectFilingManagement.setCreator(employee.getName());
							} else {
								projectFilingManagement.setCreator(SecurityUtil.getCurrentUserName());
							}
							projectFilingManagement.setChannelDistributor(channelDistributor);
						}
					}
				} else {
					projectFilingManagement = new ProjectFilingManagement();
					if (employee != null) {
						projectFilingManagement.setCreator(employee.getName());
					} else {
						projectFilingManagement.setCreator(SecurityUtil.getCurrentUserName());
					}
					projectFilingManagement.setCode(VixUUID.createCode(10));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (projectFilingManagement != null && StringUtils.isNotEmpty(projectFilingManagement.getId()) && !"0".equals(projectFilingManagement.getId())) {
				isSave = false;
			}
			projectFilingManagement.setIsTemp("");

			String py = ChnToPinYin.getPYString(projectFilingManagement.getName());
			projectFilingManagement.setChineseCharacter(py);
			//处理修改留痕
			billMarkProcessController.processMark(projectFilingManagement, updateField);
			projectFilingManagement = projectFilingManagementController.doSaveProjectFilingManagement(projectFilingManagement);
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
			ProjectFilingManagement projectFilingManagement = projectFilingManagementController.doListProjectFilingManagementById(id);
			if (null != projectFilingManagement) {
				projectFilingManagementController.doDeleteByEntity(projectFilingManagement);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				projectFilingManagementController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
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

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public ProjectFilingManagement getProjectFilingManagement() {
		return projectFilingManagement;
	}

	public void setProjectFilingManagement(ProjectFilingManagement projectFilingManagement) {
		this.projectFilingManagement = projectFilingManagement;
	}

	public List<ProjectFilingManagement> getProjectFilingManagementList() {
		return projectFilingManagementList;
	}

	public void setProjectFilingManagementList(List<ProjectFilingManagement> projectFilingManagementList) {
		this.projectFilingManagementList = projectFilingManagementList;
	}

}
