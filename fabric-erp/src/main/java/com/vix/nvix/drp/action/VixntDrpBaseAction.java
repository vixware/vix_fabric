package com.vix.nvix.drp.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.drp.channel.entity.ChannelDistributorSet;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.drp.action.VixntDrpBaseAction
 *
 * @author zhanghaibing
 *
 * @date 2016年11月27日
 */
@Controller
@Scope("prototype")
public class VixntDrpBaseAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChannelDistributorSet getChannelDistributorSet(String id) {
		ChannelDistributorSet channelDistributorSet = null;
		try {
			if (StringUtils.isNotEmpty(id)) {
				channelDistributorSet = vixntBaseService.findEntityByAttribute(ChannelDistributorSet.class, "channelDistributor.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelDistributorSet;
	}
}
