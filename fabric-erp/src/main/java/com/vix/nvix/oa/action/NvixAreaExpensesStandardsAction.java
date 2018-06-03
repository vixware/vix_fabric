package com.vix.nvix.oa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.OrgPosition;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.position.service.IOrgPositionService;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.areaExpensesStandards.entity.AreaExpensesStandards;
import com.vix.oa.claimsmanagement.areaLevel.entity.AreaLevel;
import com.vix.oa.claimsmanagement.vehicle.entity.Transport;

@Controller
@Scope("prototype")
public class NvixAreaExpensesStandardsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Resource(name = "orgPositionService")
	private IOrgPositionService orgPositionService;

	private String id;
	/** 公司id */
	private String orgId;

	private AreaExpensesStandards areaExpensesStandards;

	/**
	 * 区域
	 */
	private List<AreaLevel> areaLevelList;

	/**
	 * 交通
	 */
	private List<Transport> transportList;
	/**
	 * 岗位
	 */
	private List<OrgPosition> orgPositionList;

	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type," + SearchCondition.EQUAL, "1");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), AreaExpensesStandards.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> areaLevelParams = new HashMap<String, Object>();
			areaLevelList = vixntBaseService.findAllByConditions(AreaLevel.class, areaLevelParams);
			Map<String, Object> transportParams = new HashMap<String, Object>();
			transportList = vixntBaseService.findAllByConditions(Transport.class, transportParams);
			Map<String, Object> orgPositionParams = new HashMap<String, Object>();
			orgPositionList = vixntBaseService.findAllByConditions(OrgPosition.class, orgPositionParams);
			if (StringUtils.isNotEmpty(id)) {
				areaExpensesStandards = vixntBaseService.findEntityById(AreaExpensesStandards.class, id);
			} else {
				areaExpensesStandards = new AreaExpensesStandards();
				areaExpensesStandards.setCode(VixUUID.createCodeByNumber(10));
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
			if (null != areaExpensesStandards.getId()) {
				isSave = false;
			}
			if (areaExpensesStandards.getOrgPosition() != null && areaExpensesStandards.getOrgPosition().getId() != null) {

			} else {
				areaExpensesStandards.setOrgPosition(null);
			}

			if (areaExpensesStandards.getAreaLevel() != null && areaExpensesStandards.getAreaLevel().getId() != null) {
			} else {
				areaExpensesStandards.setAreaLevel(null);
			}
			if (areaExpensesStandards.getTransport() != null && areaExpensesStandards.getTransport().getId() != null) {

			} else {
				areaExpensesStandards.setTransport(null);
			}
			areaExpensesStandards.setType("1");
			areaExpensesStandards = vixntBaseService.merge(areaExpensesStandards);
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

	public String goChoosePosition() {
		return "goChoosePosition";
	}

	public void findOrgPositionByOrg() {
		try {
			List<OrgPosition> orgPositionList = new ArrayList<OrgPosition>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				orgPositionList = vixntBaseService.findAllSubEntity(OrgPosition.class, "parentOrgPosition.id", id, params);
			} else {
				orgPositionList = vixntBaseService.findAllSubEntity(OrgPosition.class, "parentOrgPosition.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgPositionList.size();
			for (int i = 0; i < count; i++) {
				OrgPosition org = orgPositionList.get(i);
				if (org.getSubOrgPositions().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"C\"");
					strSb.append(",name:\"");
					strSb.append(org.getPosName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"C\"");
					strSb.append(",name:\"");
					strSb.append(org.getPosName());
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

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<OrgPosition> getOrgPositionList() {
		return orgPositionList;
	}

	public void setOrgPositionList(List<OrgPosition> orgPositionList) {
		this.orgPositionList = orgPositionList;
	}

	public AreaExpensesStandards getAreaExpensesStandards() {
		return areaExpensesStandards;
	}

	public void setAreaExpensesStandards(AreaExpensesStandards areaExpensesStandards) {
		this.areaExpensesStandards = areaExpensesStandards;
	}

	public List<AreaLevel> getAreaLevelList() {
		return areaLevelList;
	}

	public void setAreaLevelList(List<AreaLevel> areaLevelList) {
		this.areaLevelList = areaLevelList;
	}

	public List<Transport> getTransportList() {
		return transportList;
	}

	public void setTransportList(List<Transport> transportList) {
		this.transportList = transportList;
	}

}
