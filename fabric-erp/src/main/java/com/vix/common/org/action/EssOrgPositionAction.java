package com.vix.common.org.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.core.web.Pager;
import com.vix.hr.position.service.IOrgPositionService;

@Controller("essOrgPositionAction")
@Scope("prototype")
public class EssOrgPositionAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IEmployeeHrService employeeHrService;
	@Autowired
	private IOrgPositionService orgPositionService;

	private String orgUnitId;

	/** 职员id */
	private String empId;
	/** 岗位id */
	private String posId;

	/**
	 * 选择岗位
	 * 
	 * @return
	 */
	public String goChooseOrgPosition() {
		return "goChooseOrgPosition";
	}

	/**
	 * 
	 * @return
	 */
	public String goChooseOrgPositionList() {
		try {
			Pager pager = getPager();

			/*
			 * if(StringUtils.isNotEmpty(orgUnitId)){ String orgViewIdStr =
			 * orgUnitId.substring(0, orgUnitId.length()-1); Long
			 * parentOrgViewId = Long.parseLong(orgViewIdStr); pager =
			 * orgPositionService.findOrgPositionPager(pager, parentOrgViewId);
			 * }
			 */
			if (orgUnitId != null) {
				// pager = orgPositionService.findOrgPositionPager(pager,
				// orgUnitId,null);
			}

			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseOrgPositionList";
	}

	/**
	 * 保存岗位
	 * 
	 * @return
	 */
	public String saveOrUpdateEssOfOrgPosition() {
		boolean isSave = true;
		try {
			/*
			 * if(null != employeeId){ isSave = false; }
			 */
			if (empId != null && posId != null) {
				// System.out.println("employeeId:"+employeeId+",boid"+boId);
				employeeHrService.saveOrUpdateEmpOrgPosition(empId, posId);
			}

			setMessage(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			if (empId != null && posId != null) {
				// System.out.println("employeeId:"+employeeId+",boid"+boId);
				employeeHrService.deleteForEmpOrgPosition(empId, posId);
			}
			// employeeHrService.deleteById(UserAccount.class, id);
			// employeeHrService
			setMessage(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	public IEmployeeHrService getEmployeeHrService() {
		return employeeHrService;
	}

	public void setEmployeeHrService(IEmployeeHrService employeeHrService) {
		this.employeeHrService = employeeHrService;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

}
