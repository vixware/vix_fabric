package com.vix.nvix.drp.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.SMSCostRules;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 短信计费规则
 * 
 * @类全名 com.vix.nvix.drp.action.MessageCostRulesAction
 * 
 * @author zhanghaibing
 *
 * @date 2017年11月1日
 */
@Controller
@Scope("prototype")
public class MessageCostRulesAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private SMSCostRules sMSCostRules;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();

			pager = vixntBaseService.findPagerByHqlConditions(pager, SMSCostRules.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				sMSCostRules = vixntBaseService.findEntityById(SMSCostRules.class, id);
			} else {
				sMSCostRules = new SMSCostRules();
				sMSCostRules.setStatus("0");
				sMSCostRules.setCode(VixUUID.createCode(12));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(sMSCostRules.getId())) {
				isSave = false;
			}
			sMSCostRules.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(sMSCostRules);
			sMSCostRules = vixntBaseService.merge(sMSCostRules);
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

	public SMSCostRules getsMSCostRules() {
		return sMSCostRules;
	}

	public void setsMSCostRules(SMSCostRules sMSCostRules) {
		this.sMSCostRules = sMSCostRules;
	}

}
