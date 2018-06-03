package com.vix.drp.channelprice.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.drp.channelprice.entity.ChannelPriceConditionPriceArea;

@Controller
@Scope("prototype")
public class ChannelPriceConditionPriceAreaAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private ChannelPriceConditionPriceArea channelPriceConditionPriceArea;

	/** 获取列表数据 */
	private List<ChannelPriceConditionPriceArea> channelPriceConditionPriceAreaList;

	public String goListContent() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = getParams();
				params.put("channelPriceCondition.id," + SearchCondition.EQUAL, id);
				channelPriceConditionPriceAreaList = baseHibernateService.findAllByConditions(ChannelPriceConditionPriceArea.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelPriceConditionPriceArea = baseHibernateService.findEntityById(ChannelPriceConditionPriceArea.class, id);
			} else {
				channelPriceConditionPriceArea = new ChannelPriceConditionPriceArea();
				channelPriceConditionPriceArea = baseHibernateService.merge(channelPriceConditionPriceArea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != channelPriceConditionPriceArea.getId() && !"".equals(channelPriceConditionPriceArea.getId())) {
				isSave = false;
			}
			channelPriceConditionPriceArea.setIsTemp("0");
			//处理修改留痕
			billMarkProcessController.processMark(channelPriceConditionPriceArea, updateField);
			channelPriceConditionPriceArea = baseHibernateService.merge(channelPriceConditionPriceArea);
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
			ChannelPriceConditionPriceArea pc = baseHibernateService.findEntityById(ChannelPriceConditionPriceArea.class, id);
			if (null != pc) {
				baseHibernateService.deleteByEntity(pc);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("mdm_priceConditionPriceAreaNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public ChannelPriceConditionPriceArea getChannelPriceConditionPriceArea() {
		return channelPriceConditionPriceArea;
	}

	public void setChannelPriceConditionPriceArea(ChannelPriceConditionPriceArea channelPriceConditionPriceArea) {
		this.channelPriceConditionPriceArea = channelPriceConditionPriceArea;
	}

	public List<ChannelPriceConditionPriceArea> getChannelPriceConditionPriceAreaList() {
		return channelPriceConditionPriceAreaList;
	}

	public void setChannelPriceConditionPriceAreaList(List<ChannelPriceConditionPriceArea> channelPriceConditionPriceAreaList) {
		this.channelPriceConditionPriceAreaList = channelPriceConditionPriceAreaList;
	}

}
