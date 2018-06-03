package com.vix.drp.channelprice.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.drp.channelprice.entity.ChannelPriceConditionCountArea;
import com.vix.drp.channelprice.service.IChannelPriceService;

@Controller
@Scope("prototype")
public class ChannelPriceConditionCountAreaAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IChannelPriceService channelPriceService;

	private String id;
	private ChannelPriceConditionCountArea channelPriceConditionCountArea;

	/** 获取列表数据 */
	private List<ChannelPriceConditionCountArea> channelPriceConditionCountAreaList;

	public String goListContent() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = getParams();
				params.put("channelPriceCondition.id," + SearchCondition.EQUAL, id);
				channelPriceConditionCountAreaList = channelPriceService.findAllByConditions(ChannelPriceConditionCountArea.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelPriceConditionCountArea = channelPriceService.findEntityById(ChannelPriceConditionCountArea.class, id);
			} else {
				channelPriceConditionCountArea = new ChannelPriceConditionCountArea();
				channelPriceConditionCountArea = channelPriceService.merge(channelPriceConditionCountArea);
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
			if (null != channelPriceConditionCountArea.getId() && !"".equals(channelPriceConditionCountArea.getId())) {
				isSave = false;
			}
			channelPriceConditionCountArea.setIsTemp("0");
			channelPriceConditionCountArea = channelPriceService.merge(channelPriceConditionCountArea);
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
			ChannelPriceConditionCountArea pc = channelPriceService.findEntityById(ChannelPriceConditionCountArea.class, id);
			if (null != pc) {
				channelPriceService.deleteByEntity(pc);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("mdm_priceConditionCountAreaNotExist"));
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

	public ChannelPriceConditionCountArea getChannelPriceConditionCountArea() {
		return channelPriceConditionCountArea;
	}

	public void setChannelPriceConditionCountArea(ChannelPriceConditionCountArea channelPriceConditionCountArea) {
		this.channelPriceConditionCountArea = channelPriceConditionCountArea;
	}

	public List<ChannelPriceConditionCountArea> getChannelPriceConditionCountAreaList() {
		return channelPriceConditionCountAreaList;
	}

	public void setChannelPriceConditionCountAreaList(List<ChannelPriceConditionCountArea> channelPriceConditionCountAreaList) {
		this.channelPriceConditionCountAreaList = channelPriceConditionCountAreaList;
	}

}
