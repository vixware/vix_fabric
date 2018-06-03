package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.SaleInvoiceType;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixSaleInvoiceTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String name;
	private SaleInvoiceType saleInvoiceType;
	
	/** 获取列表数据  */
	public void getSaleInvoiceTypeJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, SaleInvoiceType.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				saleInvoiceType = vixntBaseService.findEntityById(SaleInvoiceType.class, id);
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
			if(StrUtils.isNotEmpty(saleInvoiceType.getId())){
				isSave = false;
				saleInvoiceType.setUpdateTime(new Date());
			}else{
				saleInvoiceType.setCreateTime(new Date());
				saleInvoiceType.setUpdateTime(new Date());
			}
			saleInvoiceType = vixntBaseService.merge(saleInvoiceType);
			if("1".equals(saleInvoiceType.getIsDefault())){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("isDefault," + SearchCondition.EQUAL, "1");
				params.put("id," + SearchCondition.NOEQUAL, saleInvoiceType.getId());
				List<SaleInvoiceType> saleInvoiceTypes = vixntBaseService.findAllDataByConditions(SaleInvoiceType.class, params);
				if(saleInvoiceTypes != null && saleInvoiceTypes.size() > 0){
					for (SaleInvoiceType ss : saleInvoiceTypes) {
						ss.setIsDefault("0");
						ss = vixntBaseService.merge(ss);
					}
				}
			}
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
			saleInvoiceType = vixntBaseService.findEntityById(SaleInvoiceType.class, id);
			if(null != saleInvoiceType){
				vixntBaseService.deleteByEntity(saleInvoiceType);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			renderText("销售票据已使用,不可删除");
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

	public SaleInvoiceType getSaleInvoiceType() {
		return saleInvoiceType;
	}

	public void setSaleInvoiceType(SaleInvoiceType saleInvoiceType) {
		this.saleInvoiceType = saleInvoiceType;
	}

}
