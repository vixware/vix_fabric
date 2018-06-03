package com.vix.nvix.customer.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccountClipType;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 会员卡类型设置
 * 
 * @类全名 com.vix.nvix.customer.action.MembershipCardTypeAction
 *
 * @author zhanghaibing
 *
 * @date 2017年10月17日
 */
@Controller
@Scope("prototype")
public class MembershipCardTypeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private CustomerAccountClipType customerAccountClipType;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, CustomerAccountClipType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				customerAccountClipType = vixntBaseService.findEntityById(CustomerAccountClipType.class, id);
			} else {
				customerAccountClipType = new CustomerAccountClipType();
				customerAccountClipType.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (customerAccountClipType != null && StringUtils.isNotEmpty(customerAccountClipType.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(customerAccountClipType);
			customerAccountClipType = vixntBaseService.merge(customerAccountClipType);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");
			}
		}
	}

	public void deleteCustomerAccountClipTypeId() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				customerAccountClipType = vixntBaseService.findEntityById(CustomerAccountClipType.class, id);
				if (null != customerAccountClipType) {
					vixntBaseService.deleteByEntity(customerAccountClipType);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
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

	public CustomerAccountClipType getCustomerAccountClipType() {
		return customerAccountClipType;
	}

	public void setCustomerAccountClipType(CustomerAccountClipType customerAccountClipType) {
		this.customerAccountClipType = customerAccountClipType;
	}

}
