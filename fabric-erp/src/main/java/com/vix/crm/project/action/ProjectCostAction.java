package com.vix.crm.project.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.ProjectCostType;
import com.vix.crm.project.entity.ProjectCost;

@Controller
@Scope("prototype")
public class ProjectCostAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String ids;
	private ProjectCost projectCost;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ProjectCost.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	private List<ProjectCostType> projectCostTypeList;
	private List<CurrencyType> currencyTypeList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			projectCostTypeList = baseHibernateService.findAllByEntityClass(ProjectCostType.class);
			currencyTypeList =  baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if(null != id && !"".equals(id)){
				projectCost = baseHibernateService.findEntityById(ProjectCost.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				projectCost = new ProjectCost();
				projectCost.setHappenDate(new Date());
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
			if(StrUtils.objectIsNotNull(projectCost.getId())){
				isSave = false;
			}else{
				projectCost.setCreateTime(new Date());
				loadCommonData(projectCost);
			}

			String[] attrArray ={"crmProject","employee","currencyType","projectCostType"};
			checkEntityNullValue(projectCost,attrArray);
			
			projectCost = baseHibernateService.merge(projectCost);
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
			ProjectCost pb = baseHibernateService.findEntityById(ProjectCost.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if(null != ids && !"".equals(ids)){
				List<String> delIds = new ArrayList<String>();
				for(String idStr : ids.split(",")){
					if(null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)){
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(ProjectCost.class,delIds);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goChooseType(){
		return "goChooseType";
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ProjectCost getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(ProjectCost projectCost) {
		this.projectCost = projectCost;
	}

	public List<ProjectCostType> getProjectCostTypeList() {
		return projectCostTypeList;
	}

	public void setProjectCostTypeList(List<ProjectCostType> projectCostTypeList) {
		this.projectCostTypeList = projectCostTypeList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
}
