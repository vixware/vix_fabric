package com.vix.system.enumeration.action;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.enumeration.service.IEnumerationService;

@Controller
@Scope("prototype")
public class EnumerationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IEnumerationService enumerationService;

	private String id;
	private Enumeration enumeration;
	private EnumValue enumValue;
	private String categoryId;
	private File fileToUpload;
	private String fileToUploadFileName;
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (null != categoryId) {
				params.put("enumerationCategory.id" + "," + SearchCondition.EQUAL, categoryId);
			}
			Pager pager = enumerationService.findPagerByHqlConditions(getPager(), Enumeration.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据 */
	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = enumerationService.findPagerByHqlConditions(getPager(), Enumeration.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至维度修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				enumeration = enumerationService.findEntityById(Enumeration.class, id);
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
			if (null != enumeration.getId()) {
				isSave = false;
				enumeration.setUpdateTime(new Date());
			} else {
				enumeration.setCreateTime(new Date());
				loadCommonData(enumeration);//载入数据公司码
			}
			if (null == enumeration.getCode() || "".equals(enumeration.getCode())) {
				enumeration.setCode(UUID.randomUUID().toString());
			}
			if (null == enumeration.getEnumerationCategory() || null == enumeration.getEnumerationCategory().getId() || !enumeration.getEnumerationCategory().getId().equals("") || !enumeration.getEnumerationCategory().getId().equals("0")) {
				enumeration.setEnumerationCategory(null);
			}
			enumeration = enumerationService.merge(enumeration);
			String data = getRequestParameter("data");
			if (null != data && !"".equals(data)) {
				String[] cs = data.split(",");
				for (String s : cs) {
					String[] csItem = s.split(":");
					if (csItem.length < 6) {
						continue;
					}
					EnumValue ev = new EnumValue();
					if (!"Placeholder".equals(csItem[0]) && !"".equals(csItem[0])) {
						ev.setId(csItem[0]);
						isSave = false;
					} else {
						loadCommonData(ev);//载入数据公司码
					}
					ev.setIsDefault(csItem[1]);
					ev.setEnable(csItem[2]);
					if (!"Placeholder".equals(csItem[3]) && !"".equals(csItem[3])) {
						ev.setDisplayName(csItem[3]);
					}
					if (!"Placeholder".equals(csItem[4]) && !"".equals(csItem[4])) {
						ev.setValue(csItem[4]);
					}
					if (!"Placeholder".equals(csItem[5]) && !"".equals(csItem[5])) {
						ev.setEnumValueCode(csItem[5]);
					}
					ev.setEnumeration(enumeration);
					enumerationService.merge(ev);
				}
			}
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
	public String importFiles(){
		try{
			int count = 0;
			if(null != fileToUpload && !"".equals(fileToUploadFileName)){
				count = enumerationService.importFiles(fileToUpload, fileToUploadFileName);
			}
			if(count > 0){
				renderJson("字典导入成功!");
			}else{
				renderJson("未导入字典数据!");
			}
		}catch(Exception ex){
			ex.printStackTrace();
			renderJson("字典导入失败!");
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

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
	
}
