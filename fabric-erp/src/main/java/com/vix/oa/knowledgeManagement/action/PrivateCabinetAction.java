package com.vix.oa.knowledgeManagement.action;
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
import com.vix.oa.knowledgeManagement.controller.PrivateController;
import com.vix.oa.knowledgeManagement.entity.PrivateCabinet;
import com.vix.oa.knowledgeManagement.entity.PrivateCabinetCategory;
import com.vix.system.entity.Attachment;

@Controller
@Scope("prototype")
public class PrivateCabinetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(PrivateCabinet.class);

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private PrivateCabinet privateCabinet;
	private List<PrivateCabinet> privateCabinetList;
	@Autowired
	private PrivateController privateController;
	/**
	 * 个人文件柜分类
	 */
	private PrivateCabinetCategory privateCabinetCategory;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			/*privateCabinetList = baseHibernateService.findAllByEntityClass(PrivateCabinet.class);*/
			privateCabinetList = privateController.doListSalesOrderIndex();
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
			//uploadPersonId包含当前登录人id
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			if (parentId != null) {
				params.put("privateCabinetCategory.id," + SearchCondition.EQUAL,parentId);
			}
			Pager pager = privateController.doSubSingleList(params,getPager());
			logger.info("获取个人文件柜列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
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
			// 排序号
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				code = URLDecoder.decode(code, "utf-8");
			}
			// 文件名称
			String fileName = getRequestParameter("fileName");
			if (null != fileName && !"".equals(fileName)) {
				fileName = URLDecoder.decode(fileName, "utf-8");
			}
			// 姓名
			String keyword = getRequestParameter("keyword");
			if (null != keyword && !"".equals(keyword)) {
				keyword = URLDecoder.decode(keyword, "utf-8");
			}
			// 备注
			String memo = getRequestParameter("memo");
			if (null != memo && !"".equals(memo)) {
				memo = URLDecoder.decode(memo, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("fileName," + SearchCondition.ANYLIKE, fileName);
				pager = privateController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != code && !"".equals(code)) {
					params.put("code," + SearchCondition.ANYLIKE, code);
				}
				if (null != keyword && !"".equals(keyword)) {
					params.put("keyword," + SearchCondition.ANYLIKE, keyword);
				}
				if (null != fileName && !"".equals(fileName)) {
					params.put("fileName," + SearchCondition.ANYLIKE, fileName);
				}
				if (null != memo && !"".equals(memo)) {
					params.put("memo," + SearchCondition.ANYLIKE, memo);
				}
				pager = privateController.goSingleList(params, getPager());
			}
			logger.info("获取个人文件柜搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/**
	 * 新增个人文件柜分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateKnowledgeBaseCategory() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				privateCabinetCategory = baseHibernateService.findEntityById(PrivateCabinetCategory.class, id);
			} else {
				privateCabinetCategory = new PrivateCabinetCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateKnowledgeBaseCategory";
	}

	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				privateCabinet = baseHibernateService.findEntityById(PrivateCabinet.class, id);
			}
			//如果带出parentId,就无法修改
			/*else {
				privateCabinet = new PrivateCabinet();
				if (null != parentId && parentId.longValue() > 0) {
					PrivateCabinetCategory privateCabinetCategory = baseHibernateService.findEntityById(PrivateCabinetCategory.class,parentId);
					if (privateCabinetCategory != null) {
						privateCabinet.setPrivateCabinetCategory(privateCabinetCategory);
					}
				}
			}*/
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
			if (StringUtils.isNotEmpty(privateCabinet.getId()) && !"".equals(privateCabinet.getId())) {
				isSave = false;
			}
			// 保存附件
			String[] savePathAndName = this.saveUploadFile();
			Attachment attachment = new Attachment();
			if (savePathAndName != null && savePathAndName.length == 2) {
				attachment.setName(savePathAndName[1]);
				attachment.setPath(savePathAndName[0]);
			}
			
			/**索引 */
			String fileName = privateCabinet.getFileName();
			String py = ChnToPinYin.getPYString(fileName);
			privateCabinet.setChineseFirstLetter(py.toUpperCase());
			
			privateCabinet = privateController.doSaveSalesOrder(privateCabinet);
			
			this.privateCabinet.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.privateCabinet.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			privateCabinet.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			
			baseHibernateService.merge(attachment);
			baseHibernateService.merge(privateCabinet);
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
	 * 保存个人文件柜分类
	 * 
	 * @return
	 */
	public String saveOrUpdateKnowledgeBaseCategory() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(privateCabinetCategory.getId()) && !"".equals(privateCabinetCategory.getId())) {
				isSave = false;
			}
			if (privateCabinetCategory.getPrivateCabinetCategoryss().getId() == null||"".equals(privateCabinetCategory.getPrivateCabinetCategoryss().getId() )) {
				privateCabinetCategory.setPrivateCabinetCategoryss(null);
			}
			baseHibernateService.merge(privateCabinetCategory);
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
		if(StringUtils.isNotEmpty(id) ){
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
	 * 获取文件柜分类的树形结构
	 */
	public void findKnowledgeBaseCategoryTree() {
		try {
			List<PrivateCabinetCategory> privateCabinetCategoryList = new ArrayList<PrivateCabinetCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)&&!"0".equals(id)) {
				privateCabinetCategoryList = baseHibernateService.findAllSubEntity(PrivateCabinetCategory.class,"privateCabinetCategoryss.id", id, params);
			} else {
				privateCabinetCategoryList = baseHibernateService.findAllSubEntity(PrivateCabinetCategory.class,"privateCabinetCategoryss.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = privateCabinetCategoryList.size();
			for (int i = 0; i < count; i++) {
				PrivateCabinetCategory privateCabinetCategory = privateCabinetCategoryList.get(i);
				if (privateCabinetCategory.getPrivateCabinetCategorys().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(privateCabinetCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(privateCabinetCategory.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(privateCabinetCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(privateCabinetCategory.getName());
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

	public PrivateCabinet getPrivateCabinet() {
		return privateCabinet;
	}

	public void setPrivateCabinet(PrivateCabinet privateCabinet) {
		this.privateCabinet = privateCabinet;
	}

	public List<PrivateCabinet> getPrivateCabinetList() {
		return privateCabinetList;
	}

	public void setPrivateCabinetList(List<PrivateCabinet> privateCabinetList) {
		this.privateCabinetList = privateCabinetList;
	}

	public PrivateCabinetCategory getPrivateCabinetCategory() {
		return privateCabinetCategory;
	}

	public void setPrivateCabinetCategory(
			PrivateCabinetCategory privateCabinetCategory) {
		this.privateCabinetCategory = privateCabinetCategory;
	}

}
