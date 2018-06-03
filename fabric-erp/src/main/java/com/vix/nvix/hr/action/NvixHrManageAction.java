package com.vix.nvix.hr.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.personnelmgr.entity.HrBecomeRegular;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @人事事务管理
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixHrManageAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private Employee employee;
    private HrBecomeRegular regular;//人事事务
    
    
    /** 转正列表*/
	public void goPassList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String litigant = getRequestParameter("litigant");
			if (StringUtils.isNotEmpty(litigant)) {
				params.put("litigant," + SearchCondition.ANYLIKE, litigant);
			}
			params.put("types," + SearchCondition.ANYLIKE, "regular");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			String[] excludes={" "};
			renderDataTable(pager,excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 转正-修改 */
	public String goSaveOrUpdatePass() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdatePass";
	}
    /** 审批人*/
	public String goCheckPersonChoose(){
		return "goCheckPersonChoose";
	}
	
	/* 异动*/
	public String goUnusual(){
		return "goUnusual";
	}
	
	/** 异动列表*/
	public void goUnusualList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "unusual");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 异动-修改 */
	public String goSaveOrUpdateUnusual() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateUnusual";
	}
	
	/* 借调*/
	public String goBorrow(){
		return "goBorrow";
	}
	
	/** 借调列表*/
	public void goBorrowList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "borrow");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 借调-修改 */
	public String goSaveOrUpdateBorrow() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateBorrow";
	}
	
	/* 请假*/
	public String goAskLeave(){
		return "goAskLeave";
	}
	
	/** 请假列表*/
	public void goAskLeaveList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "leave");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 请假-修改 */
	public String goSaveOrUpdateAskLeave() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateAskLeave";
	}
	
	/* 离职*/
	public String goDimission(){
		return "goDimission";
	}
	
	/** 离职列表*/
	public void goDimissionList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "dimission");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 离职-修改 */
	public String goSaveOrUpdateDimission() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateDimission";
	}
	
	/* 辞退*/
	public String goRetire(){
		return "goRetire";
	}
	
	/** 辞退列表*/
	public void goRetireList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "refuse");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 辞退-修改 */
	public String goSaveOrUpdateRetire() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateRetire";
	}
	
	/* 离退休*/
	public String goPrepareRetire(){
		return "goPrepareRetire";
	}
	
	/** 离退休列表*/
	public void goPrepareRetireList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "retire");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 离退休-修改 */
	public String goSaveOrUpdatePrepareRetire() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdatePrepareRetire";
	}
	
	/* 返聘*/
	public String goRehire(){
		return "goRehire";
	}
	
	/** 返聘列表*/
	public void goRehireList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String employeeName = getRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employeeName," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("types," + SearchCondition.ANYLIKE, "rehire");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrBecomeRegular.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 返聘-修改 */
	public String goSaveOrUpdateRehire() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				regular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			} else {
				regular = new HrBecomeRegular();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateRehire";
	}
	
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(regular.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(regular);
			regular = vixntBaseService.merge(regular);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		
	}
	
	/** 删除*/
	public void deleteById() {
		try {
			HrBecomeRegular becomeRegular = vixntBaseService.findEntityById(HrBecomeRegular.class, id);
			if (null != becomeRegular) {
				vixntBaseService.deleteByEntity(becomeRegular);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public HrBecomeRegular getRegular() {
		return regular;
	}

	public void setRegular(HrBecomeRegular regular) {
		this.regular = regular;
	}
	
}
