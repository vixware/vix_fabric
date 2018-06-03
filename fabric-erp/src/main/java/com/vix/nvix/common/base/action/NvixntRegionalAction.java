/**
 * 
 */
package com.vix.nvix.common.base.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

/**
 * @author Bluesnow
 * 2016年8月13日
 * 
 * 地域
 */
@Controller
@Scope("prototype")
public class NvixntRegionalAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	
	
	private String id;
	private String defaultValue;
	private Regional regional;
	
	/** 获取列表数据  */
	@SuppressWarnings("unchecked")
	public void getRegionalJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			
			if (null != defaultValue && !"".equals(defaultValue)) {
				defaultValue = decode(defaultValue, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, defaultValue);
			}
			
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), Regional.class, params);
			
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new Regional());
				}
			}
			String[] excludes = {""};
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				regional = baseHibernateService.findEntityById(Regional.class,id);
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
			if(null != regional.getId()){
				isSave = false;
			}else{
				regional.setCreateTime(new Date());
				loadCommonData(regional);
			}
			regional = baseHibernateService.merge(regional);
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
			Regional pb = baseHibernateService.findEntityById(Regional.class,id);
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
    
	public String goChooseRegional(){
		return "goChooseRegional";
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}