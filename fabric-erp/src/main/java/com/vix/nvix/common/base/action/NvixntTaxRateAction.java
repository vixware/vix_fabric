package com.vix.nvix.common.base.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.TaxRate;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class NvixntTaxRateAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaxRate taxRate;
	private String id;
	private String name;
	
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(name)) {
				params.put("name,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, TaxRate.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				taxRate = vixntBaseService.findEntityById(TaxRate.class, id);
			}else {
				taxRate = new TaxRate();
				taxRate.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(taxRate.getId())) {
				isSave = false;
				taxRate.setUpdateTime(new Date());
			}else {
				taxRate.setCreateTime(new Date());
			}
			initEntityBaseController.initEntityBaseAttribute(taxRate);
			taxRate = vixntBaseService.merge(taxRate);
			if(isSave) {
				renderText(SAVE_SUCCESS);
			}else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave) {
				renderText(SAVE_FAIL);
			}else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteById() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				taxRate = vixntBaseService.findEntityById(TaxRate.class, id);
				if(taxRate != null) {
					vixntBaseService.deleteByEntity(taxRate);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	public TaxRate getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(TaxRate taxRate) {
		this.taxRate = taxRate;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
