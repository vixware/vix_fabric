package com.vix.system.initset.action;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.core.web.Pager;
import com.vix.system.initset.entity.AccountInformation;
import com.vix.system.initset.entity.CostingType;

/**
 * 
 * com.vix.system.initset.action.InitSetAction
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-19
 */
@Controller
@Scope("prototype")
public class InitSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 账套信息
	 */
	private AccountInformation accountInformation;

	private List<AccountInformation> accountInformationList;
	/**
	 * 公司信息
	 */
	private Organization organization;
	/**
	 * 核算类型
	 */
	private CostingType costingType;

	@Override
	public String goList() {
		try {
			accountInformationList = baseHibernateService.findAllByEntityClass(AccountInformation.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), AccountInformation.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存账套信息
	 * 
	 * @return
	 */
	public String saveOrUpdateAccountInformation() {
		try {
			accountInformation = baseHibernateService.merge(accountInformation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 保存公司信息
	 * 
	 * @return
	 */
	public String saveOrUpdateOrganization() {
		try {
			organization = baseHibernateService.merge(organization);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 核算类型
	 * 
	 * @return
	 */
	public String saveOrUpdateCostingType() {
		try {
			costingType = baseHibernateService.merge(costingType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			baseHibernateService.deleteById(AccountInformation.class, id);
			renderText(DELETE_SUCCESS);
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

	public AccountInformation getAccountInformation() {
		return accountInformation;
	}

	public void setAccountInformation(AccountInformation accountInformation) {
		this.accountInformation = accountInformation;
	}

	public List<AccountInformation> getAccountInformationList() {
		return accountInformationList;
	}

	public void setAccountInformationList(List<AccountInformation> accountInformationList) {
		this.accountInformationList = accountInformationList;
	}

	@Override
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public CostingType getCostingType() {
		return costingType;
	}

	public void setCostingType(CostingType costingType) {
		this.costingType = costingType;
	}

}
