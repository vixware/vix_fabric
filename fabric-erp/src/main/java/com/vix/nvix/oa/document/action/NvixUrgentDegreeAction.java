package com.vix.nvix.oa.document.action;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.oa.document.entity.UrgentDegree;

@Controller
@Scope("prototype")
public class NvixUrgentDegreeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String name;
	private UrgentDegree urgentDegree;
	
	/** 获取列表数据  */
	public void getUrgentDegreeJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, UrgentDegree.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				urgentDegree = vixntBaseService.findEntityById(UrgentDegree.class, id);
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
			if(StrUtils.isNotEmpty(urgentDegree.getId())){
				isSave = false;
				urgentDegree.setUpdateTime(new Date());
			}else{
				urgentDegree.setCreateTime(new Date());
				urgentDegree.setUpdateTime(new Date());
			}
			urgentDegree = vixntBaseService.merge(urgentDegree);
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
			urgentDegree = vixntBaseService.findEntityById(UrgentDegree.class, id);
			if(null != urgentDegree){
				vixntBaseService.deleteByEntity(urgentDegree);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UrgentDegree getUrgentDegree() {
		return urgentDegree;
	}

	public void setUrgentDegree(UrgentDegree urgentDegree) {
		this.urgentDegree = urgentDegree;
	}

}
