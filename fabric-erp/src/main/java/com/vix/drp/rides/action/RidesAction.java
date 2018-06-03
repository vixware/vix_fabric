package com.vix.drp.rides.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.rides.controller.RidesController;
import com.vix.drp.rides.entity.Rides;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class RidesAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private RidesController ridesController;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	/**
	 * 游乐设施
	 */
	private Rides rides;
	private List<Rides> ridesList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			ridesList = ridesController.doListRidesIndex();
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

			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = ridesController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getCompanyCode() != null) {
						params.put("companyCode," + SearchCondition.ANYLIKE, employee.getCompanyCode());
					}
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.code," + SearchCondition.ANYLIKE, channelDistributor.getCode());
					}
				}
			}
			if (null == id) {
			} else {
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			}
			pager = ridesController.doListRides(params, pager);
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
				rides = ridesController.doListEntityById(id);
			} else {
				rides = new Rides();
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					if (parentId != null && !"".equals(parentId)) {
						ChannelDistributor channelDistributor = ridesController.doListChannelDistributorById(parentId);
						rides.setChannelDistributor(channelDistributor);
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
			if (null != rides.getId() && !"".equals(rides.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(rides);
			//处理修改留痕
			billMarkProcessController.processMark(rides, updateField);
			ridesController.doSaveRides(rides);
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

	//action
	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			Rides rides = ridesController.doListEntityById(id);
			if (null != rides) {
				ridesController.doDeleteByEntity(rides);
				logger.info("");
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
				ridesController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public String getIds() {
		return ids;
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

	public Rides getRides() {
		return rides;
	}

	public void setRides(Rides rides) {
		this.rides = rides;
	}

	public List<Rides> getRidesList() {
		return ridesList;
	}

	public void setRidesList(List<Rides> ridesList) {
		this.ridesList = ridesList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

}
