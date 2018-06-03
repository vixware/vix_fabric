package com.vix.hr.hrmgr.action;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.hrmgr.controller.PoliciesController;
import com.vix.hr.hrmgr.entity.PolSysManage;
import com.vix.hr.hrmgr.entity.PolSysManageCategory;
import com.vix.hr.hrmgr.service.IPolSysManageService;
import com.vix.system.entity.Attachment;

@Controller
@Scope("prototype")
public class PoliciesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(PolSysManage.class);
	@Autowired
	private IPolSysManageService iPolSysManageService;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private PolSysManage polSysManage;
	private List<PolSysManage> polSysManageList;
	@Autowired
	private PoliciesController policiesController;
	/**
	 * 政策制度管理分类
	 */
	private PolSysManageCategory polSysManageCategory;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			polSysManageList = policiesController.doListSalesOrderIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			/* 状态 */
			String states = getRequestParameter("states");
			if (null != states && !"".equals(states)) {
				params.put("states," + SearchCondition.ANYLIKE, states);
			}
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			// uploadPersonId包含当前登录人id
			/*
			 * String employeeId = SecurityUtil.getCurrentUserId();
			 * params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			 */
			if (parentId != null) {
				params.put("privateCabinetCategory.id," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = policiesController.doSubSingleList(params, getPager());
			logger.info("获取政策制度管理列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	public String goSearch() {
		return "goSearch";
	}

	/** 获取搜索公共文件柜列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 文件名
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				code = URLDecoder.decode(code, "utf-8");
			}
			// 姓名
			String polName = getRequestParameter("polName");
			if (null != polName && !"".equals(polName)) {
				polName = URLDecoder.decode(polName, "utf-8");
			}
			// 文件名称
			String fileName = getRequestParameter("fileName");
			if (null != fileName && !"".equals(fileName)) {
				fileName = URLDecoder.decode(fileName, "utf-8");
			}
			// 创建人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
				pager = policiesController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != code && !"".equals(code)) {
					params.put("code," + SearchCondition.ANYLIKE, code);
				}
				if (null != polName && !"".equals(polName)) {
					params.put("polName," + SearchCondition.ANYLIKE, polName);
				}
				if (null != fileName && !"".equals(fileName)) {
					params.put("fileName," + SearchCondition.ANYLIKE, fileName);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				pager = policiesController.goSingleList(params, getPager());
			}
			logger.info("获取政策制度管理搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 新增政策制度管理分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateCategory() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				polSysManageCategory = iPolSysManageService.findEntityById(PolSysManageCategory.class, id);
			} else {
				polSysManageCategory = new PolSysManageCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCategory";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				polSysManage = iPolSysManageService.findEntityById(PolSysManage.class, id);
			}
			// 如果带出parentId,就无法修改
			/*
			 * else { privateCabinet = new PrivateCabinet(); if (null !=
			 * parentId && parentId.longValue() > 0) { PrivateCabinetCategory
			 * privateCabinetCategory =
			 * privateCabinetService.findEntityById(PrivateCabinetCategory.class
			 * ,parentId); if (privateCabinetCategory != null) {
			 * privateCabinet.setPrivateCabinetCategory(privateCabinetCategory);
			 * } } }
			 */
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
			if (StringUtils.isNotEmpty(polSysManage.getId()) && !"".equals(polSysManage.getId())) {
				isSave = false;
			}
			// 保存附件
			String[] savePathAndName = this.saveUploadFile();
			Attachment attachment = new Attachment();
			if (savePathAndName != null && savePathAndName.length == 2) {
				attachment.setName(savePathAndName[1]);
				attachment.setPath(savePathAndName[0]);
			}

			/** 索引 */
			String fileName = polSysManage.getFileName();
			String py = ChnToPinYin.getPYString(fileName);
			polSysManage.setChineseFirstLetter(py.toUpperCase());

			polSysManage = policiesController.doSaveSalesOrder(polSysManage);

			this.polSysManage.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.polSysManage.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			polSysManage.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());

			iPolSysManageService.merge(attachment);
			iPolSysManageService.merge(polSysManage);
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
	 * 保存政策制度管理分类
	 * 
	 * @return
	 */
	public String saveOrUpdateCategory() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(polSysManageCategory.getId()) && !"".equals(polSysManageCategory.getId())) {
				isSave = false;
			}
			if (polSysManageCategory.getPolSysManageCategoryss().getId() == null || "".equals(polSysManageCategory.getPolSysManageCategoryss().getId())) {
				polSysManageCategory.setPolSysManageCategoryss(null);
			}
			iPolSysManageService.merge(polSysManageCategory);
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

	public String downloadAttachment() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				Attachment doc = iPolSysManageService.findEntityById(Attachment.class, this.id);
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
		} else {
			return NONE;
		}
		return "downloadAttachment";
	}

	/**
	 * 获取文件柜分类的树形结构
	 */
	public void findKnowledgeBaseCategoryTree() {
		try {
			List<PolSysManageCategory> polSysManageCategoryList = new ArrayList<PolSysManageCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				polSysManageCategoryList = iPolSysManageService.findAllSubEntity(PolSysManageCategory.class, "polSysManageCategoryss.id", id, params);
			} else {
				polSysManageCategoryList = iPolSysManageService.findAllSubEntity(PolSysManageCategory.class, "polSysManageCategoryss.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = polSysManageCategoryList.size();
			for (int i = 0; i < count; i++) {
				PolSysManageCategory polSysManageCategory = polSysManageCategoryList.get(i);
				if (polSysManageCategory.getPolSysManageCategorys().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(polSysManageCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(polSysManageCategory.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(polSysManageCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(polSysManageCategory.getName());
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

	public String goPoliciesCategory() {
		return "goPoliciesCategory";
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

	public PolSysManage getPolSysManage() {
		return polSysManage;
	}

	public void setPolSysManage(PolSysManage polSysManage) {
		this.polSysManage = polSysManage;
	}

	public List<PolSysManage> getPolSysManageList() {
		return polSysManageList;
	}

	public void setPolSysManageList(List<PolSysManage> polSysManageList) {
		this.polSysManageList = polSysManageList;
	}

	public PolSysManageCategory getPolSysManageCategory() {
		return polSysManageCategory;
	}

	public void setPolSysManageCategory(PolSysManageCategory polSysManageCategory) {
		this.polSysManageCategory = polSysManageCategory;
	}

}
