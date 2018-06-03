package com.vix.mdm.item.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.mdm.item.entity.PriceConditionCountArea;
import com.vix.mdm.item.entity.PriceConditionGift;
import com.vix.mdm.item.entity.PriceConditionPriceArea;

@Controller
@Scope("prototype")
public class PurchasePriceConditionGiftAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	
	private String id;
	private PriceConditionGift priceConditionGift;
	private String pcaId;
	private String type;
	
	/** 获取列表数据  */
	private List<PriceConditionGift> giftList;
	public String goListContent(){
		try {
			if(null != pcaId && !pcaId.equals("") && !pcaId.equals("0")){
				if(null != type && "pcca".equals(type)){
					giftList = baseHibernateService.findAllByEntityClassAndAttribute(PriceConditionGift.class,"priceConditionCountArea.id",pcaId);
				}else if(null != type && "pcpa".equals(type)){
					giftList = baseHibernateService.findAllByEntityClassAndAttribute(PriceConditionGift.class,"priceConditionPriceArea.id",pcaId);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goListContent";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				priceConditionGift = baseHibernateService.findEntityById(PriceConditionGift.class,id);
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
			if(null != priceConditionGift.getId()){
				isSave = false;
			}
			if(null != type && "pcca".equals(type)){
				PriceConditionCountArea pcca = new PriceConditionCountArea();
				pcca.setId(pcaId);
				priceConditionGift.setPriceConditionCountArea(pcca);
				priceConditionGift = baseHibernateService.merge(priceConditionGift);
			}else if(null != type && "pcpa".equals(type)){
				PriceConditionPriceArea pcpa = new PriceConditionPriceArea();
				pcpa.setId(pcaId);
				priceConditionGift.setPriceConditionPriceArea(pcpa);
				priceConditionGift = baseHibernateService.merge(priceConditionGift);
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
			
			PriceConditionGift pgg = baseHibernateService.findEntityById(PriceConditionGift.class, id);
			if(null != pgg){
				baseHibernateService.deleteByEntity(pgg);
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

	public PriceConditionGift getPriceConditionGift() {
		return priceConditionGift;
	}

	public void setPriceConditionGift(PriceConditionGift priceConditionGift) {
		this.priceConditionGift = priceConditionGift;
	}


	public String getPcaId() {
		return pcaId;
	}

	public void setPcaId(String pcaId) {
		this.pcaId = pcaId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PriceConditionGift> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<PriceConditionGift> giftList) {
		this.giftList = giftList;
	}
}
