package com.vix.oa.personaloffice.action;

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
import com.vix.oa.personaloffice.entity.SignIn;

@Controller
@Scope("prototype")
public class SignInAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SignIn signIn;
	private String id;
	private String name1;

	// 获取数据列表
	public String goListContent() {
		try {
			System.out.println(SecurityUtil.getCurrentUserTenantId());
			Map<String, Object> params = getParams();
			if (name1 != null && !"".equals(name1)) {
				params.put("title", "%" + name1 + "%");
			}

			/*
			 * wxpKnowLedgeService.findPagerByHqlConditions(getPager(),
			 * WxpKnowLedge.class, params);
			 */

			Pager pager = this.getPager();
			pager.setPageSize(20);
			String creator = getRequestParameter("creator");
			if (StrUtils.isNotEmpty(creator)) {
				params.put("creator," + SearchCondition.EQUAL, creator);
			}
			String rank = getDecodeRequestParameter("rank");
			if (StrUtils.isNotEmpty(rank)) {
				params.put("rank," + SearchCondition.ANYLIKE, rank);
			}

			pager = this.baseHibernateService.findPagerByHqlConditions(pager,
					SignIn.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 (新建规则)*/
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				signIn = baseHibernateService.findEntityById(SignIn.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}
	
	/** 跳转至用户修改页面 (弹性规则)*/
	public String goSaveOrUpdateTX() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				signIn = baseHibernateService.findEntityById(SignIn.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTX";
	}
	
	/** 跳转至用户修改页面 (导出考勤汇总)*/
	public String goSaveOrUpdateHZ() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				signIn = baseHibernateService.findEntityById(SignIn.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateHZ";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != signIn && StrUtils.objectIsNotNull(signIn.getId())) {
				isSave = false;
				signIn.setUpdateTime(new Date());
			} else {
				signIn.setCreateTime(new Date());
				loadCommonData(signIn);// 载入数据公司码
			}
			System.out.println(SecurityUtil.getCurrentUserTenantId());
			signIn = baseHibernateService.merge(signIn);
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
			SignIn pb = baseHibernateService.findEntityById(SignIn.class, id);
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
	
	public String goSearch(){
		
		return "goSearch";
	}

	public SignIn getSignIn() {
		return signIn;
	}

	public void setSignIn(SignIn signIn) {
		this.signIn = signIn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

}
