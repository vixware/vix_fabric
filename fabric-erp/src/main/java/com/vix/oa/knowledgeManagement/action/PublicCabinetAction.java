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
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.knowledgeManagement.controller.PublicController;
import com.vix.oa.knowledgeManagement.entity.PublicCabinet;
import com.vix.oa.knowledgeManagement.entity.PublicCabinetCategory;
import com.vix.system.entity.Attachment;

@Controller
@Scope("prototype")
public class PublicCabinetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(PublicController.class);

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private PublicCabinet publicCabinet;
	private List<PublicCabinet> publicCabinetList;
	@Autowired
	private PublicController publicController;
	/**
	 * 公共文件柜分类
	 */
	private PublicCabinetCategory publicCabinetCategory;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			//publicCabinetList = baseHibernateService.findAllByEntityClass(PublicCabinet.class);
			publicCabinetList = publicController.doListSalesOrderIndex();
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
				params.put("publicCabinetCategory.id," + SearchCondition.EQUAL, parentId);
			}
			Pager pager = publicController.doSubSingleList(params,getPager());
			logger.info("获取公共文件柜列表数据");
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
				pager = publicController.goSearchList(params, getPager());
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
				pager = publicController.goSingleList(params, getPager());
			}
			logger.info("获取公共文件柜搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/**
	 * 新增公共文件柜分类
	 * 
	 * @return
	 */
	public String goSaveOrUpdateKnowledgeBaseCategory() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				publicCabinetCategory = baseHibernateService.findEntityById(PublicCabinetCategory.class, id);
			} else {
				publicCabinetCategory = new PublicCabinetCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateKnowledgeBaseCategory";
	}

	
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				publicCabinet = baseHibernateService.findEntityById(PublicCabinet.class, id);
			} 
			//如果带出parentId,就无法修改
			/*else {
				publicCabinet = new PublicCabinet();
				if (null != parentId && parentId.longValue() > 0) {
					PublicCabinetCategory publicCabinetCategory = baseHibernateService.findEntityById(PublicCabinetCategory.class,parentId);
					if (publicCabinetCategory != null) {
						publicCabinet.setPublicCabinetCategory(publicCabinetCategory);
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
			if (StringUtils.isNotEmpty(publicCabinet.getId()) && !"".equals(publicCabinet.getId())) {
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
			String fileName = publicCabinet.getFileName();
			String py = ChnToPinYin.getPYString(fileName);
			publicCabinet.setChineseFirstLetter(py.toUpperCase());
			publicCabinet = publicController.doSaveSalesOrder(publicCabinet);
			
			baseHibernateService.merge(attachment);
			baseHibernateService.merge(publicCabinet);
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
	 * 保存公共文件柜分类
	 * 
	 * @return
	 */
	public String saveOrUpdateKnowledgeBaseCategory() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(publicCabinetCategory.getId()) && !"".equals(publicCabinetCategory.getId())) {
				isSave = false;
			}
			if (publicCabinetCategory.getPublicCabinetCategoryss().getId() == null||"".equals(publicCabinetCategory.getPublicCabinetCategoryss().getId() )) {
				publicCabinetCategory.setPublicCabinetCategoryss(null);
			}
			baseHibernateService.merge(publicCabinetCategory);
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
		if(StringUtils.isNotEmpty(id) && !id.equals("0")){
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
			List<PublicCabinetCategory> publicCabinetCategoryList = new ArrayList<PublicCabinetCategory>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)&&!"0".equals(id)) {
				publicCabinetCategoryList = baseHibernateService.findAllSubEntity(PublicCabinetCategory.class, "publicCabinetCategoryss.id", id, params);
			} else {
				publicCabinetCategoryList = baseHibernateService.findAllSubEntity(PublicCabinetCategory.class, "publicCabinetCategoryss.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = publicCabinetCategoryList.size();
			for (int i = 0; i < count; i++) {
				PublicCabinetCategory publicCabinetCategory = publicCabinetCategoryList.get(i);
				if (publicCabinetCategory.getPublicCabinetCategorys().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(publicCabinetCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(publicCabinetCategory.getName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(publicCabinetCategory.getId());
					strSb.append("\",name:\"");
					strSb.append(publicCabinetCategory.getName());
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

	public PublicCabinet getPublicCabinet() {
		return publicCabinet;
	}

	public void setPublicCabinet(PublicCabinet publicCabinet) {
		this.publicCabinet = publicCabinet;
	}

	public List<PublicCabinet> getPublicCabinetList() {
		return publicCabinetList;
	}

	public void setPublicCabinetList(List<PublicCabinet> publicCabinetList) {
		this.publicCabinetList = publicCabinetList;
	}

	public PublicCabinetCategory getPublicCabinetCategory() {
		return publicCabinetCategory;
	}

	public void setPublicCabinetCategory(PublicCabinetCategory publicCabinetCategory) {
		this.publicCabinetCategory = publicCabinetCategory;
	}


}
