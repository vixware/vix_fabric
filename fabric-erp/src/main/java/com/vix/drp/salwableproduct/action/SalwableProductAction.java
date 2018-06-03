package com.vix.drp.salwableproduct.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.vix.drp.salwableproduct.controller.SalwableProductController;
import com.vix.drp.salwableproduct.service.ISalwableProductService;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;

@Controller
@Scope("prototype")
public class SalwableProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private SalwableProductController salwableProductController;
	@Autowired
	private ISalwableProductService salwableProductService;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	/** 产品 */
	private Item item;

	private List<Item> itemList;
	/**
	 * 经销商
	 */
	private ChannelDistributor channelDistributor;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = salwableProductController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						channelDistributor = employee.getChannelDistributor();
					}
				}
			}
			itemList = salwableProductController.doListItemList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (StringUtils.isEmpty(id)) {
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = salwableProductController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getChannelDistributor() != null) {
							channelDistributor = employee.getChannelDistributor();
						}
					}
				}
			} else {
				if ("C".equals(treeType)) {
					/* 获取到下级所有经销商可销售的产品信息 */
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					channelDistributor = salwableProductController.doListChannelDistributorById(id);
				}
			}
			String searchContent = getDecodeRequestParameter("searchContent");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
			}
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = salwableProductController.doListItem(channelDistributor, params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
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
						orgUnitList = salwableProductService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = salwableProductService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
	 * 将经销商 物料进关联
	 * 
	 * @return
	 */
	public String itemToChannelDistributor() {
		try {
			String itemids = getRequestParameter("itemids");
			if ("C".equals(treeType)) {
				/* 获取到下级所有经销商可销售的产品信息 */
			} else if ("CH".equals(treeType)) {
				// 点击的树节点是分销体系结构
				channelDistributor = salwableProductController.doListChannelDistributorById(id);
			}
			String[] itemidsarr = itemids.split(",");
			if (channelDistributor != null) {
				if (itemidsarr != null && !"".equals(itemidsarr) && itemidsarr.length > 0) {
					Set<Item> itemSet = channelDistributor.getMdmItem();
					for (String itemId : itemidsarr) {
						if (itemId != null && !"".equals(itemId)) {
							Item item = salwableProductController.doListItemById(itemId);
							itemSet.add(item);
						}
					}
					channelDistributor.setMdmItem(itemSet);
					try {
						salwableProductController.saveOrUpdateChannelDistributor(channelDistributor);
						renderText(SAVE_SUCCESS);
					} catch (Exception e) {
						renderText(SAVE_FAIL);
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	// 跳转到选择采购订单页面
	public String goChooseItem() {
		return "goChooseItem";
	}

	public String goItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			//获取所选经销商信息
			ChannelDistributor channelDistributor = null;
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				channelDistributor = salwableProductService.findEntityById(ChannelDistributor.class, parentId);
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = salwableProductService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null && employee.getChannelDistributor() != null) {
						channelDistributor = salwableProductService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
					}
				}
			}
			if (channelDistributor != null) {
				if ("0".equals(channelDistributor.getIsHasParentChannelDistributor())) {

				} else if ("1".equals(channelDistributor.getIsHasParentChannelDistributor()) && channelDistributor.getParentChannelDistributor() != null) {
					ChannelDistributor c = channelDistributor.getParentChannelDistributor();
					Set<Item> itemSet = c.getMdmItem();
					String itemId = "";
					for (Item item : itemSet) {
						itemId += "," + item.getId();
					}
					params.put("id," + SearchCondition.IN, itemId);
				}
			}
			pager = salwableProductController.getItemList(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemList";
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getIds() {
		return ids;
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

	public void setIds(String ids) {
		this.ids = ids;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
