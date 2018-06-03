package com.vix.oa.travelclaims.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.areaExpensesStandards.entity.AreaExpensesStandards;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;
import com.vix.oa.claimsmanagement.areaLevel.entity.VixCity;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;
import com.vix.oa.travelclaims.entity.TransportCosts;

@Controller
@Scope("prototype")
public class TransportCostsAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private TransportCosts transportCosts;

	/** 获取列表数据 */
	private List<TransportCosts> transportCostsList;
	private String areaLevelId;
	private String transportId;
	private String orgPositionId;
	private String priceType;
	/**
	 * 区域
	 */
	private List<AreaLevel> areaLevelList;
	/**
	 * 交通工具
	 */
	private List<Transport> transportList;
	/**
	 * 出差城市
	 */
	private List<VixCity> vixCityList;

	public String goListContent() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				Map<String, Object> params = getParams();
				params.put("travelExpense.id," + SearchCondition.EQUAL, id);
				transportCostsList = baseHibernateService.findAllByConditions(TransportCosts.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> areaLevelParams = new HashMap<String, Object>();
			areaLevelList = baseHibernateService.findAllByConditions(AreaLevel.class, areaLevelParams);
			Map<String, Object> transportParams = new HashMap<String, Object>();
			transportList = baseHibernateService.findAllByConditions(Transport.class, transportParams);
			Map<String, Object> vixCityParams = new HashMap<String, Object>();
			vixCityList = baseHibernateService.findAllByConditions(VixCity.class, vixCityParams);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				transportCosts = baseHibernateService.findEntityById(TransportCosts.class, id);
			} else {
				transportCosts = new TransportCosts();
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getOrgPositions().size() > 0) {
							for (OrgPosition orgPosition : employee.getOrgPositions()) {
								transportCosts.setOrgPosition(orgPosition);
							}
						}
					}
				}
				transportCosts = baseHibernateService.merge(transportCosts);
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
			if (null != transportCosts.getId()) {
				isSave = false;
			}
			transportCosts = baseHibernateService.merge(transportCosts);
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
			TransportCosts transportCosts = baseHibernateService.findEntityById(TransportCosts.class, id);
			if (null != transportCosts) {
				baseHibernateService.deleteByEntity(transportCosts);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText(""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 获取报销标准
	 */
	public void getReimbursementAmount() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(areaLevelId) && !areaLevelId.equals("0")){
				params.put("areaLevel.id," + SearchCondition.EQUAL, areaLevelId);
			}
			if(StringUtils.isNotEmpty(transportId) && !transportId.equals("0")){
				params.put("transport.id," + SearchCondition.EQUAL, transportId);
			}
			if(StringUtils.isNotEmpty(orgPositionId) && !orgPositionId.equals("0")){
				params.put("orgPosition.id," + SearchCondition.EQUAL, orgPositionId);
			}
			params.put("type," + SearchCondition.EQUAL, "1");
			List<AreaExpensesStandards> areaExpensesStandardsList = baseHibernateService.findAllByConditions(AreaExpensesStandards.class, params);
			if (null != areaExpensesStandardsList) {
				for (AreaExpensesStandards areaExpensesStandards : areaExpensesStandardsList) {
					if (areaExpensesStandards != null) {
						renderJson(areaExpensesStandards.getReimbursementAmount().toString());
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the orgPositionId
	 */
	public String getOrgPositionId() {
		return orgPositionId;
	}

	/**
	 * @param orgPositionId
	 *            the orgPositionId to set
	 */
	public void setOrgPositionId(String orgPositionId) {
		this.orgPositionId = orgPositionId;
	}

	/**
	 * @return the areaLevelId
	 */
	public String getAreaLevelId() {
		return areaLevelId;
	}

	/**
	 * @param areaLevelId
	 *            the areaLevelId to set
	 */
	public void setAreaLevelId(String areaLevelId) {
		this.areaLevelId = areaLevelId;
	}

	/**
	 * @return the transportId
	 */
	public String getTransportId() {
		return transportId;
	}

	/**
	 * @param transportId
	 *            the transportId to set
	 */
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}

	public TransportCosts getTransportCosts() {
		return transportCosts;
	}

	public void setTransportCosts(TransportCosts transportCosts) {
		this.transportCosts = transportCosts;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public List<TransportCosts> getTransportCostsList() {
		return transportCostsList;
	}

	public void setTransportCostsList(List<TransportCosts> transportCostsList) {
		this.transportCostsList = transportCostsList;
	}

	/**
	 * @return the areaLevelList
	 */
	public List<AreaLevel> getAreaLevelList() {
		return areaLevelList;
	}

	/**
	 * @param areaLevelList
	 *            the areaLevelList to set
	 */
	public void setAreaLevelList(List<AreaLevel> areaLevelList) {
		this.areaLevelList = areaLevelList;
	}

	/**
	 * @return the transportList
	 */
	public List<Transport> getTransportList() {
		return transportList;
	}

	/**
	 * @param transportList
	 *            the transportList to set
	 */
	public void setTransportList(List<Transport> transportList) {
		this.transportList = transportList;
	}

	/**
	 * @return the vixCityList
	 */
	public List<VixCity> getVixCityList() {
		return vixCityList;
	}

	/**
	 * @param vixCityList
	 *            the vixCityList to set
	 */
	public void setVixCityList(List<VixCity> vixCityList) {
		this.vixCityList = vixCityList;
	}

}
