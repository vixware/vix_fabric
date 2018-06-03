package com.vix.mdm.item.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.mdm.item.entity.ItemBrand;

/**
 * 商品品牌
 */
@Controller
@Scope("prototype")
public class ItemBrandAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	
	private String id;
	private ItemBrand itemBrand;
	
	/** 获取列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			getPager().setPageSize(20);
			baseHibernateService.findPagerByHqlConditions(getPager(), ItemBrand.class, params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				itemBrand = baseHibernateService.findEntityById(ItemBrand.class, id);
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
			if(null != itemBrand.getId()){
				isSave = false;
				itemBrand.setUpdateTime(new Date());
			}else{
				itemBrand.setCreateTime(new Date());
				loadCommonData(itemBrand);//载入数据公司码
			}
			itemBrand = baseHibernateService.merge(itemBrand);
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
			ItemBrand pb = baseHibernateService.findEntityById(ItemBrand.class, id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("sys_BrandNotExist"));
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

	public ItemBrand getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(ItemBrand itemBrand) {
		this.itemBrand = itemBrand;
	}
}