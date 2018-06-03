package com.vix.drp.KnowledgeBasemanagement.action;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.KnowledgeBasemanagement.entity.KnowledgeBase;
import com.vix.drp.KnowledgeBasemanagement.entity.KnowledgeBaseCategory;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.system.entity.Attachment;

@Controller
@Scope("prototype")
public class KnowledgeBasemanagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private KnowledgeBase knowledgeBase;
	private List<KnowledgeBase> knowledgeBaseList;
	/**
	 * 知识点分类
	 */
	private KnowledgeBaseCategory knowledgeBaseCategory;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(parentId) && !"0".equals(parentId)) {
				params.put("knowledgeBaseCategory.id," + SearchCondition.EQUAL, parentId);
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					ChannelDistributor channelDistributor = employee.getChannelDistributor();
					params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
				}
			}// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			knowledgeBaseList = baseHibernateService.findAllByConditions(KnowledgeBase.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(parentId) && !"0".equals(parentId)) {
				params.put("knowledgeBaseCategory.id," + SearchCondition.EQUAL, parentId);
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					ChannelDistributor channelDistributor = employee.getChannelDistributor();
					params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
				}
			}// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			this.addAdvFilterAndSort(params, pager);
			pager = baseHibernateService.findPagerByHqlConditions(pager, KnowledgeBase.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 新增知识点分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateKnowledgeBaseCategory() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				knowledgeBaseCategory = baseHibernateService.findEntityById(KnowledgeBaseCategory.class, id);
			} else {
				knowledgeBaseCategory = new KnowledgeBaseCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateKnowledgeBaseCategory";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				knowledgeBase = baseHibernateService.findEntityById(KnowledgeBase.class, id);
			} else {
				knowledgeBase = new KnowledgeBase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != knowledgeBase.getId() && !"".equals(knowledgeBase.getId())) {
				isSave = false;
			}
			// 保存附件
			String[] savePathAndName = this.saveUploadFile();
			Attachment attachment = new Attachment();
			if (savePathAndName != null && savePathAndName.length == 2) {
				attachment.setName(savePathAndName[1]);
				attachment.setPath(savePathAndName[0]);
				System.out.println("ftp://127.0.0.1/goodsimage/" + savePathAndName[1].toString());
			}
			baseHibernateService.merge(attachment);
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						knowledgeBase.setChannelDistributor(employee.getChannelDistributor());
					}
				}
			}
			if (knowledgeBase.getKnowledgeBaseCategory() == null) {
				knowledgeBase.setKnowledgeBaseCategory(null);
			}
			initEntityBaseController.initEntityBaseAttribute(knowledgeBase);
			//处理修改留痕
			billMarkProcessController.processMark(knowledgeBase, updateField);

			baseHibernateService.merge(knowledgeBase);
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

	/**
	 * 保存知识点分类
	 * 
	 * @return
	 */
	public String saveOrUpdateKnowledgeBaseCategory() {
		boolean isSave = true;
		try {
			if (null != knowledgeBaseCategory.getId() && !"".equals(knowledgeBaseCategory.getId())) {
				isSave = false;
			}
			if (knowledgeBaseCategory.getParentKnowledgeBaseCategory() == null || knowledgeBaseCategory.getParentKnowledgeBaseCategory().getId() == null || "".equals(knowledgeBaseCategory.getParentKnowledgeBaseCategory().getId())) {
				knowledgeBaseCategory.setParentKnowledgeBaseCategory(null);
			}
			initEntityBaseController.initEntityBaseAttribute(knowledgeBaseCategory);
			baseHibernateService.merge(knowledgeBaseCategory);
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

	public String downloadAttachment() {
		try {
			Attachment doc = baseHibernateService.findEntityById(Attachment.class, this.id);
			if (doc != null) {
				String fileName = doc.getName();
				String filePath = doc.getPath();
				String title = doc.getRealName();
				int idx = fileName.lastIndexOf(".");
				if (idx != -1) {
					title = title + fileName.substring(idx);
				}
				this.setOriFileName(title);
				String downloadFile = filePath + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadAttachment";
	}

	/**
	 * 获取知识点分类的树形结构
	 */
	public void findKnowledgeBaseCategoryTree() {
		try {
			List<KnowledgeBaseCategory> knowledgeBaseCategoryList = new ArrayList<KnowledgeBaseCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				knowledgeBaseCategoryList = baseHibernateService.findAllSubEntity(KnowledgeBaseCategory.class, "parentKnowledgeBaseCategory.id", id, params);
			} else {
				knowledgeBaseCategoryList = baseHibernateService.findAllSubEntity(KnowledgeBaseCategory.class, "parentKnowledgeBaseCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = knowledgeBaseCategoryList.size();
			for (int i = 0; i < count; i++) {
				KnowledgeBaseCategory knowledgeBaseCategory = knowledgeBaseCategoryList.get(i);
				if (knowledgeBaseCategory.getSubKnowledgeBaseCategorys().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(knowledgeBaseCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(knowledgeBaseCategory.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(knowledgeBaseCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(knowledgeBaseCategory.getName());
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

	public String goKnowledgeBaseCategory() {
		return "goKnowledgeBaseCategory";
	}

	//action
	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			KnowledgeBase knowledgeBase = baseHibernateService.findEntityById(KnowledgeBase.class, id);
			if (null != knowledgeBase) {
				baseHibernateService.deleteByEntity(knowledgeBase);
				logger.info("");
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
				baseHibernateService.batchDelete(KnowledgeBase.class, delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public List<KnowledgeBase> getKnowledgeBaseList() {
		return knowledgeBaseList;
	}

	public void setKnowledgeBaseList(List<KnowledgeBase> knowledgeBaseList) {
		this.knowledgeBaseList = knowledgeBaseList;
	}

	public KnowledgeBaseCategory getKnowledgeBaseCategory() {
		return knowledgeBaseCategory;
	}

	public void setKnowledgeBaseCategory(KnowledgeBaseCategory knowledgeBaseCategory) {
		this.knowledgeBaseCategory = knowledgeBaseCategory;
	}

}
