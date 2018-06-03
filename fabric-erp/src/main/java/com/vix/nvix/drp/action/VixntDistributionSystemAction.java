package com.vix.nvix.drp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.message.entity.MessageSendRecord;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorMessageSet;
import com.vix.drp.distributermanagement.service.IDistributerManagementService;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.crm.entity.RechargeRecord;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntDistributionSystemAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String parentId;
	private String treeType;
	private String type;
	private String area;
	private ChannelDistributor channelDistributor;
	private ChannelDistributorMessageSet channelDistributorMessageSet;
	private Employee emp;
	private RechargeRecord rechargeRecord;
	private ChannelDistributor c;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private IDistributerManagementService distributerManagementService;

	private List<CurrencyType> currencyTypeList;

	private String channelDistributorId;

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
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.EQUAL, parentId);
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("shopType," + SearchCondition.EQUAL, "1");
			pager = vixntBaseService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 店铺储值记录
	 */
	public void goRechargeRecordList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(channelDistributorId)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributorId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, RechargeRecord.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 店铺短信发送记录
	 */
	public void goMessageSendRecordList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(channelDistributorId)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributorId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, MessageSendRecord.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goEmployeeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(channelDistributorId)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributorId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			}
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
				if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
					if ("C".equals(treeType)) {
						/* 要根据orgType 判断组织结构类型 */
						Organization o = distributerManagementService.findEntityById(Organization.class, parentId);
						if (null != o) {
							channelDistributor = new ChannelDistributor();
							channelDistributor.setCode(VixUUID.createCode(12));
							channelDistributor.setType(type);
							channelDistributor.setOrganization(o);
							channelDistributor.setShopType("1");
							channelDistributor.setIsTemp("1");
							channelDistributor.setIsHasParentChannelDistributor("0");
						}
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor parentChannelDistributor = distributerManagementService.findEntityById(ChannelDistributor.class, parentId);
						if (parentChannelDistributor != null) {
							channelDistributor = new ChannelDistributor();
							channelDistributor.setCode(VixUUID.createCode(12));
							channelDistributor.setType(type);
							channelDistributor.setShopType("1");
							channelDistributor.setIsTemp("1");
							channelDistributor.setParentChannelDistributor(parentChannelDistributor);
							channelDistributor.setOrganization(parentChannelDistributor.getOrganization());
							channelDistributor.setIsHasParentChannelDistributor("1");
						}
					}
				} else {
					channelDistributor = new ChannelDistributor();
					channelDistributor.setCode(VixUUID.createCode(12));
					channelDistributor.setType(type);
					channelDistributor.setShopType("1");
					channelDistributor.setIsTemp("1");
					channelDistributor.setIsHasParentChannelDistributor("0");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
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
				if (channelDistributor.getSalesEmployee() == null || StringUtils.isEmpty(channelDistributor.getSalesEmployee().getId())) {
					channelDistributor.setSalesEmployee(null);
				}
				if (channelDistributor.getEmployee() == null || StringUtils.isEmpty(channelDistributor.getEmployee().getId())) {
					channelDistributor.setEmployee(null);
				}
				channelDistributor = vixntBaseService.merge(channelDistributor);
				// 上传门店账号到电商系统
				uploadUseraccountToEc(channelDistributor);
				// 创建编码规则
				createEncodingRulesTableInTheMiddle(py, channelDistributor.getTenantId());
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

	public void saveOrUpdateRechargeRecord() {
		boolean isSave = true;
		try {
			initEntityBaseController.initEntityBaseAttribute(rechargeRecord);
			rechargeRecord.setPayDate(new Date());
			rechargeRecord = vixntBaseService.merge(rechargeRecord);
			if (rechargeRecord != null && rechargeRecord.getChannelDistributor() != null && StringUtils.isNotEmpty(rechargeRecord.getChannelDistributor().getId())) {
				channelDistributorMessageSet = vixntBaseService.findEntityByAttribute(ChannelDistributorMessageSet.class, "channelDistributor.id", rechargeRecord.getChannelDistributor().getId());
				if (channelDistributorMessageSet != null) {
					if (channelDistributorMessageSet.getAmount() != null && channelDistributorMessageSet.getAmount() > 0) {
						channelDistributorMessageSet.setAmount(channelDistributorMessageSet.getAmount() + rechargeRecord.getPayMoney());
					} else {
						channelDistributorMessageSet.setAmount(rechargeRecord.getPayMoney());
					}
				} else {
					channelDistributorMessageSet = new ChannelDistributorMessageSet();
					channelDistributorMessageSet.setAmount(rechargeRecord.getPayMoney());
					initEntityBaseController.initEntityBaseAttribute(channelDistributorMessageSet);
				}
				channelDistributorMessageSet = vixntBaseService.merge(channelDistributorMessageSet);
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
		c.setShopType(channelDistributor.getShopType());
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
		// uploadUseraccountToEc(c);
		// 创建编码规则
		// createEncodingRulesTableInTheMiddle(py, c.getTenantId());
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

	/**
	 * 
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String goChooseArea() {
		return "goChooseArea";
	}

	public void saveChannelDistributorByArea() throws Exception {
		String py = ChnToPinYin.getPinYinHeadChar(area).toUpperCase();
		if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
			if ("C".equals(treeType)) {
				/* 要根据orgType 判断组织结构类型 */
				Organization o = distributerManagementService.findEntityById(Organization.class, parentId);
				if (null != o) {
					channelDistributor = new ChannelDistributor();
					channelDistributor.setCode(py + "-");
					channelDistributor.setType(type);
					channelDistributor.setShopType("1");
					channelDistributor.setOrganization(o);
					channelDistributor.setIsHasParentChannelDistributor("0");
					channelDistributor.setStatus("0");
					channelDistributor.setIsTemp("1");
					initEntityBaseController.initEntityBaseAttribute(channelDistributor);
					channelDistributor = vixntBaseService.merge(channelDistributor);
				}
			} else if ("CH".equals(treeType)) {
				// 点击的树节点是分销体系结构
				ChannelDistributor parentChannelDistributor = distributerManagementService.findEntityById(ChannelDistributor.class, parentId);
				if (parentChannelDistributor != null) {
					channelDistributor = new ChannelDistributor();
					channelDistributor.setCode(py + "-");
					channelDistributor.setType(type);
					channelDistributor.setShopType("1");
					channelDistributor.setStatus("0");
					channelDistributor.setParentChannelDistributor(parentChannelDistributor);
					channelDistributor.setOrganization(parentChannelDistributor.getOrganization());
					channelDistributor.setIsHasParentChannelDistributor("1");
					channelDistributor.setIsTemp("1");
					initEntityBaseController.initEntityBaseAttribute(channelDistributor);
					channelDistributor = vixntBaseService.merge(channelDistributor);
				}
			}
		} else {
			channelDistributor = new ChannelDistributor();
			channelDistributor.setCode(py + "-");
			channelDistributor.setType(type);
			channelDistributor.setShopType("1");
			channelDistributor.setStatus("0");
			channelDistributor.setIsHasParentChannelDistributor("0");
			channelDistributor.setIsTemp("1");
			initEntityBaseController.initEntityBaseAttribute(channelDistributor);
			channelDistributor = vixntBaseService.merge(channelDistributor);
		}
		if (channelDistributor != null && StringUtils.isNotEmpty(channelDistributor.getId())) {
			renderText(channelDistributor.getId() + ":" + "区域选择成功!");
		}
	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("CH")) {
						// 加载公司信息和部门信息
						orgUnitList = distributerManagementService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = distributerManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName(), orgTmp.getCompanyCode());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName(), childOrg.getCompanyCode()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getChannelDistributors().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (ChannelDistributor channelDistributor : orgTmp.getChannelDistributors()) {
							if (channelDistributor.getParentChannelDistributor() != null) {
							} else {
								OrgUnit ou2 = new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName(), channelDistributor.getCode());
								if (channelDistributor.getSubChannelDistributors().size() > 0) {
									List<OrgUnit> ou3List = new LinkedList<OrgUnit>();
									for (ChannelDistributor c : channelDistributor.getSubChannelDistributors()) {
										ou3List.add(new OrgUnit(c.getId(), "CH", c.getName(), c.getCode()));
									}
									ou2.setSubOrgUnits(ou3List);
								}
								ou2List.add(ou2);
							}
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getOrgCode());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getOrgCode());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String goChooseOrganization() {
		return "goChooseOrganization";
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

	private UserAccount entity;
	private UserAccount entityForm;
	private String addUserAccountRoleIds;
	private String rolenames;

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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				listOrganization = distributerManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = distributerManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"C\"");
					strSb.append(",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"C\"");
					strSb.append(",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public ChannelDistributorMessageSet getChannelDistributorMessageSet() {
		return channelDistributorMessageSet;
	}

	public void setChannelDistributorMessageSet(ChannelDistributorMessageSet channelDistributorMessageSet) {
		this.channelDistributorMessageSet = channelDistributorMessageSet;
	}

	public ChannelDistributor getC() {
		return c;
	}

	public void setC(ChannelDistributor c) {
		this.c = c;
	}

	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}

	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public UserAccount getEntity() {
		return entity;
	}

	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	public UserAccount getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}

}