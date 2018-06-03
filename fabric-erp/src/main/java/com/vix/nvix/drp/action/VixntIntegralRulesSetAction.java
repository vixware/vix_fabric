package com.vix.nvix.drp.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.integralRulesSet.entity.IntegralRules;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 积分规则设定
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntIntegralRulesSetAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private IntegralRules integralRules;
	private ChannelDistributor channelDistributor;

	@Override
	public String goList() {
		try {
			Employee employee = getEmployee();
			if (employee != null) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
			}
			if (channelDistributor != null && StringUtils.isNotEmpty(channelDistributor.getId())) {
				integralRules = vixntBaseService.findEntityByAttribute(IntegralRules.class, "channelDistributor.id", channelDistributor.getId());
				if (integralRules != null) {

				} else {
					integralRules = new IntegralRules();
					integralRules.setStatus("0");
					integralRules.setCode(VixUUID.createCode(12));
					integralRules.setChannelDistributor(channelDistributor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(integralRules.getId())) {
				isSave = false;
			}
			integralRules.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(integralRules);
			integralRules = vixntBaseService.merge(integralRules);
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public IntegralRules getIntegralRules() {
		return integralRules;
	}

	public void setIntegralRules(IntegralRules integralRules) {
		this.integralRules = integralRules;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}
}