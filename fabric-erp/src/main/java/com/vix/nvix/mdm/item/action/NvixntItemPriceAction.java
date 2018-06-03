package com.vix.nvix.mdm.item.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.item.constant.ItemConstant;
import com.vix.mdm.item.entity.ItemPrice;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixntItemPriceAction extends VixntBaseAction implements ItemConstant {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private ItemPrice itemPrice;
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				itemPrice = vixntBaseService.findEntityById(ItemPrice.class, id);
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
			if(null != itemPrice.getId()){
				isSave = false;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Map<String,Object> params = getParams();
			params.put("item.id,"+SearchCondition.EQUAL, itemPrice.getItem().getId());
			params.put("priceType,"+SearchCondition.EQUAL, itemPrice.getPriceType());
			params.put("endDate,"+SearchCondition.EQUAL, sdf.parse("9999-12-31 23:59:59"));
			List<ItemPrice> ipList = vixntBaseService.findAllByConditions(ItemPrice.class, params);
			if(null != ipList && ipList.size() > 0){
				ItemPrice ip = ipList.get(0);
				ip.setEndDate(new Date());
				vixntBaseService.merge(ip);
			}
			itemPrice.setStartDate(new Date());
			itemPrice.setEndDate(sdf.parse("9999-12-31 23:59:59"));
			if(null != itemPrice.getMaxPrice() && null != itemPrice.getMinPrice() && null != itemPrice.getPrice()){
				if(itemPrice.getMaxPrice() >= itemPrice.getMinPrice() && itemPrice.getPrice() >= itemPrice.getMinPrice()){
					vixntBaseService.merge(itemPrice);
				}else{
					setMessage("价格范围错误，数据未保存。");
					return UPDATE;
				}
			}else{
				setMessage("价格填写不全，数据未保存。");
				return UPDATE;
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
			ItemPrice pb = vixntBaseService.findEntityById(ItemPrice.class, id);
			if(null != pb){
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("mdm_priceNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public void getItemPriceJson(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(null != id && !"".equals(id)){
				String type = getRequestParameter("type");
				params.put("item.id,"+SearchCondition.EQUAL, id);
				if(null != type && type.equals("price")){
					params.put("priceType,"+SearchCondition.EQUAL, "price");
				}else if(null != type && type.equals("purchasePrice")){
					params.put("priceType,"+SearchCondition.EQUAL, "purchasePrice");
				}
			}
			pager = vixntBaseService.findPagerByHqlConditions(getPager(), ItemPrice.class, params);
			String[] excludes = { "" };
			renderDataTable(pager, excludes);
		} catch (Exception ex) {
			ex.printStackTrace();
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

	public ItemPrice getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(ItemPrice itemPrice) {
		this.itemPrice = itemPrice;
	}
}