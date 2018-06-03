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
import com.vix.mm.settings.entity.CraftsRoute;
import com.vix.mm.settings.entity.CraftsRouteDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixCraftsRouteAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String craftsRouteId;
	private String ids;
	private CraftsRoute craftsRoute;
	private CraftsRouteDetail craftsRouteDetail;

	/** 获取列表数据 */
	public void goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			String craftsCode = getDecodeRequestParameter("craftsCode");
			if(StrUtils.isNotEmpty(craftsCode)){
				params.put("craftsCode," + SearchCondition.ANYLIKE, craftsCode);
			}
			String craftsName = getDecodeRequestParameter("craftsName");
			if(StrUtils.isNotEmpty(craftsName)){
				params.put("craftsName," + SearchCondition.ANYLIKE, craftsName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, CraftsRoute.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				craftsRoute = baseHibernateService.findEntityById(CraftsRoute.class, id);
			}else{
				craftsRoute = new CraftsRoute();
				craftsRoute.setCraDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			if (null != craftsRoute.getId()) {
				craftsRoute.setUpdateTime(new Date());
			} else {
				craftsRoute.setCreateTime(new Date());
				craftsRoute.setUpdateTime(new Date());
			}
			craftsRoute = baseHibernateService.merge(craftsRoute);
			renderText("1:"+craftsRoute.getId()+":"+OPER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:"+OPER_FAIL);
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				CraftsRoute pb = baseHibernateService.findEntityById(CraftsRoute.class, id);
				if (null != pb) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("craftsRoute.id," + SearchCondition.EQUAL, pb.getId());
					List<CraftsRouteDetail> craftsRouteDetails = baseHibernateService.findAllByConditions(CraftsRouteDetail.class, params);
					if(craftsRouteDetails != null && craftsRouteDetails.size() > 0){
						renderText("含有工序的工艺线路不能删除!");
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
	
	public void goCraftsRouteDetailList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			String name = getDecodeRequestParameter("name");
			if(StrUtils.isNotEmpty(name)){
				params.put("standardProcedure," + SearchCondition.ANYLIKE, name);
			}
			String orgName = getDecodeRequestParameter("orgName");
			if(StrUtils.isNotEmpty(orgName)){
				params.put("workCenter.orgName," + SearchCondition.ANYLIKE, orgName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("craftsRoute.id," + SearchCondition.EQUAL, id);
				pager = baseHibernateService.findPagerByHqlConditions(pager, CraftsRouteDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateCraftsRouteDetail() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				craftsRouteDetail = baseHibernateService.findEntityById(CraftsRouteDetail.class, id);
			} else {
				if (StringUtils.isNotEmpty(craftsRouteId) ) {
					craftsRouteDetail = new CraftsRouteDetail();
					craftsRoute = baseHibernateService.findEntityById(CraftsRoute.class, craftsRouteId);
					craftsRouteDetail.setCraftsRoute(craftsRoute);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCraftsRouteDetail";
	}

	/** 处理修改操作 */
	public void saveOrUpdateCraftsRouteDetail() {
		boolean isSave = true;
		try {
			if (StrUtils.isNotEmpty(craftsRouteDetail.getId())) {
				isSave = false;
				craftsRouteDetail.setUpdateTime(new Date());
			}else{
				craftsRouteDetail.setCreateTime(new Date());
				craftsRouteDetail.setUpdateTime(new Date());
			}
			craftsRouteDetail = baseHibernateService.merge(craftsRouteDetail);
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
	public String deleteCraftsRouteDetailById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				CraftsRouteDetail pb = baseHibernateService.findEntityById(CraftsRouteDetail.class, id);
				if (null != pb) {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public CraftsRoute getCraftsRoute() {
		return craftsRoute;
	}

	public void setCraftsRoute(CraftsRoute craftsRoute) {
		this.craftsRoute = craftsRoute;
	}

	public String getCraftsRouteId() {
		return craftsRouteId;
	}

	public void setCraftsRouteId(String craftsRouteId) {
		this.craftsRouteId = craftsRouteId;
	}

	public CraftsRouteDetail getCraftsRouteDetail() {
		return craftsRouteDetail;
	}

	public void setCraftsRouteDetail(CraftsRouteDetail craftsRouteDetail) {
		this.craftsRouteDetail = craftsRouteDetail;
	}
}
