package com.vix.nvix.system.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 预警源设置
 * @author jackie
 *
 */
import com.vix.system.warningSource.entity.ModuleCategory;
import com.vix.system.warningSource.entity.WarningType;
@Controller
@Scope("prototype")
public class NvixntWarningSourceAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private ModuleCategory moduleCategory;
	private WarningType warningType;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			pager = vixntBaseService.findPagerByHqlConditions(pager, ModuleCategory.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				moduleCategory = vixntBaseService.findEntityById(ModuleCategory.class, id);
			}else{
				moduleCategory = new ModuleCategory();
				moduleCategory.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != moduleCategory.getId()) {
				isSave = false;
			}
			/*String dlJson = getRequestParameter("dlJson");
			List<WarningType> dlList = new ArrayList<WarningType>();
			if (dlJson != null && !"".equals(dlJson)) {
				dlList = new JSONDeserializer<List<WarningType>>().use("values", WarningType.class).deserialize(dlJson);
			}*/
			initEntityBaseController.initEntityBaseAttribute(moduleCategory);
			moduleCategory = vixntBaseService.merge(moduleCategory);
			if (isSave) {
				renderText("1:"+SAVE_SUCCESS+":"+moduleCategory.getId());
			} else {
				renderText("1:"+UPDATE_SUCCESS+":"+moduleCategory.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:"+SAVE_FAIL);
			} else {
				renderText("0:"+UPDATE_FAIL);
			}
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)){
				ModuleCategory moduleCategory = vixntBaseService.findEntityById(ModuleCategory.class, id);
				if(moduleCategory != null){
					vixntBaseService.deleteByEntity(moduleCategory);
					renderText(DELETE_SUCCESS);
				}else{
					renderText("该信息不存在!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void goSingleWarningTypeList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("moduleCategory.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, WarningType.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateWarningType(){
		try {
			if(StringUtils.isNotEmpty(id)){
				warningType = vixntBaseService.findEntityById(WarningType.class, id);
			}else{
				warningType = new WarningType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateWarningType";
	}
	public void saveOrUpdateWarningType(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(warningType.getId())){
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(warningType);
			warningType = vixntBaseService.merge(warningType);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteWarningById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				warningType = vixntBaseService.findEntityById(WarningType.class, id);
				if(warningType != null){
					vixntBaseService.deleteByEntity(warningType);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public ModuleCategory getModuleCategory() {
		return moduleCategory;
	}

	public void setModuleCategory(ModuleCategory moduleCategory) {
		this.moduleCategory = moduleCategory;
	}

	public WarningType getWarningType() {
		return warningType;
	}

	public void setWarningType(WarningType warningType) {
		this.warningType = warningType;
	}
}
