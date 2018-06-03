package com.vix.drp.membershipInformation.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.distributermanagement.service.IDistributerManagementService;
import com.vix.drp.membershipInformation.controller.MembershipInformationController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class MembershipInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MembershipInformationController membershipInformationController;
	@Autowired
	private IDistributerManagementService distributerManagementService;
	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private CustomerAccount customerAccount;
	private String treeType;
	private List<CustomerAccount> customerAccountList;
	private String source;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("type," + SearchCondition.EQUAL, "personal");

			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(treeType)) {
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = membershipInformationController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getChannelDistributor() != null) {
							// 如果登录的员工属于经销商或门店
							ChannelDistributor channelDistributor = employee.getChannelDistributor();
							params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						}
					}
				}
			}

			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			customerAccountList = membershipInformationController.doListCustomerAccountList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			/* 高级查询 */
			params.put("type," + SearchCondition.EQUAL, "personal");

			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(treeType)) {
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = membershipInformationController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getChannelDistributor() != null) {
							// 如果登录的员工属于经销商或门店
							ChannelDistributor channelDistributor = employee.getChannelDistributor();
							params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						}
					}
				}
			}
			//处理查询条件
			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String customerName = getDecodeRequestParameter("customerName");
			if (customerName != null && !"".equals(customerName)) {
				params.put("name," + SearchCondition.ANYLIKE, customerName);
			}
			String mobilePhone = getRequestParameter("mobilePhone");
			if (mobilePhone != null && !"".equals(mobilePhone)) {
				params.put("mobilePhone," + SearchCondition.EQUAL, mobilePhone);
			}
			//处理查询条件

			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = membershipInformationController.doListCustomerAccountPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				customerAccount = membershipInformationController.doListEntityById(id);
			} else {
				customerAccount = new CustomerAccount();
				if (StringUtils.isNotEmpty(parentId)) {
					ChannelDistributor channelDistributor = membershipInformationController.doListChannelDistributorById(parentId);
					if (channelDistributor != null) {
						customerAccount.setChannelDistributor(channelDistributor);
					}
				}
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = membershipInformationController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						customerAccount.setCode(autoCreateCode.getBillNO(channelDistributor.getCode()));
					}
				}
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
			if (null != customerAccount.getId() && !"".equals(customerAccount.getId())) {
				isSave = false;
			}
			customerAccount.setType("personal");
			customerAccount.setIsDownLoad("1");
			customerAccount.setVipChannel("90");
			initEntityBaseController.initEntityBaseAttribute(customerAccount);
			//处理修改留痕
			billMarkProcessController.processMark(customerAccount, updateField);
			membershipInformationController.doSaveCustomerAccount(customerAccount);
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
			CustomerAccount customerAccount = membershipInformationController.doListEntityById(id);
			if (null != customerAccount) {
				membershipInformationController.doDeleteByEntity(customerAccount);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				membershipInformationController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson
	 * 
	 * @Description: 加载公司和部门的混合树
	 * 
	 * @param 设定文件
	 * 
	 * @return void 返回类型
	 * 
	 * @throws
	 */
	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O") || nodeTreeType.equals("CH")) {
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
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getChannelDistributors().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (ChannelDistributor channelDistributor : orgTmp.getChannelDistributors()) {
							ou2List.add(new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName()));
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
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
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

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public List<CustomerAccount> getCustomerAccountList() {
		return customerAccountList;
	}

	public void setCustomerAccountList(List<CustomerAccount> customerAccountList) {
		this.customerAccountList = customerAccountList;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

}
