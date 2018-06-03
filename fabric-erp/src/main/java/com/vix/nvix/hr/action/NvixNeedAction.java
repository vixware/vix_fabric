package com.vix.nvix.hr.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.trainning.entity.DemandApply;
import com.vix.hr.trainning.entity.DemandNotice;
import com.vix.hr.trainning.entity.DemandSummary;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @需求通知/需求申请/需求汇总
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixNeedAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String demandSummaryId;
	private String applylId;
	private Employee employee;
    private DemandNotice notice;//需求通知
    private DemandSummary demandSummary;//培训需求汇总
    private DemandApply apply;//需求申请
    private TrainingCourse course;//培训课程 
    
	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 需求通知列表*/
	public void goNoticeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String noticeTheName = getRequestParameter("noticeTheName");
			if (StringUtils.isNotEmpty(noticeTheName)) {
				params.put("noticeTheName," + SearchCondition.ANYLIKE, noticeTheName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, DemandNotice.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
   /** 增加需求通知*/
	public String goSaveOrUpdateNotice(){
		try {
			if(StringUtils.isNotEmpty(id)){
				notice = vixntBaseService.findEntityById(DemandNotice.class, id);
			}else{
				notice = new DemandNotice();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateNotice";
	}
	
	/** 保存*/
	public void saveOrUpdateNotice(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(notice.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(notice);
			notice = vixntBaseService.merge(notice);
		    Calendars calendars = new Calendars();
		    calendars.setDemandNotice(notice);
		    calendars.setCreateTime(new Date());
		    calendars.setCalendarsContent(notice.getNoticeContent());
		    calendars.setScheduleName(notice.getNoticeTheName());
		    calendars = vixntBaseService.merge(calendars);
			if (isSave) {
				renderText("1:" + notice.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + notice.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	/** 需求汇总*/
	public String goAllNeedList(){
		return "goAllNeedList";
	}
	
	/** 需求汇总列表*/
	public void allNeedList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String summaryName = getRequestParameter("summaryName");
			if (StringUtils.isNotEmpty(summaryName)) {
				params.put("summaryName," + SearchCondition.ANYLIKE, summaryName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, DemandSummary.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 增加需求汇总*/
	public String goSaveOrUpdateAllNeed(){
		try {
			if(StringUtils.isNotEmpty(id)){
				demandSummary = vixntBaseService.findEntityById(DemandSummary.class, id);
			}else{
				demandSummary = new DemandSummary();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAllNeed";
	}
	
	/** 选择申请*/
	public String goChooseApply(){
		return "goChooseApply";
	}
	
	public void createApply(){
		try {
			 if(StringUtils.isNotEmpty(demandSummaryId)){
				 demandSummary = vixntBaseService.findEntityById(DemandSummary.class, demandSummaryId);
				if(StringUtils.isNotEmpty(id)){
					apply = vixntBaseService.findEntityById(DemandApply.class, id);
					 if(apply != null && demandSummary!= null){
						  apply.setDemandSummary(demandSummary);
						  initEntityBaseController.initEntityBaseAttribute(apply);
						  apply = vixntBaseService.merge(apply);
						  renderText("1:"+apply.getId()+":增加成功!");
					 }else{
						 renderText("0:" + SAVE_FAIL);
					 }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 选择申请列表*/
	public void goChooseApplyList(){
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String applicationName = getRequestParameter("applicationName");
			if (StringUtils.isNotEmpty(applicationName)) {
				params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("demandSummary.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), DemandApply.class, params);
			}
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 保存汇总*/
	public void saveOrUpdateAllNeed(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(demandSummary.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(demandSummary);
			demandSummary = vixntBaseService.merge(demandSummary);
			if (isSave) {
				renderText("1:" + demandSummary.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + demandSummary.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////////////
	/** 需求申请*/
	public String goNeedApply(){
		return "goNeedApply";
	}
	
	/** 需求申请列表*/
	public void needApplyList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String applicationName = getRequestParameter("applicationName");
			if (StringUtils.isNotEmpty(applicationName)) {
				params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, DemandApply.class, params);
			String [] excludes = {" "};
			renderDataTable(pager, excludes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增需求申请*/
	public String goSaveOrUpdateNeedApply(){
		try {
			if(StringUtils.isNotEmpty(id)){
				apply = vixntBaseService.findEntityById(DemandApply.class, id);
			}else{
				apply = new DemandApply();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateNeedApply";
	}
	
	/** 选择课程列表*/
	public void goChooseCourseList(){
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String courseName = getRequestParameter("courseName");
			if (StringUtils.isNotEmpty(courseName)) {
				params.put("courseName," + SearchCondition.ANYLIKE, courseName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("demandApply.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingCourse.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 保存需求申请*/
	public void saveOrUpdateApply(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(apply.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(apply);
			apply = vixntBaseService.merge(apply);
			if (isSave) {
				renderText("1:" + apply.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + apply.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	
	/** 选择课程*/
	public String goChooseCourse(){
		return "goChooseCourse";
	}
	public void createCourse(){
		try {
			 if(StringUtils.isNotEmpty(applylId)){
				 apply = vixntBaseService.findEntityById(DemandApply.class, applylId);
				if(StringUtils.isNotEmpty(id)){
					course = vixntBaseService.findEntityById(TrainingCourse.class, id);
					 if(course != null && apply!= null){
						  course.setDemandApply(apply);
						  initEntityBaseController.initEntityBaseAttribute(course);
						  course = vixntBaseService.merge(course);
						  renderText("1:"+course.getId()+":增加成功!");
					 }else{
						 renderText("0:" + SAVE_FAIL);
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

	public String getApplylId() {
		return applylId;
	}

	public void setApplylId(String applylId) {
		this.applylId = applylId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DemandNotice getNotice() {
		return notice;
	}

	public void setNotice(DemandNotice notice) {
		this.notice = notice;
	}

	public DemandSummary getDemandSummary() {
		return demandSummary;
	}

	public void setDemandSummary(DemandSummary demandSummary) {
		this.demandSummary = demandSummary;
	}

	public DemandApply getApply() {
		return apply;
	}

	public void setApply(DemandApply apply) {
		this.apply = apply;
	}

	public TrainingCourse getCourse() {
		return course;
	}

	public void setCourse(TrainingCourse course) {
		this.course = course;
	}

	public String getDemandSummaryId() {
		return demandSummaryId;
	}

	public void setDemandSummaryId(String demandSummaryId) {
		this.demandSummaryId = demandSummaryId;
	}
	
}
