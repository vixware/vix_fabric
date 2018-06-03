package com.vix.nvix.common.base.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorSet;

@Controller
@Scope("prototype")
public class VixntStoreSetAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private ChannelDistributorSet channelDistributorSet;

	private String channelDistributorId;
	private String treeType;

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(channelDistributorId) && "CH".equals(treeType)) {
				channelDistributorSet = vixntBaseService.findEntityByAttribute(ChannelDistributorSet.class, "channelDistributor.id", channelDistributorId);
				if (channelDistributorSet != null) {
				} else {
					channelDistributorSet = new ChannelDistributorSet();
					ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, channelDistributorId);
					if (channelDistributor != null) {
						channelDistributorSet.setChannelDistributor(channelDistributor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	/** 新增修改编码规则 */
	public void saveOrUpdate() {

		boolean isSave = true;
		try {
			initEntityBaseController.initEntityBaseAttribute(channelDistributorSet);
			channelDistributorSet = vixntBaseService.merge(channelDistributorSet);
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
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public ChannelDistributorSet getChannelDistributorSet() {
		return channelDistributorSet;
	}

	public void setChannelDistributorSet(ChannelDistributorSet channelDistributorSet) {
		this.channelDistributorSet = channelDistributorSet;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

}
