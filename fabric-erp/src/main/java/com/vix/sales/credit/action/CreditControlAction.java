package com.vix.sales.credit.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.credit.entity.CreditControl;

@Controller
@Scope("prototype")
public class CreditControlAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	private String id;
	private CreditControl creditControl;
	private String pageNo;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String title = getRequestParameter("title");
			if(null != title && !"".equals(title)){
				title = decode(title, "UTF-8");
				params.put("title,"+SearchCondition.ANYLIKE, title);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CreditControl.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				creditControl = baseHibernateService.findEntityById(CreditControl.class,id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			}else{
				creditControl = new CreditControl();
				creditControl.setCreateTime(new Date());
				loadCommonData(creditControl);
				creditControl = baseHibernateService.merge(creditControl);
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
			if(null != creditControl.getId()){
				isSave = false;
			}else{
				creditControl.setCreateTime(new Date());
				loadCommonData(creditControl);
			}
			baseHibernateService.merge(creditControl);
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
			CreditControl pb = baseHibernateService.findEntityById(CreditControl.class,id);
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CreditControl getCreditControl() {
		return creditControl;
	}

	public void setCreditControl(CreditControl creditControl) {
		this.creditControl = creditControl;
	}
}