package com.vix.nvix.produce.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mm.settings.entity.ProcessManagement;
import com.vix.nvix.common.base.action.VixntBaseAction;
@Controller
@Scope("prototype")
public class NvixProcessManagementAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private ProcessManagement processManagement;
	
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, ProcessManagement.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				processManagement = vixntBaseService.findEntityById(ProcessManagement.class, id);
			}else{
				processManagement = new ProcessManagement();
				processManagement.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(processManagement.getId())){
				isSave = false;
			}
			processManagement.setCreateTime(new Date());
			processManagement.setUpdateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(processManagement);
			processManagement = vixntBaseService.merge(processManagement);
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
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				ProcessManagement processManagement = vixntBaseService.findEntityById(ProcessManagement.class, id);
				if(processManagement != null){
					vixntBaseService.deleteByEntity(processManagement);
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

	public ProcessManagement getProcessManagement() {
		return processManagement;
	}

	public void setProcessManagement(ProcessManagement processManagement) {
		this.processManagement = processManagement;
	}
}
