package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.RepairOrderType;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixRepairOrderTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String name;
	private RepairOrderType repairOrderType;
	
	/** 获取列表数据  */
	public void getRepairOrderTypeJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, RepairOrderType.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				repairOrderType = vixntBaseService.findEntityById(RepairOrderType.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.isNotEmpty(repairOrderType.getId())){
				isSave = false;
				repairOrderType.setUpdateTime(new Date());
			}else{
				repairOrderType.setCreateTime(new Date());
				repairOrderType.setUpdateTime(new Date());
			}
			repairOrderType = vixntBaseService.merge(repairOrderType);
			if("1".equals(repairOrderType.getIsDefault())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDefault," + SearchCondition.EQUAL, "1");
				params.put("id," + SearchCondition.NOEQUAL, repairOrderType.getId());
				List<RepairOrderType> repairOrderTypes = vixntBaseService.findAllDataByConditions(RepairOrderType.class, params);
				if(repairOrderTypes != null && repairOrderTypes.size() > 0){
					for (RepairOrderType ht : repairOrderTypes) {
						ht.setIsDefault("0");
						ht = vixntBaseService.merge(ht);
					}
				}
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			repairOrderType = vixntBaseService.findEntityById(RepairOrderType.class, id);
			if(null != repairOrderType){
				vixntBaseService.deleteByEntity(repairOrderType);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			renderText("维修工单类型已使用,不可删除");
		}
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RepairOrderType getRepairOrderType() {
		return repairOrderType;
	}

	public void setRepairOrderType(RepairOrderType repairOrderType) {
		this.repairOrderType = repairOrderType;
	}

}
