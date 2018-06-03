package com.vix.wechat.knowLedge.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.wechat.base.entity.WxpKnowLedge;

/**
 * 知识列表
 */
@Controller
@Scope("prototype")
public class WxpKnowLedgeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private WxpKnowLedge wxpKnowLedge;
	private String name;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			System.out.println(SecurityUtil.getCurrentUserTenantId());
			Map<String, Object> params = getParams();
			if(name != null && !"".equals(name)){
				params.put("title", "%"+name+"%");
			}
			
				/*baseHibernateService.findPagerByHqlConditions(getPager(),
					 WxpKnowLedge.class, params);*/
				
				Pager pager = this.getPager();
				pager.setPageSize(20);
				String title = getRequestParameter("title");
				if (StrUtils.isNotEmpty(title)) {
					params.put("title," + SearchCondition.EQUAL, title);
				}
				String classLfication = getDecodeRequestParameter("classLfication");
				if (StrUtils.isNotEmpty(classLfication)) {
					params.put("classLfication," + SearchCondition.ANYLIKE, classLfication);
				}
				String population = getDecodeRequestParameter("population");
				if (StrUtils.isNotEmpty(population)) {
					params.put("population," + SearchCondition.EQUAL, population);
				}
				String state = getDecodeRequestParameter("state");
				if (StrUtils.isNotEmpty(population)) {
					params.put("state," + SearchCondition.EQUAL, state);
				}
				String sequenceNnumber = getDecodeRequestParameter("sequenceNnumber");
				if (StrUtils.isNotEmpty(population)) {
					params.put("sequenceNnumber," + SearchCondition.EQUAL, sequenceNnumber);
				}
				pager = this.baseHibernateService.findPagerByHqlConditions(pager, WxpKnowLedge.class, params);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				wxpKnowLedge = baseHibernateService
						.findEntityById(WxpKnowLedge.class, id);
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
			if (null != wxpKnowLedge && StrUtils.objectIsNotNull(wxpKnowLedge.getId())) {
				isSave = false;
				wxpKnowLedge.setUpdateTime(new Date());
			} else {
				wxpKnowLedge.setCreateTime(new Date());
				loadCommonData(wxpKnowLedge);// 载入数据公司码
			}
			System.out.println(SecurityUtil.getCurrentUserTenantId());
			wxpKnowLedge = baseHibernateService.merge(wxpKnowLedge);
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
			WxpKnowLedge pb = baseHibernateService.findEntityById(WxpKnowLedge.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("sys_BrandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WxpKnowLedge getWxpKnowLedge() {
		return wxpKnowLedge;
	}

	public void setWxpKnowLedge(WxpKnowLedge wxpKnowLedge) {
		this.wxpKnowLedge = wxpKnowLedge;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}