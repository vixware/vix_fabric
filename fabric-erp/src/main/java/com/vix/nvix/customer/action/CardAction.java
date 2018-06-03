package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CardEntity;
import com.vix.mdm.crm.entity.CardEntityDetail;
import com.vix.mdm.crm.entity.CustomerAccountClip;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.util.StrUtils;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 会员卡定义
 * 
 * @类全名 com.vix.nvix.customer.action.CardAction
 * 
 * @author zhanghaibing
 *
 * @date 2017年10月27日
 */
@Controller
@Scope("prototype")
public class CardAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String imagesId;

	private CardEntity cardEntity;
	private String cardEntityId;
	private CardEntityDetail cardEntityDetail;
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 处理查询条件
			String name = getDecodeRequestParameter("searchName");
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CardEntity.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				cardEntity = vixntBaseService.findEntityById(CardEntity.class, id);
			} else {
				cardEntity = new CardEntity();
				cardEntity.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(cardEntity.getId())) {
				isSave = false;
			}
			cardEntity.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(cardEntity);
			cardEntity = vixntBaseService.merge(cardEntity);

			if (StringUtils.isNotEmpty(imagesId)) {
				WxpQyPicture wxpQyPicture = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", imagesId);
				wxpQyPicture.setCardEntity(cardEntity);
				wxpQyPicture = vixntBaseService.merge(wxpQyPicture);
				cardEntity.setPictureUrl(wxpQyPicture.getPictureUrl());
				cardEntity = vixntBaseService.merge(cardEntity);
			}
			if (isSave) {
				renderText("1:"+SAVE_SUCCESS+":"+cardEntity.getId());
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
	}

	/**
	 * 保存项目图片
	 */
	@Override
	public void saveOrUpdatePicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = vixntBaseService.merge(wxpQyPicture);
				if (wxpQyPicture != null) {
					renderText(wxpQyPicture.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0,保存失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				cardEntityDetail = vixntBaseService.findEntityById(CardEntityDetail.class, id);
			} else {
				cardEntityDetail = new CardEntityDetail();
				if(StringUtils.isNotEmpty(cardEntityId)){
					cardEntity = vixntBaseService.findEntityById(CardEntity.class, cardEntityId);
					cardEntityDetail.setCardEntity(cardEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateItem";
	}
	public String updateCardEntityDetail() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(cardEntityDetail.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id)) {
				cardEntity = vixntBaseService.findEntityById(CardEntity.class, id);
				if (cardEntity != null) {
					cardEntityDetail.setCardEntity(cardEntity);
				}
				cardEntityDetail = vixntBaseService.merge(cardEntityDetail);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}
	public void goStoredCardEntityDetailContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			if(StringUtils.isNotEmpty(cardEntityId)){
				params.put("cardEntity.id,"+SearchCondition.EQUAL, cardEntityId);
				pager = vixntBaseService.findPagerByHqlConditions(pager,CardEntityDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 删除会员卡定义
	public void deleteById(){
		try{
			if(StrUtils.isNotEmpty(id)){
				cardEntity = vixntBaseService.findEntityById(CardEntity.class, id);
				if(cardEntity != null){
					List<CustomerAccountClip> customerAccountClips = vixntBaseService.findAllByEntityClassAndAttribute(CustomerAccountClip.class, "card.id", cardEntity.getId());
					if(customerAccountClips != null && customerAccountClips.size() > 0){
						renderText("会员卡已使用");
					}else{
						List<CardEntityDetail> cardEntityDetails = vixntBaseService.findAllByEntityClassAndAttribute(CardEntityDetail.class, "cardEntity.id", cardEntity.getId());
						if(cardEntityDetails != null && cardEntityDetails.size() > 0){
							for (CardEntityDetail cardEntityDetail : cardEntityDetails) {
								vixntBaseService.deleteByEntity(cardEntityDetail);
							}
						}
						vixntBaseService.deleteByEntity(cardEntity);
						renderText(DELETE_SUCCESS);
					}
				}else{
					renderText(DELETE_FAIL);
				}
			}else{
				renderText(DELETE_FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	// 删除服务项目
	public void deleteServiceItemById() {
		try{
			if(StrUtils.isNotEmpty(id)){
				cardEntityDetail = vixntBaseService.findEntityById(CardEntityDetail.class, id);
				if(cardEntityDetail != null){
					vixntBaseService.deleteByEntity(cardEntityDetail);
					renderText(DELETE_SUCCESS);
				}else{
					renderText(DELETE_FAIL);
				}
			}else{
				renderText(DELETE_FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	public String getImagesId() {
		return imagesId;
	}

	public void setImagesId(String imagesId) {
		this.imagesId = imagesId;
	}

	public CardEntity getCardEntity() {
		return cardEntity;
	}

	public void setCardEntity(CardEntity cardEntity) {
		this.cardEntity = cardEntity;
	}

	public String getCardEntityId() {
		return cardEntityId;
	}

	public void setCardEntityId(String cardEntityId) {
		this.cardEntityId = cardEntityId;
	}

	public CardEntityDetail getCardEntityDetail() {
		return cardEntityDetail;
	}

	public void setCardEntityDetail(CardEntityDetail cardEntityDetail) {
		this.cardEntityDetail = cardEntityDetail;
	}
}
