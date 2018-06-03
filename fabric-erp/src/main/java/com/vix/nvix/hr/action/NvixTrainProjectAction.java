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
import com.vix.hr.trainning.entity.QuestionnaireSurvey;
import com.vix.hr.trainning.entity.TrainingActivity;
import com.vix.hr.trainning.entity.TrainingActivitySummarize;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingCourseSummarize;
import com.vix.hr.trainning.entity.TrainingProject;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @培训规划/培训活动/问卷调查/培训总结
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixTrainProjectAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private Employee employee;
    private TrainingCourse course;//培训课程
    private TrainingProject trainingProject;//培训规划
    private TrainingActivity activity;//培训活动
    private TrainingActivitySummarize activitySummarize;//培训活动总结
    private TrainingCourseSummarize  courseSummarize;//培训课程总结
    private QuestionnaireSurvey question;//问卷调查
    
	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 培训规划列表*/
	public void projectList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String projectName = getRequestParameter("projectName");
			if (StringUtils.isNotEmpty(projectName)) {
				params.put("projectName," + SearchCondition.ANYLIKE, projectName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingProject.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
   /** 增加培训规划*/
	public String goSaveOrUpdateProject(){
		try {
			if(StringUtils.isNotEmpty(id)){
				trainingProject = vixntBaseService.findEntityById(TrainingProject.class, id);
			}else{
				trainingProject = new TrainingProject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateProject";
	}
	
	/** 保存培训规划*/
	public void saveOrUpdateProject(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingProject.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainingProject);
			trainingProject = vixntBaseService.merge(trainingProject);
			if (isSave) {
				renderText("1:" + trainingProject.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + trainingProject.getId() + ":" + UPDATE_SUCCESS);
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
    ////////////////////////////////////////////////////////////////////////////////////
	/** 培训活动*/
    public String goActivityList(){
    	return "goActivityList";
    }
    
    /** 培训活动列表*/
    public void activityList(){
    	try {
			Map<String, Object> params = new HashMap<String, Object>();
			String activityName = getRequestParameter("activityName");
			if (StringUtils.isNotEmpty(activityName)) {
				params.put("activityName," + SearchCondition.ANYLIKE, activityName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingActivity.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    /** 新增培训活动*/
    public String goSaveOrUpdateActivity(){
    	try {
			if(StringUtils.isNotEmpty(id)){
				activity = vixntBaseService.findEntityById(TrainingActivity.class, id);
			}else{
				activity = new TrainingActivity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateActivity";
    }
    /** 保存培训活动*/
    public void saveOrUpdateActivity(){
    	boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(activity.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(activity);
			activity = vixntBaseService.merge(activity);
			if (isSave) {
				renderText("1:" + activity.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + activity.getId() + ":" + UPDATE_SUCCESS);
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
    ////////////////////////////////////////////////////////////////////////////////////
    /** 课程总结*/
    public String goCourseSummarizeList(){
    	return "goCourseSummarizeList";
    } 
    /** 培训规划列表*/
	public void courseSummarizeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String courseName = getRequestParameter("courseName");
			if (StringUtils.isNotEmpty(courseName)) {
				params.put("courseName," + SearchCondition.ANYLIKE, courseName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingCourseSummarize.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增课程总结*/
	public String goSaveOrUpdateCourseSummarize(){
		try {
			if(StringUtils.isNotEmpty(id)){
				courseSummarize = vixntBaseService.findEntityById(TrainingCourseSummarize.class, id);
			}else{
				courseSummarize = new TrainingCourseSummarize();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCourseSummarize";
	}
	
	/** 保存课程总结*/
	public void saveOrUpdateCourseSummarize(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(courseSummarize.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(courseSummarize);
			courseSummarize = vixntBaseService.merge(courseSummarize);
			if (isSave) {
				renderText("1:" + courseSummarize.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + courseSummarize.getId() + ":" + UPDATE_SUCCESS);
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
	/////////////////////////////////////////////////////////////////////////////////////////
	/** 活动总结*/
	public String goActivitySummarizeList(){
		return "goActivitySummarizeList";
	}
	
	/** 活动总结列表*/
	public void activitySummarizeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String activityName = getRequestParameter("activityName");
			if (StringUtils.isNotEmpty(activityName)) {
				params.put("activityName," + SearchCondition.ANYLIKE, activityName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingActivitySummarize.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增活动总结*/
	public String goSaveOrUpdateActivitySummarize(){
		try {
			if(StringUtils.isNotEmpty(id)){
				activitySummarize = vixntBaseService.findEntityById(TrainingActivitySummarize.class, id);
			}else{
				activitySummarize = new TrainingActivitySummarize();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateActivitySummarize";
	}
	
	/** 保存活动总结*/
	public void saveOrUpdateActivitySummarize(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(activitySummarize.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(activitySummarize);
			activitySummarize = vixntBaseService.merge(activitySummarize);
			if (isSave) {
				renderText("1:" + activitySummarize.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + activitySummarize.getId() + ":" + UPDATE_SUCCESS);
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
	///////////////////////////////////////////////////////////////////////////////////////
	/** 问卷调查*/
	public String goQuessionList(){
		return "goQuessionList";
	}
	
	/** 问卷调查列表*/
	public void quessionList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String questionName = getRequestParameter("questionName");
			if (StringUtils.isNotEmpty(questionName)) {
				params.put("questionName," + SearchCondition.ANYLIKE, questionName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), QuestionnaireSurvey.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增活动总结*/
	public String goSaveOrUpdateQuession(){
		try {
			if(StringUtils.isNotEmpty(id)){
				question = vixntBaseService.findEntityById(QuestionnaireSurvey.class, id);
			}else{
				question = new QuestionnaireSurvey();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateQuession";
	}
	
	/** 保存问卷调查*/
	public void saveOrUpdateQuession(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(question.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(question);
			question = vixntBaseService.merge(question);
			if (isSave) {
				renderText("1:" + question.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + question.getId() + ":" + UPDATE_SUCCESS);
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TrainingCourse getCourse() {
		return course;
	}

	public void setCourse(TrainingCourse course) {
		this.course = course;
	}

	public TrainingProject getTrainingProject() {
		return trainingProject;
	}

	public void setTrainingProject(TrainingProject trainingProject) {
		this.trainingProject = trainingProject;
	}

	public TrainingActivity getActivity() {
		return activity;
	}

	public void setActivity(TrainingActivity activity) {
		this.activity = activity;
	}

	public TrainingActivitySummarize getActivitySummarize() {
		return activitySummarize;
	}

	public void setActivitySummarize(TrainingActivitySummarize activitySummarize) {
		this.activitySummarize = activitySummarize;
	}

	public TrainingCourseSummarize getCourseSummarize() {
		return courseSummarize;
	}

	public void setCourseSummarize(TrainingCourseSummarize courseSummarize) {
		this.courseSummarize = courseSummarize;
	}

	public QuestionnaireSurvey getQuestion() {
		return question;
	}

	public void setQuestion(QuestionnaireSurvey question) {
		this.question = question;
	}
	
}
