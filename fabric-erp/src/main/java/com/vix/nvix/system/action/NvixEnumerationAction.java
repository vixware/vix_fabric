/**
 * 
 */
package com.vix.nvix.system.action;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.enumeration.entity.EnumerationCategory;
import com.vix.system.enumeration.service.IEnumerationService;

/**
 * @author Bluesnow 2016年8月26日
 * 
 *         引用字典管理
 * 
 * 
 */
@Controller
@Scope("prototype")
public class NvixEnumerationAction extends VixntBaseAction {

	private static final long serialVersionUID = -9162039317040215936L;

	@Autowired
	private IEnumerationService enumerationService;

	private String id;
	private Enumeration enumeration;
	private EnumValue enumValue;
	private String categoryId;
	private String parentId;
	private String searchName;
	private String enumValueName;
	private File fileToUpload;
	private String fileToUploadFileName;

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void getEnumerationJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null != categoryId) {
				params.put("enumerationCategory.id," + SearchCondition.EQUAL, categoryId);
			}
			if (StrUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			enumerationService.findPagerByHqlConditions(getPager(), Enumeration.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new Enumeration());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 获取明细列表数据 */
	public void getEnumValueJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null != parentId) {
				params.put("enumeration.id," + SearchCondition.EQUAL, parentId);
			}
			if (StrUtils.isNotEmpty(enumValueName)) {
				params.put("displayName," + SearchCondition.ANYLIKE, enumValueName);
			}
			enumerationService.findPagerByHqlConditions(getPager(), EnumValue.class, params);
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至维度修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				enumeration = enumerationService.findEntityById(Enumeration.class, id);
			} else {
				if (StrUtils.isNotEmpty(categoryId)) {
					enumeration = new Enumeration();
					enumeration.setCode(UUID.randomUUID().toString());
					enumeration.setEnumerationCategory(enumerationService.findEntityById(EnumerationCategory.class, categoryId));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至枚举值修改页面 */
	public String goSaveOrUpdateEnumValue() {
		try {
			if (StrUtils.isNotEmpty(id)) {
				enumValue = enumerationService.findEntityById(EnumValue.class, id);
			} else {
				if (StrUtils.isNotEmpty(parentId)) {
					enumValue = new EnumValue();
					enumValue.setEnumValueCode(UUID.randomUUID().toString());
					enumValue.setEnumeration(enumerationService.findEntityById(Enumeration.class, parentId));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateEnumValue";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != enumeration.getId()) {
				isSave = false;
				enumeration.setUpdateTime(new Date());
			} else {
				enumeration.setCreateTime(new Date());
				loadCommonData(enumeration);// 载入数据公司码
			}
			String[] attrArrays = {"enumerationCategory"};
			checkEntityNullValue(enumeration, attrArrays);
			enumeration = enumerationService.merge(enumeration);
			if (isSave) {
				setMessage(enumeration.getId() + "," + SAVE_SUCCESS);
			} else {
				setMessage(enumeration.getId() + "," + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0," + SAVE_FAIL);
			} else {
				this.setMessage("0," + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveOrUpdateEnumValue() {
		boolean isSave = true;
		try {
			if (null != enumValue.getId()) {
				isSave = false;
				enumValue.setUpdateTime(new Date());
			} else {
				enumValue.setCreateTime(new Date());
				loadCommonData(enumValue);// 载入数据公司码
			}
			String[] attrArrays = {"enumeration"};
			checkEntityNullValue(enumValue, attrArrays);
			enumValue = enumerationService.merge(enumValue);
			if (isSave) {
				setMessage(enumValue.getId() + "," + SAVE_SUCCESS);
			} else {
				setMessage(enumValue.getId() + "," + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("0," + SAVE_FAIL);
			} else {
				this.setMessage("0," + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Enumeration e = enumerationService.findEntityById(Enumeration.class, id);
			if (null != e) {
				enumerationService.deleteByAttribute(EnumValue.class, "enumeration.id", e.getId());
				enumerationService.deleteByEntity(e);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("system_enumerationNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String deleteEnumValueById() {
		try {
			EnumValue e = enumerationService.findEntityById(EnumValue.class, id);
			if (null != e) {
				enumerationService.deleteByEntity(e);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("system_enumValueNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseEnumeration() {
		return "goChooseEnumeration";
	}

	/** 导入字典 */
	public String importFiles() {
		try {
			int count = 0;
			if (null != fileToUpload && !"".equals(fileToUploadFileName)) {
				count = enumerationService.importFiles(fileToUpload, fileToUploadFileName);
			}
			if (count > 0) {
				renderJson("字典导入成功!");
			} else {
				renderJson("未导入字典数据!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("字典导入失败!");
		}
		return UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	public EnumValue getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(EnumValue enumValue) {
		this.enumValue = enumValue;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getEnumValueName() {
		return enumValueName;
	}
	public void setEnumValueName(String enumValueName) {
		this.enumValueName = enumValueName;
	}
}
