package com.vix.nvix.produce.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mm.settings.entity.CraftsRouteDetail;
import com.vix.mm.settings.entity.WorkCenter;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixWorkCenterAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private WorkCenter workCenter;

	/** 获取列表数据 */
	public void goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			String org = getDecodeRequestParameter("org");
			if(StrUtils.isNotEmpty(org)){
				params.put("org," + SearchCondition.ANYLIKE, org);
			}
			String orgName = getDecodeRequestParameter("orgName");
			if(StrUtils.isNotEmpty(orgName)){
				params.put("orgName," + SearchCondition.ANYLIKE, orgName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, WorkCenter.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				workCenter = baseHibernateService.findEntityById(WorkCenter.class, id);
			}else{
				workCenter = new WorkCenter();
				workCenter.setCreDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					workCenter.setEstablishmentUser(e.getName());
				}
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
			if (null != workCenter.getId()) {
				isSave = false;
				workCenter.setUpdateTime(new Date());
			} else {
				workCenter.setCreateTime(new Date());
				workCenter.setUpdateTime(new Date());
			}
			workCenter = baseHibernateService.merge(workCenter);
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
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				WorkCenter pb = baseHibernateService.findEntityById(WorkCenter.class, id);
				if (null != pb) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("workCenter.id," + SearchCondition.EQUAL, pb.getId());
					List<CraftsRouteDetail> craftsRouteDetails = baseHibernateService.findAllByConditions(CraftsRouteDetail.class, params);
					if(craftsRouteDetails != null && craftsRouteDetails.size() > 0){
						renderText("含有工序的工作中心不能删除!");
					}else{
						baseHibernateService.deleteByEntity(pb);
						renderText(DELETE_SUCCESS);
					}
				} else {
					renderText(DELETE_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	public String goChooseWorkCenter() {
		return "goChooseWorkCenter";
	}
	
	public void goChooseWorkCenterListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StrUtils.isNotEmpty(searchName)){
				params.put("orgName," + SearchCondition.ANYLIKE, searchName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, WorkCenter.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public WorkCenter getWorkCenter() {
		return workCenter;
	}

	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}
}
