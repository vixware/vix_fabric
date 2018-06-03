package com.vix.oa.picture.action;

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
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.picture.controller.PictureBrowseController;
import com.vix.oa.picture.entity.PictureBrowse;
import com.vix.oa.picture.entity.PictureBrowseCategory;
import com.vix.system.entity.Attachment;

@Controller
@Scope("prototype")
public class PictureBrowseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(PictureBrowseController.class);

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private PictureBrowse pictureBrowse;
	private List<PictureBrowse> pictureBrowseList;
	@Autowired
	private PictureBrowseController pictureBrowseController;
	/**
	 * 图片浏览分类
	 */
	private PictureBrowseCategory pictureBrowseCategory;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			// pictureBrowseList =
			// baseHibernateService.findAllByEntityClass(PictureBrowse.class);
			pictureBrowseList = pictureBrowseController.doListSalesOrderIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			if (parentId != null) {
				params.put("pictureBrowseCategory.id," + SearchCondition.EQUAL, parentId);
			}
			/*
			 * Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(),
			 * PictureBrowse.class, params); setPager(pager);
			 */
			Pager pager = pictureBrowseController.doSubSingleList(params, getPager());
			logger.info("获取图片浏览列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取搜索公共文件柜列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 文件名称
			String fileName = getRequestParameter("fileName");
			if (null != fileName && !"".equals(fileName)) {
				fileName = URLDecoder.decode(fileName, "utf-8");
			}
			//
			String keyword = getRequestParameter("keyword");
			if (null != keyword && !"".equals(keyword)) {
				keyword = URLDecoder.decode(keyword, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("fileName," + SearchCondition.ANYLIKE, fileName);
				pager = pictureBrowseController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != keyword && !"".equals(keyword)) {
					params.put("keyword," + SearchCondition.ANYLIKE, keyword);
				}
				if (null != fileName && !"".equals(fileName)) {
					params.put("fileName," + SearchCondition.ANYLIKE, fileName);
				}
				pager = pictureBrowseController.goSingleList(params, getPager());
			}
			logger.info("获取图片浏览搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 新增图片浏览分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateKnowledgeBaseCategory() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				pictureBrowseCategory = baseHibernateService.findEntityById(PictureBrowseCategory.class, id);
			} else {
				pictureBrowseCategory = new PictureBrowseCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateKnowledgeBaseCategory";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				pictureBrowse = baseHibernateService.findEntityById(PictureBrowse.class, id);
			}
			// 如果带出parentId,就无法修改
			/*
			 * else { pictureBrowse = new PictureBrowse(); if (null != parentId &&
			 * parentId.longValue() > 0) { PictureBrowseCategory pictureBrowseCategory =
			 * baseHibernateService.findEntityById(PictureBrowseCategory.class,parentId); if
			 * (pictureBrowseCategory != null) {
			 * pictureBrowse.setPictureBrowseCategory(PictureBrowseCategory); } } }
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
			if (null != pictureBrowse.getId()) {
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
			String fileName = pictureBrowse.getFileName();
			String py = ChnToPinYin.getPYString(fileName);
			pictureBrowse.setChineseFirstLetter(py.toUpperCase());
			pictureBrowse = pictureBrowseController.doSaveSalesOrder(pictureBrowse);

			baseHibernateService.merge(attachment);
			baseHibernateService.merge(pictureBrowse);
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
	 * 保存图片浏览分类
	 * 
	 * @return
	 */
	public String saveOrUpdateKnowledgeBaseCategory() {
		boolean isSave = true;
		try {
			if (null != pictureBrowseCategory.getId()) {
				isSave = false;
			}
			if (pictureBrowseCategory.getPictureBrowseCategoryss().getId() == null) {
				pictureBrowseCategory.setPictureBrowseCategoryss(null);
			}
			baseHibernateService.merge(pictureBrowseCategory);
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
		if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
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
		} else {
			return NONE;
		}
		return "downloadAttachment";
	}

	/**
	 * 获取知识点分类的树形结构
	 */
	public void findKnowledgeBaseCategoryTree() {
		try {
			List<PictureBrowseCategory> pictureBrowseCategoryList = new ArrayList<PictureBrowseCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				pictureBrowseCategoryList = baseHibernateService.findAllSubEntity(PictureBrowseCategory.class, "pictureBrowseCategoryss.id", id, params);
			} else {
				pictureBrowseCategoryList = baseHibernateService.findAllSubEntity(PictureBrowseCategory.class, "pictureBrowseCategoryss.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = pictureBrowseCategoryList.size();
			for (int i = 0; i < count; i++) {
				PictureBrowseCategory pictureBrowseCategory = pictureBrowseCategoryList.get(i);
				if (pictureBrowseCategory.getPictureBrowseCategorys().size() > 0) {
					strSb.append("{id:");
					strSb.append(pictureBrowseCategory.getId());
					strSb.append(",name:\"");
					strSb.append(pictureBrowseCategory.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(pictureBrowseCategory.getId());
					strSb.append(",name:\"");
					strSb.append(pictureBrowseCategory.getName());
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

	public String goPrivateCabinetCategory() {
		return "goPrivateCabinetCategory";
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

	public PictureBrowse getPictureBrowse() {
		return pictureBrowse;
	}

	public void setPictureBrowse(PictureBrowse pictureBrowse) {
		this.pictureBrowse = pictureBrowse;
	}

	public List<PictureBrowse> getPictureBrowseList() {
		return pictureBrowseList;
	}

	public void setPictureBrowseList(List<PictureBrowse> pictureBrowseList) {
		this.pictureBrowseList = pictureBrowseList;
	}

	public PictureBrowseController getPictureBrowseController() {
		return pictureBrowseController;
	}

	public void setPictureBrowseController(PictureBrowseController pictureBrowseController) {
		this.pictureBrowseController = pictureBrowseController;
	}

	public PictureBrowseCategory getPictureBrowseCategory() {
		return pictureBrowseCategory;
	}

	public void setPictureBrowseCategory(PictureBrowseCategory pictureBrowseCategory) {
		this.pictureBrowseCategory = pictureBrowseCategory;
	}

}
