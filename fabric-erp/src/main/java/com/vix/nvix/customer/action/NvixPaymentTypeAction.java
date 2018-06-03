package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.PaymentType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.service.IVixntBaseService;

@Controller
@Scope("prototype")
public class NvixPaymentTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String name;
	private PaymentType paymentType;
	
	/** 获取列表数据  */
	public void getPaymentTypeJson(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			if(null != name && !"".equals(name)){
				name = decode(name, "UTF-8");
				params.put("payment,"+SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PaymentType.class, params);
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StrUtils.isNotEmpty(id) && !id.equals("0")){
				paymentType = vixntBaseService.findEntityById(PaymentType.class, id);
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
			if(StrUtils.isNotEmpty(paymentType.getId())){
				isSave = false;
				paymentType.setUpdateTime(new Date());
			}else{
				paymentType.setCreateTime(new Date());
				paymentType.setUpdateTime(new Date());
			}
			paymentType = vixntBaseService.merge(paymentType);
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
			paymentType = vixntBaseService.findEntityById(PaymentType.class, id);
			if(null != paymentType){
				vixntBaseService.deleteByEntity(paymentType);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			renderText("支付方式已使用,不可删除");
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
