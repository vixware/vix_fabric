package com.vix.drp.channelprice.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channelprice.entity.ChannelPriceCondition;
import com.vix.drp.channelprice.entity.ChannelPriceConditionCountArea;
import com.vix.drp.channelprice.service.IChannelPriceService;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;

@Controller
@Scope("prototype")
public class ChannelPriceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	@Autowired
	private IChannelPriceService channelPriceService;
	private List<CurrencyType> currencyTypeList;

	private String channelDistributorId;
	private String itemId;
	private Double count;
	/**
	 * 渠道价格管理
	 */
	private ChannelPriceCondition channelPriceCondition;
	private List<ChannelPriceCondition> channelPriceConditionList;

	@Override
	public String goList() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			//处理搜索条件
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
			if (null == parentId || "0".equals(parentId)) {
			} else {
				if ("C".equals(treeType)) {
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			//跟进登陆账号显示定价条件   当前登陆账号只能看到他的下级的定价条件
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = channelPriceService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						if (channelDistributor != null && channelDistributor.getSubChannelDistributors() != null) {
							String cids = "";
							for (ChannelDistributor c : channelDistributor.getSubChannelDistributors()) {
								cids += c.getId();
							}
							params.put("channelDistributor.id," + SearchCondition.IN, cids);
						}
					}
				}
			}
			channelPriceConditionList = channelPriceService.findAllByConditions(ChannelPriceCondition.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null == parentId || "0".equals(parentId)) {
			} else {
				if ("C".equals(treeType)) {
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			//跟进登陆账号显示定价条件   当前登陆账号只能看到他的下级的定价条件
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = channelPriceService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						if (channelDistributor != null && channelDistributor.getSubChannelDistributors() != null) {
							String cids = "";
							for (ChannelDistributor c : channelDistributor.getSubChannelDistributors()) {
								cids += c.getId();
							}
							params.put("channelDistributor.id," + SearchCondition.IN, cids);
						}
					}
				}
			}

			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String code = getRequestParameter("code");
			String selectName = getDecodeRequestParameter("selectName");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (selectName != null && !"".equals(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			//处理搜索条件
			//处理搜索条件
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
			pager = channelPriceService.findPagerByHqlConditions(pager, ChannelPriceCondition.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return GO_SINGLE_LIST;
	}

	/** 获取列表数据 */
	public String goChooseListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = channelPriceService.findPagerByHqlConditions(getPager(), ChannelPriceCondition.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseListContent";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = channelPriceService.findAllByEntityClass(CurrencyType.class);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelPriceCondition = channelPriceService.findEntityById(ChannelPriceCondition.class, id);
			} else {
				channelPriceCondition = new ChannelPriceCondition();
				channelPriceCondition.setIsTemp("1");
				channelPriceCondition.setCode(VixUUID.createCode(10));
				if (parentId != null) {
					ChannelDistributor channelDistributor = channelPriceService.findEntityById(ChannelDistributor.class, parentId);
					channelPriceCondition.setChannelDistributor(channelDistributor);
				}

				channelPriceCondition = channelPriceService.merge(channelPriceCondition);
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

			if (channelPriceCondition.getId() != null && !"".equals(channelPriceCondition.getId())) {
				isSave = false;
			}
			channelPriceCondition.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(channelPriceCondition);
			String py = ChnToPinYin.getPYString(channelPriceCondition.getName());
			channelPriceCondition.setChineseCharacter(py);
			//处理修改留痕
			billMarkProcessController.processMark(channelPriceCondition, updateField);
			channelPriceCondition = channelPriceService.merge(channelPriceCondition);
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
			ChannelPriceCondition pb = channelPriceService.findEntityById(ChannelPriceCondition.class, id);
			if (null != pb) {
				channelPriceService.deleteByEntity(pb);
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
				channelPriceService.batchDelete(ChannelPriceCondition.class, delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getPrice() {
		try {
			Double price = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributorId);
			params.put("item.id," + SearchCondition.EQUAL, itemId);
			List<ChannelPriceCondition> channelPriceConditionList = channelPriceService.findAllByConditions(ChannelPriceCondition.class, params);
			Item item = channelPriceService.findEntityById(Item.class, itemId);
			if (channelPriceConditionList != null && channelPriceConditionList.size() > 0) {
				for (ChannelPriceCondition channelPriceCondition : channelPriceConditionList) {
					if (channelPriceCondition.getChannelPriceConditionCountAreas() != null && channelPriceCondition.getChannelPriceConditionCountAreas().size() > 0) {
						for (ChannelPriceConditionCountArea channelPriceConditionCountArea : channelPriceCondition.getChannelPriceConditionCountAreas()) {
							if (channelPriceConditionCountArea.getStartCount() <= count && count <= channelPriceConditionCountArea.getEndCount()) {
								price = item.getPrice() * channelPriceConditionCountArea.getDiscount();
								String st = price.toString();
								renderJson(st);
								break;
							}
						}
					}
					/*if (channelPriceCondition.getChannelPriceConditionPriceAreas() != null && channelPriceCondition.getChannelPriceConditionPriceAreas().size() > 0) {
						for (ChannelPriceConditionPriceArea channelPriceConditionPriceArea : channelPriceCondition.getChannelPriceConditionPriceAreas()) {
							if (channelPriceConditionPriceArea.getStartTotalAmount() <= item.getPrice() * count && item.getPrice() * count <= channelPriceConditionPriceArea.getEndTotalAmount()) {
								price = item.getPrice() * channelPriceConditionPriceArea.getDiscount();
							}
						}
					}*/
				}
			} else {
				renderJson(item.getPrice().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			ChannelDistributor channelDistributor = channelPriceService.findEntityById(ChannelDistributor.class, parentId);
			if (channelDistributor != null) {
				Set<Item> itemSet = channelDistributor.getMdmItem();
				String itemId = "";
				for (Item item : itemSet) {
					itemId += "," + item.getId();
				}
				if (itemId != "") {
					params.put("id," + SearchCondition.IN, itemId);
				}
			}
			pager = channelPriceService.findPagerByHqlConditions(pager, Item.class, params);
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

	public void setIds(String ids) {
		this.ids = ids;
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

	/**
	 * @return the channelDistributorId
	 */
	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	/**
	 * @param channelDistributorId
	 *            the channelDistributorId to set
	 */
	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<ChannelPriceCondition> getChannelPriceConditionList() {
		return channelPriceConditionList;
	}

	public void setChannelPriceConditionList(List<ChannelPriceCondition> channelPriceConditionList) {
		this.channelPriceConditionList = channelPriceConditionList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
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

	public ChannelPriceCondition getChannelPriceCondition() {
		return channelPriceCondition;
	}

	public void setChannelPriceCondition(ChannelPriceCondition channelPriceCondition) {
		this.channelPriceCondition = channelPriceCondition;
	}

}
