package com.vix.nvix.chain.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.integralRulesSet.entity.IntegralActivity;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 处理积分活动吧
 * 
 * @类全名 com.vix.nvix.chain.action.VixntActivityAction
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月16日
 */
@Controller
@Scope("prototype")
public class VixntActivityAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private IntegralActivity integralActivity;

	private ChannelDistributor channelDistributor;

	public String goSaveOrUpdate() {
		try {
			Employee employee = getEmployee();
			if (employee != null) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
			}
			if (StringUtils.isNotEmpty(id)) {
				integralActivity = vixntBaseService.findEntityById(IntegralActivity.class, id);
			}
			if (integralActivity != null) {
				
			} else {
				integralActivity = new IntegralActivity();
				integralActivity.setCode(VixUUID.createCode(12));
				if (channelDistributor != null) {
					integralActivity.setChannelDistributor(channelDistributor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(integralActivity.getId())) {
				isSave = false;
			}
			integralActivity.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(integralActivity);
			integralActivity = vixntBaseService.merge(integralActivity);
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
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager = vixntBaseService.findPagerByHqlConditions(pager, IntegralActivity.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
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

	public IntegralActivity getIntegralActivity() {
		return integralActivity;
	}

	public void setIntegralActivity(IntegralActivity integralActivity) {
		this.integralActivity = integralActivity;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}