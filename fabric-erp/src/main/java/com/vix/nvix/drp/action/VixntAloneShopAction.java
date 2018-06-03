package com.vix.nvix.drp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorMessageSet;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.wx.entity.WxpWeixinSite;
import com.vix.wechat.util.WxApiUtil;

@Controller
@Scope("prototype")
public class VixntAloneShopAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String type;
	private String area;
	private ChannelDistributor channelDistributor;
	private String channelDistributorId;
	private ChannelDistributor c;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	private WxpWeixinSite wxpWeixinSite;
	private List<CurrencyType> currencyTypeList;
	private String parentId;
	private ChannelDistributorMessageSet channelDistributorMessageSet;
	private String account;
	private RechargeRecord rechargeRecord;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("shopType," + SearchCondition.EQUAL, "2");
			pager = vixntBaseService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = vixntBaseService.findAllByConditions(CurrencyType.class, params);
			if (StringUtils.isNotEmpty(id)) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setCode(VixUUID.createCode(12));
				channelDistributor.setType("4");
				channelDistributor.setShopType("2");
				channelDistributor.setStatus("0");
				channelDistributor.setIsTemp("1");
				channelDistributor.setIsHasParentChannelDistributor("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	private UserAccount entity;
	private UserAccount entityForm;
	private String addUserAccountRoleIds;
	private String rolenames;
	@Autowired
	private IAccountManagementService accountManagementService;
	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;

	/** 处理修改操作 */
	public void saveOrUpdateUserAccount() {
		boolean isSave = true;
		String accountId = null;
		try {
			if (null != entityForm.getId()) {
				accountId = entityForm.getId();
				isSave = false;
			}
			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			if (StringUtils.isNotEmpty(parentId) && !"undefined".equals(parentId)) {
				String[] empIds = parentId.split(",");
				for (String empId : empIds) {
					if (StringUtils.isNotEmpty(empId)) {
						Employee employee = userAccountService.findEntityById(Employee.class, empId);
						if (employee != null) {
							accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "sys", entityForm.getAccount(), entityForm.getPassword(), empId, "1", employee.getCompanyCode());
						}
					}
				}
			}
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

	public String goMessageSet() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class, "channelDistributor.id", parentId);
				if (channelDistributorMessageSet != null) {

				} else {
					channelDistributorMessageSet = new ChannelDistributorMessageSet();
					channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, parentId);
					if (channelDistributor != null) {
						channelDistributorMessageSet.setChannelDistributor(channelDistributor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMessageSet";
	}

	/** 跳转至用户修改页面 */
	public String goMessage() {
		try {
			rechargeRecord = new RechargeRecord();
			if (StringUtils.isNotEmpty(parentId)) {
				ChannelDistributor c = vixntBaseService.findEntityById(ChannelDistributor.class, parentId);
				if (c != null) {
					rechargeRecord.setChannelDistributor(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMessage";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			String py = ChnToPinYin.getPinYinHeadChar(channelDistributor.getName()).toUpperCase();
			channelDistributor.setChineseCharacter(py);
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
				c = vixntBaseService.findEntityById(ChannelDistributor.class, channelDistributor.getId());
				updateChannelDistributor(py, c, channelDistributor);
			} else {
				initEntityBaseController.initEntityBaseAttribute(channelDistributor);

				if (channelDistributor.getParentChannelDistributor() != null && channelDistributor.getParentChannelDistributor().getId() != null && !"".equals(channelDistributor.getParentChannelDistributor().getId())) {
					channelDistributor.setIsHasParentChannelDistributor("1");
				} else {
					channelDistributor.setParentChannelDistributor(null);
					channelDistributor.setIsHasParentChannelDistributor("0");
				}

				channelDistributor.setIsTemp("");
				if (channelDistributor.getEmployee() == null || StringUtils.isEmpty(channelDistributor.getEmployee().getId())) {
					channelDistributor.setEmployee(null);
				}
				channelDistributor = vixntBaseService.merge(channelDistributor);
				// 上传门店账号到电商系统
				// uploadUseraccountToEc(channelDistributor);
				// 创建编码规则
				// createEncodingRulesTableInTheMiddle(py,
				// channelDistributor.getTenantId());
			}
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

	public String goShow() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "goShow";
	}

	private Employee emp;

	public String goSaveOrUpdateStorePerson() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, parentId);
			}
			if (channelDistributor != null) {
				if (StringUtils.isNotEmpty(channelDistributor.getEmployeeId())) {
					emp = vixntBaseService.findEntityById(Employee.class, channelDistributor.getEmployeeId());
					if (emp != null) {
						if (emp.getUserAccounts() != null && emp.getUserAccounts().size() > 0) {
							for (UserAccount userAccount : emp.getUserAccounts()) {
								entity = userAccount;
								addUserAccountRoleIds = "";
								rolenames = "";
								if (entity != null) {
									for (Role role : entity.getRoles()) {
										addUserAccountRoleIds += "," + role.getId();
										rolenames += " " + role.getName();
									}
								}
							}
						} else {
							entity = new UserAccount();
							entity.setEmployee(new BaseEmployee(id));
							entity.setEnable("1");
						}
					}
				} else {
					emp = new Employee();
					emp.setChannelDistributor(channelDistributor);
					entity = new UserAccount();
					entity.setEnable("1");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateStorePerson";
	}

	public void saveOrUpdateStorePerson() {
		boolean isSave = true;
		String accountId = null;
		try {
			initEntityBaseController.initEntityBaseAttribute(emp);
			emp = vixntBaseService.merge(emp);
			if ("0".equals(emp.getUserType())) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, emp.getChannelDistributor().getId());
				channelDistributor.setEmployeeId(emp.getId());
				channelDistributor = vixntBaseService.merge(channelDistributor);
			}

			if (null != entityForm.getId()) {
				accountId = entityForm.getId();
				isSave = false;
			}

			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			if (emp != null) {
				accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "fx", entityForm.getAccount(), entityForm.getPassword(), emp.getId(), "1", emp.getCompanyCode());
			}

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

	/**
	 * 
	 * @param py
	 * @param c
	 * @param channelDistributor
	 * @throws Exception
	 */
	private void updateChannelDistributor(String py, ChannelDistributor c, ChannelDistributor channelDistributor) throws Exception {
		c.setCode(channelDistributor.getCode());
		c.setName(channelDistributor.getName());
		c.setShortName(channelDistributor.getShortName());
		c.setArtificialPerson(channelDistributor.getArtificialPerson());
		// c.setIsHasParentChannelDistributor(channelDistributor.getIsHasParentChannelDistributor());
		c.setOrganization(channelDistributor.getOrganization());
		c.setTelephone(channelDistributor.getTelephone());
		c.setEmail(channelDistributor.getEmail());
		c.setAddress(channelDistributor.getAddress());
		c.setIndustry(channelDistributor.getIndustry());
		c.setEmployeeCounts(channelDistributor.getEmployeeCounts());
		c.setLevel(channelDistributor.getLevel());
		c.setType(channelDistributor.getType());
		c.setRegisteredCapital(channelDistributor.getRegisteredCapital());
		c.setCurrencyType(channelDistributor.getCurrencyType());
		c.setStatus(channelDistributor.getStatus());
		c.setContacts(channelDistributor.getContacts());
		c.setChineseCharacter(channelDistributor.getChineseCharacter());
		c.setIsTemp("");

		initEntityBaseController.initEntityBaseAttribute(c);

		if (channelDistributor.getSalesEmployee() == null || StringUtils.isEmpty(channelDistributor.getSalesEmployee().getId())) {
			c.setSalesEmployee(null);
		} else {
			c.setSalesEmployee(channelDistributor.getSalesEmployee());
		}

		if (channelDistributor.getEmployee() == null || StringUtils.isEmpty(channelDistributor.getEmployee().getId())) {
			c.setEmployee(null);
		} else {
			c.setEmployee(channelDistributor.getEmployee());
		}

		c = vixntBaseService.merge(c);
		// 上传门店账号到电商系统
		uploadUseraccountToEc(c);
		// 创建编码规则
		createEncodingRulesTableInTheMiddle(py, c.getTenantId());

	}

	/**
	 * 
	 * @param channelDistributor
	 * @throws Exception
	 */
	private void uploadUseraccountToEc(ChannelDistributor channelDistributor) throws Exception {
		Employee emp = channelDistributor.getEmployee();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("employee.id," + SearchCondition.EQUAL, emp.getId());
		List<UserAccount> userAccountList = vixntBaseService.findAllByConditions(UserAccount.class, params);
		if (userAccountList != null && userAccountList.size() > 0) {
			for (UserAccount userAccount : userAccountList) {
				uploadItem(channelDistributor.getCode(), channelDistributor.getName(), userAccount.getAccount(), userAccount.getInitpassword());
			}
		}
	}

	public void saveOrUpdateChannelDistributorMessageSet() {
		boolean isSave = true;
		try {
			initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
			channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
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

	public void checkUserAccount() {
		try {
			Boolean isEdit = (StrUtils.isNotEmpty(id)) ? true : false;
			Boolean hasUa = userAccountService.findHasUserAccount(isEdit, id, account, null);
			if (hasUa) {
				renderText("账号重复，请重新填写！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, id);
				if (null != channelDistributor) {
					Map<String, Object> params = getParams();
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
					List<Employee> employeeList = this.vixntBaseService.findAllByConditions(Employee.class, params);
					if (employeeList != null && employeeList.size() > 0) {
						for (Employee employee : employeeList) {
							if (employee != null) {
								Map<String, Object> p = getParams();
								p.put("employee.id," + SearchCondition.EQUAL, employee.getId());
								List<UserAccount> userAccountList = this.vixntBaseService.findAllByConditions(UserAccount.class, p);
								for (UserAccount userAccount : userAccountList) {
									if (userAccount != null) {
										vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount_role where UserAccount_ID ='" + userAccount.getId() + "'", null);
									}
								}
								vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount where Employee_ID ='" + employee.getId() + "'", null);
								vixntBaseService.batchDeleteBySql("DELETE from oa_uploader where employee_ID ='" + employee.getId() + "'", null);
								vixntBaseService.batchDeleteBySql("DELETE from hr_org_employee where id ='" + employee.getId() + "'", null);
							}
						}
					}
					Map<String, Object> stockrecordsparams = getParams();
					stockrecordsparams.put("channelDistributor.id," + SearchCondition.EQUAL, id);
					List<StockRecords> stockRecordsList = this.vixntBaseService.findAllByConditions(StockRecords.class, stockrecordsparams);
					if (stockRecordsList != null && stockRecordsList.size() > 0) {
						for (StockRecords stockRecords : stockRecordsList) {
							Set<StockRecordLines> stockRecordLinesList = stockRecords.getSubStockRecordLines();
							if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
								for (StockRecordLines stockRecordLines : stockRecordLinesList) {
									vixntBaseService.batchDeleteBySql("DELETE from inv_stockrecordlines where id ='" + stockRecordLines.getId() + "'", null);
								}
							}
							vixntBaseService.batchDeleteBySql("DELETE from inv_stockrecords where id ='" + stockRecords.getId() + "'", null);
						}
					}
					vixntBaseService.batchDeleteBySql("DELETE from drp_storeitem_channeldistributor where CHANNELDISTRIBUTOR_ID ='" + id + "'", null);
					vixntBaseService.batchDeleteBySql("DELETE from drp_channeldistributor where id ='" + id + "'", null);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				vixntBaseService.batchDelete(ChannelDistributor.class, delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	public String goWeixinSite() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, id);
				if (channelDistributor != null) {
					wxpWeixinSite = vixntBaseService.findEntityByAttribute(WxpWeixinSite.class, "channelDistributor.id", id);
					if (wxpWeixinSite != null) {
					} else {
						wxpWeixinSite = new WxpWeixinSite();
						wxpWeixinSite.setChannelDistributor(channelDistributor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWeixinSite";
	}

	/** 处理修改操作 */
	public void saveOrUpdateWeixinSite() {
		boolean isSave = true;
		try {
			if (wxpWeixinSite != null && StringUtils.isNotEmpty(wxpWeixinSite.getId())) {
				isSave = false;
			} else {
				wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
			}
			update(wxpWeixinSite);
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

	// 更新AccessToken
	private WxpWeixinSite update(WxpWeixinSite wxpWeixinSite) throws Exception {
		if (wxpWeixinSite != null && wxpWeixinSite.needReloadQiyeAccessToken()) {
			if (StringUtils.isNotEmpty(wxpWeixinSite.getAppId()) && StringUtils.isNotEmpty(wxpWeixinSite.getSecret())) {
				Map<?, ?> dataMap = WxApiUtil.reloadAccessToken(wxpWeixinSite.getAppId(), wxpWeixinSite.getSecret());
				if (dataMap != null) {
					if (dataMap.containsKey("access_token")) {
						String newAccessToken = (String) dataMap.get("access_token");
						wxpWeixinSite.setAccessToken(newAccessToken);
					}
					if (dataMap.containsKey("expires_in")) {
						Integer expireSec = (Integer) dataMap.get("expires_in");
						long expireTime = System.currentTimeMillis() + 1000L * expireSec;
						wxpWeixinSite.setExpiresInTime(new Date(expireTime));
					}
					wxpWeixinSite = vixntBaseService.merge(wxpWeixinSite);
				}
			}
		}
		return wxpWeixinSite;
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public WxpWeixinSite getWxpWeixinSite() {
		return wxpWeixinSite;
	}

	public void setWxpWeixinSite(WxpWeixinSite wxpWeixinSite) {
		this.wxpWeixinSite = wxpWeixinSite;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public ChannelDistributor getC() {
		return c;
	}

	public void setC(ChannelDistributor c) {
		this.c = c;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public UserAccount getEntity() {
		return entity;
	}

	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	public String getAddUserAccountRoleIds() {
		return addUserAccountRoleIds;
	}

	public void setAddUserAccountRoleIds(String addUserAccountRoleIds) {
		this.addUserAccountRoleIds = addUserAccountRoleIds;
	}

	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public UserAccount getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}

	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}

	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public ChannelDistributorMessageSet getChannelDistributorMessageSet() {
		return channelDistributorMessageSet;
	}

	public void setChannelDistributorMessageSet(ChannelDistributorMessageSet channelDistributorMessageSet) {
		this.channelDistributorMessageSet = channelDistributorMessageSet;
	}

}