/**
 * 
 */
package com.vix.nvix.oa.attendance.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.oa.attendance.entity.HolidayRule;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Bluesnow 2016年7月14日
 * 
 *         假日管理
 */
@Controller
@Scope("prototype")
public class HoliDayRuleAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	private String treeType;
	private String treeId;
	private String empCode;
	private String empName;
	private String orgId;
	private String unitId;
	private String empId;
	private String startTime;
	private String endTime;
	private HolidayRule holidayRule;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private List<HolidayRule> holidayRuleList;

	public void getHolidayRuleTableData() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, HolidayRule.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			String start = getRequestParameter("start");
			String end = "";
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				holidayRule = baseHibernateService.findEntityById(HolidayRule.class, id);
			} else {
				holidayRule = new HolidayRule();
				if (StringUtils.isNotEmpty(start)) {
					Long startl = Long.parseLong(start);
					String times = transferLongToDate(startl);
					start = times.substring(0, 10) + " 00:00:00";
					end = times.substring(0, 10) + " 23:59:59";
					holidayRule.setHolidayStartTime(dateFormat.parse(start));
					holidayRule.setHolidayEndTime(dateFormat.parse(end));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(holidayRule.getId())) {
				isSave = false;
				holidayRule.setCreateTime(new Date());
				holidayRule.setIsTemp("0");
			} else {
				holidayRule.setUpdateTime(new Date());
				holidayRule.setIsTemp("0");
			}
			loadCommonData(holidayRule);
			/** 检查空值对象 */
			// String[] attrArray = { "emp", "unit", "org" };
			// checkEntityNullValue(holidayRule, attrArray);

			baseHibernateService.merge(holidayRule);

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

	public void holiDayRuleEvents() {
		try {
			String json = "";
			Map<String, Object> params = new HashMap<String, Object>();
			holidayRuleList = baseHibernateService.findAllByConditions(HolidayRule.class, params);
			if (holidayRuleList != null && holidayRuleList.size() > 0) {
				JSONArray array = new JSONArray();
				for (HolidayRule holidayRule : holidayRuleList) {
					JSONObject object = new JSONObject();
					object.put("id", holidayRule.getId());
					object.put("title", holidayRule.getName());
					if (holidayRule.getHolidayStartTime() != null) {
						object.put("start", dateFormat.format(holidayRule.getHolidayStartTime()));
					}
					if (holidayRule.getHolidayEndTime() != null) {
						object.put("end", dateFormat.format(holidayRule.getHolidayEndTime()));
					}
					array.add(object);
				}
				json = array.toString();
			}
			if (!"".equals(json)) {
				renderJson(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goCanlendarList() {
		List<HolidayRule> holidayRule = new ArrayList<HolidayRule>();
		String json = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String tag = getRequestParameter("tag");
			if (StringUtils.isNotEmpty(tag) && !"0".equals(tag)) {
				params.put("taskType," + SearchCondition.EQUAL, tag);
			}
			holidayRule = baseHibernateService.findAllByConditions(HolidayRule.class, params);
			if (holidayRule != null && holidayRule.size() > 0) {
				json = convertListToJson(holidayRule, holidayRule.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!"".equals(json)) {
			renderJson(json);
		} else {
			renderJson("{\"total\":0,\"rows\":[]}");
		}
	}

	public String deleteHolidayRuleById() {
		try {
			HolidayRule holidayRule = baseHibernateService.findEntityById(HolidayRule.class, id);
			if (null != holidayRule) {
				baseHibernateService.deleteByEntity(holidayRule);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * 把毫秒转化成日期
	 * 
	 * @param dateFormat
	 *            (日期格式，例如：MM/ dd/yyyy HH:mm:ss)
	 * 
	 * @param millSec
	 *            (毫秒数)
	 * 
	 * @return
	 * 
	 */

	public String transferLongToDate(Long millSec) {

		Date date = new Date(millSec);

		return dateFormat.format(date);

	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public HolidayRule getHolidayRule() {
		return holidayRule;
	}

	public void setHolidayRule(HolidayRule holidayRule) {
		this.holidayRule = holidayRule;
	}

	public List<HolidayRule> getHolidayRuleList() {
		return holidayRuleList;
	}

	public void setHolidayRuleList(List<HolidayRule> holidayRuleList) {
		this.holidayRuleList = holidayRuleList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
